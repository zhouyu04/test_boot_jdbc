package com.zzyy.rxml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.DefaultText;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @Auther: zhouyu
 * @Date: 2019/10/8 17:14
 * @Description:
 */
public class XmlReader {


    public static Object readXml(String filePath) throws Exception {


        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(new File(filePath));
        //3.获取根节点
        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();

        recCust(iterator);

        return null;
    }
    /**
     * 功能描述: 递归出所有节点及子节点
     *
     * @auther: zhouyu
     * @date: 2019/10/9 9:55
     */
    private static void recCust(Iterator iterator) {

        while (iterator.hasNext()) {

            Element next = (Element) iterator.next();

            List<Attribute> attributes = next.attributes();
            String name1 = next.getName();
            System.out.println("节点name:" + name1);
            for (Attribute at : attributes) {
                String value = at.getValue();
                System.out.println("value:" + value);
            }

            Iterator iterator1 = next.elementIterator();
            while (iterator1.hasNext()) {
                Element element = (Element) iterator1.next();

                String name2 = element.getName();
                String stringValue = element.getStringValue();
                System.out.println("子节点名称:"+name2+"子节点值:"+stringValue);

            }
        }

//        while (iterator.hasNext()) {
//
//            Element next = (Element) iterator.next();
//
//            List<Attribute> attributes = next.attributes();
//            String name1 = next.getName();
//            int i = next.attributeCount();
//            System.out.println("节点name:" + name1 + "子节点数:" + i);
//            for (Attribute at : attributes) {
//                String value = at.getValue();
//                System.out.println("value:" + value);
//            }
//
//            Iterator iterator1 = next.elementIterator();
//
//            recCust(iterator1);
//        }
    }


    public static void main(String[] args) {

        try {
            readXml("src\\main\\resources\\customXml.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
