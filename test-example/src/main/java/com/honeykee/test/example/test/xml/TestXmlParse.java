package com.honeykee.test.example.test.xml;

import lombok.SneakyThrows;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2020-02-26 15:12
 * @since JDK 1.8
 *
 * @see // https://www.cnblogs.com/Sharley/p/8623356.html
 */
public class TestXmlParse {

    /**
     <?xml version="1.0" encoding="UTF-8"?>
     <bookstore>
         <book id="1">
             <name>挪威的森林</name>
             <author>村上春树</author>
             <year>2014</year>
             <price>89</price>
         </book>
         <book id="2">
             <name>java从入门到放弃</name>
             <year>2018</year>
             <price>222</price>
             <language>English</language>
         </book>
     </bookstore>
     */

    @SneakyThrows
    public static void parseXmlWithJdom( String xml) {
        SAXBuilder saxBuilder  = new SAXBuilder();
        StringReader stringReader = new StringReader( xml );
        org.jdom2.Document document = saxBuilder.build( stringReader );
        org.jdom2.Element rootElement = document.getRootElement();

        Iterator< org.jdom2.Attribute > iterator = rootElement.getAttributes().iterator();

        while ( iterator.hasNext() ){

        }
        List< org.jdom2.Element > children = rootElement.getChildren();

        Iterator< org.jdom2.Element > iterator1 = children.iterator();
        while ( iterator1.hasNext() ){
            org.jdom2.Element book = iterator1.next();
            List< org.jdom2.Attribute > bookAttributes = book.getAttributes();
            for ( org.jdom2.Attribute  attribute : bookAttributes ){

            }
            List< org.jdom2.Element > bookChildren = book.getChildren();

            for ( org.jdom2.Element children1 : bookChildren ){
                children1.getName();

            }
        }
    }


    @SneakyThrows
    public static void parseXmlWithDom4j( String xml) {
        SAXReader saxReader = new SAXReader();

        StringReader stringReader = new StringReader( xml );
        InputSource source = new InputSource(stringReader);
        Document document = saxReader.read( stringReader );

        Element rootElement = document.getRootElement();

        Iterator< Element > it = rootElement.elementIterator();
        while(it.hasNext()){
            Element book = it.next();
            List< Attribute > attributes = book.attributes();
            for ( Attribute  attribute : attributes ){
                System.out.println("属性名称：" + attribute.getName() + ", 值："+ attribute.getData() + ",value:" + attribute.getValue() );
            }
            Iterator< Element > elementIterator = book.elementIterator();
            while(elementIterator.hasNext() ){
                Element bookChild = elementIterator.next();
                System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
            }
        }
    }


    public static void main( String[] args ) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<bookstore>\n" +
                "    <book id=\"1\">\n" +
                "        <name>挪威的森林</name>\n" +
                "        <author>村上春树</author>\n" +
                "        <year>2014</year>\n" +
                "        <price>89</price>\n" +
                "    </book>\n" +
                "    <book id=\"2\">\n" +
                "        <name>java从入门到放弃</name>\n" +
                "        <year>2018</year>\n" +
                "        <price>222</price>\n" +
                "        <language>English</language>\n" +
                "    </book>    \n" +
                "</bookstore>";

        parseXmlWithDom4j( xml );
    }


}
