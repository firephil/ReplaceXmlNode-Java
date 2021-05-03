/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package replacexmlnode;

import java.io.StringWriter;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
public static void main (String args[]){
//get pomweb dependency
File fXmlFile = new File(prop.getProperty("pomwebfile"));//path to pomweb file
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    Document document;
    Node resultPomweb = null;
   
    document = dbf.newDocumentBuilder().parse(fXmlFile);
    XPath xPath = XPathFactory.newInstance().newXPath();
    String xpathStr = "/project/dependencies";
    resultPomweb = (Node) xPath.evaluate(xpathStr, document, XPathConstants.NODE);
        //System.out.println(nodeToString(resultPomweb));
        
        //get pom.xml depenedencies 
    File fXmlFile2 = new File(prop.getProperty("pomfile"));//path to pom file
    DocumentBuilderFactory dbf2 = DocumentBuilderFactory.newInstance();
    Document document2;
    Node resultPomxml = null;
       
    document2 = dbf2.newDocumentBuilder().parse(fXmlFile2);
    XPath xPath2 = XPathFactory.newInstance().newXPath();
    String xpathStr2 = "/project/dependencies";
    resultPomxml = (Node) xPath2.evaluate(xpathStr2, document2, XPathConstants.NODE);
           
       
           //System.out.println(nodeToString(resultPomxml));
    Path testpom = Paths.get(prop.getProperty("testPom"));
        //read file into a string
    String pomfile = new String(Files.readAllBytes(testpom),StandardCharsets.UTF_8);
        
        // find and replace and write to file
    if(pomfile.contains(nodeToString(resultPomxml))) {
          log.info("found");
          pomfile=pomfile.replace(nodeToString(resultPomxml), nodeToString(resultPomweb));
      }
      
     Files.write(testpom, pomfile.getBytes())
}

*/