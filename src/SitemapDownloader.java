import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SitemapDownloader {
    /* List all files from a directory and its subdirectories
    * @param directoryName to be listed
    */
	private String directoryName = "C:\\SitemapProjects\\Sitemap\\WebsitesSitemaps\\";
	public SitemapDownloader()
	{
		findAllRobotsFiles(directoryName);
	}
	private void findSitemapURLinRobotsFile(String robotsFilePath, String directoryName)
	{
		BufferedReader br = null;
		int isSitemapAvailable = 0;
		String sitemapUrl = null;
		try {
			String sCurrentLine, subStringSitemap;
			br = new BufferedReader(new FileReader(robotsFilePath));
			while ((sCurrentLine = br.readLine()) != null) {
				if(sCurrentLine.length()>=7)
				{
					subStringSitemap = sCurrentLine.substring(0, 7);
					if(subStringSitemap.matches("Sitemap"))
					{
						sitemapUrl = sCurrentLine.substring(9, sCurrentLine.length());
						System.out.println(sCurrentLine);
						System.out.println(sitemapUrl);
						FileDownloader.downloadFile(sitemapUrl, directoryName);
						System.out.println();
						isSitemapAvailable = 1;
					}
				}
			}
			if(isSitemapAvailable == 0)
			{
				System.out.println("Sitemap path not available in " + robotsFilePath);
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
   private void findAllRobotsFiles(String directoryName){
       File directory = new File(directoryName);
       //get all the files from a directory
       File[] fList = directory.listFiles();
       for (File file : fList){
           if (file.isFile()){
               //System.out.println(file.getAbsolutePath());
               findSitemapURLinRobotsFile(file.getAbsolutePath(), directoryName);
           } else if (file.isDirectory()){
               findAllRobotsFiles(file.getAbsolutePath());
           }
       }
   }
}