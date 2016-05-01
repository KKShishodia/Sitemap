import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
 
public class RobotsFileDownloader {
	   public static void ArticlesSites ( ) throws IOException {
			BufferedReader br = null;
			String url = null;
			String WebsitesSitemapsPath = "C:\\SitemapProjects\\Sitemap\\WebsitesSitemaps\\";
			String dirName = null;
//			String robotsFileName = null;
			String WebsiteName = null;
			try {
				String sCurrentLine;
				br = new BufferedReader(new FileReader("C:\\SitemapProjects\\Sitemap\\ArticlesSites.txt"));
				while ((sCurrentLine = br.readLine()) != null) {
					System.out.println(sCurrentLine);
					url = sCurrentLine.concat("robots.txt");
					
					// Split url into segments
					String segments[] = sCurrentLine.split("/");
					// Grab the last segment
					WebsiteName = segments[segments.length - 1];
					System.out.println(WebsiteName);
					
					dirName = WebsitesSitemapsPath.concat(WebsiteName);
					//Creating a directory for the site. 
					new File(dirName).mkdir();
					
//					robotsFileName = dirName.concat("\\robots.txt");
					try {
				           FileDownloader.downloadFile(url, dirName);
				           } catch (IOException e) {
				            e.printStackTrace();
				            }
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
	   	}
	   public static void main(String[] args) throws IOException {
	        ArticlesSites();
	        System.out.println();
	        new SitemapDownloader();
	        
	    }
}