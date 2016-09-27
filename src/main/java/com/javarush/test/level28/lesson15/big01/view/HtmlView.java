package com.javarush.test.level28.lesson15.big01.view;

import com.javarush.test.level28.lesson15.big01.Controller;
import com.javarush.test.level28.lesson15.big01.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by pdudenkov on 29.06.2016.
 */
public class HtmlView implements View
{
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replaceAll("\\.", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies)
    {
        try
        {
            String updatedFileContent = getUpdatedFileContent(vacancies);
            updateFile(updatedFileContent);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some exception occurred");
        }
    }

    @Override
    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    protected Document getDocument() throws IOException {
        return Jsoup.parse(new File(filePath), "UTF-8");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) throws IOException
    {
        Document document = getDocument();

        Element template = document.clone().getElementsByClass("template")
                .removeAttr("style")
                .removeClass("template").first();

        document.select("tr[class=vacancy]").remove();

        for (Vacancy vacancy : vacancies)
        {
            Element currentTag = template.clone();
            currentTag.select("[class=city]").append(vacancy.getCity());
            currentTag.select("[class=companyName]").append(vacancy.getCompanyName());
            currentTag.select("[class=salary]").append(vacancy.getSalary());
            currentTag.select("a").attr("href", vacancy.getUrl()).append(vacancy.getTitle());
            document.select("tr[class=vacancy template]").before(currentTag.outerHtml());
        }

        return document.html();
    }

    private void updateFile(String fileContent) throws IOException
    {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(fileContent);
        fileWriter.flush();
        fileWriter.close();
    }
}
