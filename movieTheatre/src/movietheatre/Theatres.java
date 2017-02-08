/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietheatre;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author camilla
 */
public class Theatres {
    
    private Document doc;
    private HashMap<String, String> map;

    public HashMap<String, String> getMap() {
        return map;
    }
    
    
    public Theatres(String content) {
        
        try {
            System.out.println("Ladataan teatterien nimiä...");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            System.out.println("Ladataan teatterien nimiä...");
            doc = dBuilder.parse(new InputSource(new StringReader(content)));
            
            System.out.println("Ladataan teatterien nimiä...");
            doc.getDocumentElement().normalize();
            
            System.out.println("Ladataan teatterien nimiä...");
            map = new HashMap();
            parseCurrentData();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Theatres.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void parseCurrentData() {
        
        NodeList theatres = doc.getElementsByTagName("Name");
        NodeList ids = doc.getElementsByTagName("ID");
        
        for(int i = 2; i < ids.getLength(); i++) {
            Node node1 = theatres.item(i);
            Node node2 = ids.item(i);
            Element e1 = (Element) node1;
            Element e2 = (Element) node2;
            
            //System.out.println(e1.getTextContent());
            //System.out.println(e2.getTextContent());
            //System.out.println(e.getElementsByTagName("Name").item(i));
            map.put(e1.getTextContent(), e2.getTextContent());
        
        }
        
    }
    
    private String getValue(String tag, Element e, String attr) {
        return ((Element)e.getElementsByTagName(tag).item(0)).getAttribute(attr);
    
    }
    
}
