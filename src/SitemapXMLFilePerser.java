import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class SitemapXMLFilePerser {
	   public static void main(String[] args){		   
		      try {	
		         File inputFile = new File("C:\\SitemapProjects\\Sitemap\\WebsitesSitemaps\\www.artipot.com\\sitemap_index.xml");
		         DocumentBuilderFactory dbFactory 
		            = DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		         Document doc = dBuilder.parse(inputFile);
		         doc.getDocumentElement().normalize();
		         System.out.println("Root element :" 
		            + doc.getDocumentElement().getNodeName());
		         NodeList nList = doc.getElementsByTagName("sitemap");
		         System.out.println("----------------------------");
		         for (int temp = 0; temp < nList.getLength(); temp++) {
		            Node nNode = nList.item(temp);
		            System.out.println("\nCurrent Element :" 
		               + nNode.getNodeName());
		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		               Element eElement = (Element) nNode;
		               System.out.println("Location : " 
		                  + eElement
		                  .getElementsByTagName("loc")
		                  .item(0)
		                  .getTextContent());
		               System.out.println("Last modified date : " 
		               + eElement
		                  .getElementsByTagName("lastmod")
		                  .item(0)
		                  .getTextContent());
		            }
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		   }
	   public static void downloadAllSitemapsFromSitemapFile(String directoryName) {
	       File directory = new File(directoryName);
	       String XMLFileName;
	       String sitemapUrl = null;
	       //get all the files from a directory
	       File[] fList = directory.listFiles();
	       for (File file : fList){
	    	   XMLFileName = file.getName(); 
	           if (XMLFileName.substring(XMLFileName.length()-4, XMLFileName.length()).matches(".xml")){
	        	   try {	
	  		         File inputFile = new File(file.getAbsolutePath());
	  		         DocumentBuilderFactory dbFactory 
	  		            = DocumentBuilderFactory.newInstance();
	  		         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	  		         Document doc = dBuilder.parse(inputFile);
	  		         doc.getDocumentElement().normalize();
	  		         System.out.println("Root element :" 
	  		            + doc.getDocumentElement().getNodeName());
	  		         NodeList nList = doc.getElementsByTagName("sitemap");
	  		         System.out.println("----------------------------");
	  		         for (int temp = 0; temp < nList.getLength(); temp++) {
	  		            Node nNode = nList.item(temp);
	  		            System.out.println("\nCurrent Element :" 
	  		               + nNode.getNodeName());
	  		            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	  		               Element eElement = (Element) nNode;
	  		               sitemapUrl = eElement.getElementsByTagName("loc").item(0).getTextContent();
	  		               System.out.println("Location : " 
	  		                  + eElement
	  		                  .getElementsByTagName("loc")
	  		                  .item(0)
	  		                  .getTextContent());
	  		             FileDownloader.downloadFile(sitemapUrl, directoryName);
							
	  		               
	  		               System.out.println("Last modified date : " 
	  		               + eElement
	  		                  .getElementsByTagName("lastmod")
	  		                  .item(0)
	  		                  .getTextContent());
	  		            }
	  		         }
	  		      } catch (Exception e) {
	  		         e.printStackTrace();
	  		      }
	           } 
	       }
	} 
}
