package com.gtmap.platform.util;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;


public class XmlReader {
	
	 /**
     * 根据XML文档路径得到该文档对象
     * 
     * @param parseXmlPath String
     * @return Document
     * @exception
     */
    public static Document getParseXmlDocument(String parseXmlPath)
    {
        
        if (parseXmlPath == null)
        {
            return null;
        }
        SAXReader reader = new SAXReader();
        Document doc = null;
        
        try
        {
            // 将文档转换为输入流
            InputStream is = XmlReader.class.getResourceAsStream(parseXmlPath);
            doc = reader.read(is);
            return doc;
        }
        catch (DocumentException de)
        {
            return null;
        }
    }
}
