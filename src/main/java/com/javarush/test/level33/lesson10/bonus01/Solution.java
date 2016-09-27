package com.javarush.test.level33.lesson10.bonus01;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.Queue;

/* Комментарий внутри xml
Реализовать метод toXmlWithComment, который должен возвращать строку - xml представление объекта obj.
В строке перед каждым тэгом tagName должен быть вставлен комментарий comment.
Сериализация obj в xml может содержать CDATA с искомым тегом. Перед ним вставлять комментарий не нужно.

Пример вызова:  toXmlWithComment(firstSecondObject, "second", "it's a comment")
Пример результата:
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<first>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second>some string</second>
    <!--it's a comment-->
    <second><![CDATA[need CDATA because of < and >]]></second>
    <!--it's a comment-->
    <second/>
</first>
*/

//my solution
public class Solution {
    public static void main(String[] args)
    {
        First first = new First();
        System.out.println(toXmlWithComment(first, "second", "it's a comment"));
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) {
        try
        {
            Marshaller marshaller = JAXBContext.newInstance(obj.getClass()).createMarshaller();
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(obj, document);

            Queue<Node> cdataQueue = new ArrayDeque<>();
            cdataQueue.add(document);
            while (!cdataQueue.isEmpty()) {
                Node currentNode = cdataQueue.poll();
                if (currentNode.getNodeName().equals(tagName)) {
                    Node parentNode = currentNode.getParentNode();
                    parentNode.insertBefore(document.createComment(comment), currentNode);
                }
                if (currentNode.hasChildNodes()) {
                    NodeList childNodes = currentNode.getChildNodes();
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        cdataQueue.add(childNodes.item(i));
                    }
                } else if (currentNode.getNodeType() == Node.TEXT_NODE && currentNode.getNodeValue().matches(".*[<>&].*")) {
                    Node parent = currentNode.getParentNode();
                    parent.appendChild(document.createCDATASection(currentNode.getNodeValue()));
                    parent.removeChild(currentNode);
                }
            }

            StringWriter stringWriter = new StringWriter();
            Source source = new DOMSource(document);
            Result result = new StreamResult(stringWriter);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(source, result);

            return stringWriter.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

//submitted solution
//public class Solution {
//    public static void main(String[] args)
//    {
//        First first = new First();
//        System.out.println(toXmlWithComment(first, "second", "it's a comment"));
//    }
//
//    public static String toXmlWithComment(Object obj, String tagName, String comment) {
//
//        try {
//
//            JAXBContext context = JAXBContext.newInstance(obj.getClass());
//            Marshaller marshaller = context.createMarshaller();
//            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
//            marshaller.marshal(obj, doc);
//
//            NodeList nodes = doc.getElementsByTagName("*");
//
//            for (int i = 0; i < nodes.getLength(); i++) {
//                Node node = nodes.item(i);
//
//                if (node.getNodeName().equals(tagName)) {
//                    Comment com = doc.createComment(comment);
//                    node.getParentNode().insertBefore(com, node);
//                }
//                replaceTextWithCDATA(node, doc);
//            }
//            Transformer transformer = TransformerFactory.newInstance().newTransformer();
//            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//
//            StringWriter sw = new StringWriter();
//            transformer.transform(new DOMSource(doc), new StreamResult(sw));
//            return sw.toString();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    private static void replaceTextWithCDATA(Node node, Document doc) {
//        if ((node.getNodeType() == 3) && (Pattern.compile("[<>&'\"]").matcher(node.getTextContent()).find())) {
//
//            Node cnode = doc.createCDATASection(node.getNodeValue());
//            node.getParentNode().replaceChild(cnode, node);
//        }
//
//        NodeList list = node.getChildNodes();
//
//        for (int i = 0; i < list.getLength(); i++) {
//            replaceTextWithCDATA(list.item(i), doc);
//        }
//    }
//}