//package com.javarush.test.level21.lesson08.task02;
//
//import static org.junit.Assert.*;
//import static com.javarush.test.level21.lesson08.task02.Solution.Tree;
//
//public class TreeTest
//{
//
//    @org.junit.Test
//    public void testGetBranches() throws Exception
//    {
//
//    }
//
//    @org.junit.Test
//    public void testClone() throws Exception
//    {
//        Tree tree[] = new Tree[4];
//        tree[0] = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
//        tree[1] = new Tree(null, null);
//        tree[2] = new Tree("willow", null);
//        tree[3] = new Tree(null, new String[]{"s1", "s2", "s3", "s4"});
//
//        Tree clone[] = new Tree[4];
//        try {
//            for (int i = 0; i < tree.length; i++)
//                clone[i] = tree[i].clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < tree.length; i++)
//        {
//            Tree tree1 = tree[i];
//            Tree clone1 = clone[i];
//            //assertEquals(tree1, clone1);
//            assertNotEquals(tree1.hashCode(), clone1.hashCode());
//            assertEquals(tree1.getName(), clone1.getName());
//            assertNotEquals(tree1.getName().hashCode(), clone1.getName().hashCode());
//            if (tree1.getBranches() != null)
//            {
//                assertNotEquals(tree1.getBranches().hashCode(), clone1.getBranches().hashCode());
//                for (int k = 0; k < tree1.getBranches().length; k++)
//                {
//                    assertEquals(tree1.getBranches()[k], clone1.getBranches()[k]);
//                    assertNotEquals(tree1.getBranches()[k].hashCode(), clone1.getBranches()[k].hashCode());
//                }
//            }
//        }
//
//    }
//}