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
public class PlacesAndTime {
    
    private Document doc;
    private HashMap<String, String> map;

    public HashMap<String, String> getMap() {
        return map;
    }
    
    
    public PlacesAndTime(String content) {
        
        try {
            System.out.println("Ladataan listaa elokuvista...");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            
            System.out.println("Ladataan listaa elokuvista...");
            doc = dBuilder.parse(new InputSource(new StringReader(content)));
            
            System.out.println("Ladataan listaa elokuvista...");
            doc.getDocumentElement().normalize();
            
            System.out.println("Ladataan listaa elokuvista...");
            map = new HashMap();
            parseCurrentData();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(Theatres.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void parseCurrentData() {
        
        NodeList movies = doc.getElementsByTagName("Title");
        NodeList genres = doc.getElementsByTagName("Genres");
        NodeList start = doc.getElementsByTagName("dttmShowStart");
        NodeList end = doc.getElementsByTagName("dttmShowEnd");
        NodeList date = doc.getElementsByTagName("dtAccounting");
        NodeList theatre = doc.getElementsByTagName("Theatre");
        
        System.out.println(movies.getLength());
        for(int i = 0; i < movies.getLength(); i++) {
            Node node_movies = movies.item(i);
            Node node_genres = genres.item(i);
            Node node_start = start.item(i);
            Node node_end = end.item(i);
            Node node_date = date.item(i);
            Node node_theatre = theatre.item(i);
            
            
            Element e_m = (Element) node_movies;
            Element e_g = (Element) node_genres;
            Element e_s = (Element) node_start;
            Element e_e = (Element) node_end;
            Element e_d = (Element) node_date;
            Element e_t = (Element) node_theatre;
            
            String temp = String.valueOf(i);
            System.out.println(temp);
            
            System.out.println(e_m.getTextContent());
            map.put(temp , e_m.getTextContent());
            map.put(e_m.getTextContent(), e_g.getTextContent());
            map.put(e_g.getTextContent(), e_s.getTextContent());
            map.put(e_s.getTextContent(), e_e.getTextContent());
            map.put(e_e.getTextContent(), e_d.getTextContent());
            map.put(e_d.getTextContent(), e_t.getTextContent());
        
        }
        
    }
    
    private String getValue(String tag, Element e, String attr) {
        return ((Element)e.getElementsByTagName(tag).item(0)).getAttribute(attr);
    
    }
    
    
}
