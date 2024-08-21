package com.davideferrara.jx.classes;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XmlConfig {
    private Document configXml;

    public XmlConfig(String CONFIG_FILE) {
        try {
            configXml = loadDocument(CONFIG_FILE);
        } catch (Exception e) {
            System.exit(-1);
        }
    }

    public Document loadDocument(String fileName) throws Exception {
        try
        {
            File file = new File(fileName);
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(file);
        }
        catch (Exception e) {
            System.exit(-1);
            return null;
        }
    }

    public String getValueFromElement(String parentTagName, String tagName) {
        try {
            Element root = configXml.getDocumentElement();
            NodeList parentList = root.getElementsByTagName(parentTagName);
            if (parentList.getLength() > 0) {
                Element parentElement = (Element) parentList.item(0);
                NodeList nodeList = parentElement.getElementsByTagName(tagName);
                if (nodeList.getLength() > 0) {
                    return nodeList.item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Tags are not valid!");
        }
        return null;
    }

}
