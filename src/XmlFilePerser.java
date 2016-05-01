import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class XmlFilePerser {
 
  public static void main(String[] args) throws Exception {
    SAXParserFactory parserFactor = SAXParserFactory.newInstance();
    SAXParser parser = parserFactor.newSAXParser();
    SAXHandler handler = new SAXHandler();
    parser.parse(ClassLoader.getSystemResourceAsStream("xml\\Sitemap.xml"),
                 handler);
    
    //Printing the list of employees obtained from XML
    for ( URL url : handler.urlList){
     System.out.println(url.loc);
     System.out.println(url.lastmod);
     System.out.println(url.changefreq);
     System.out.println(url.priority);
     System.out.println();    
    }

 System.out.println("Total number of URLs in the Sitemap.xml file :" + handler.urlList.size() );
  }
}
/**
 * The Handler for SAX Events.
 */
class SAXHandler extends DefaultHandler {
 
  List<URL> urlList = new ArrayList<>();
  URL url = null;
  String content = null;
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d'T'HH:mm:ss");
  @Override
  //Triggered when the start of tag is found.
  public void startElement(String uri, String localName,
                           String qName, Attributes attributes)
                           throws SAXException {
  
    switch(qName){
      //Create a new Employee object when the start tag is found
      case "url":
        url = new URL();
        //emp.id = attributes.getValue("id");
        break;
    }
  }
 
  @Override
  public void endElement(String uri, String localName,
                         String qName) throws SAXException {
   switch(qName){
     //Add the url to list once end tag is found
     case "url":
       urlList.add(url); 
       break;
     //For all other end tags the url has to be updated.
     case "loc":
       url.loc = content;
       break;
     case "lastmod":
      try
         {
      url.lastmod = sdf.parse( content );
         }
         catch (ParseException e)
         {
          e.printStackTrace();
         } 
         break;
     case "changefreq":
       url.changefreq = content;
       break;
     case "priority":
     url.priority = Float.parseFloat( content );
   }
  }
 
  @Override
  public void characters(char[] ch, int start, int length)
          throws SAXException {
    content = new String(ch, start, length);
  }
 /* 
  SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-d'T'HH:mm:ssZ");
  Date date;
      try
      {
        date = parser.parse(dateTimeString);
      }
      catch (ParseException e)
      {
        e.printStackTrace();
        return null;
      }
  */
  
/*
  // prints data stored in between '<' and '>' tags  
  public void characters(char ch[], int start, int length)  
    throws SAXException {  
     
   if (firstNameTag.equals("open")) {  
    System.out.println("First Name : " + new String(ch, start, length));  
   }  
   if (lastNameTag.equals("open")) {  
    System.out.println("Last Name : " + new String(ch, start, length));  
   }  
   if (emailTag.equals("open")) {  
    System.out.println("Email : " + new String(ch, start, length));  
   }  
   if (phoneTag.equals("open")) {  
    System.out.println("Phone : " + new String(ch, start, length));  
   }  
  }  

 */
}
 
class URL {
  String loc;
  Date lastmod;
  String changefreq;
  Float priority;
 
//  @Override
/*  public String toString() {
    return firstName + " " + lastName + "(" + id + ")" + location;
  }
*/
}