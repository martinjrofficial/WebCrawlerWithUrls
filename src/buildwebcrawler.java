import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.HashSet;



class webcrawler {
	//Hashet to Link Urls 
	private HashSet<String> links = new HashSet<>();
	private String url;
	private List<String> tofile =  new ArrayList<String>();

   public webcrawler() {
     links = new HashSet<String>();
   }

   public void getPageLinks(String URL) {
       System.out.println(URL +" url to craw");
       setUrl(URL);
	   if (!links.contains(URL)) {
	   try {
	   //Get HTML source document for url using jsoup     
       Document document = Jsoup.connect(URL).get();
       //Parse the HTML to extract links to other URLs
       Elements linksOnPage = document.select("a[href]");

       //Print the urls
       links.add(URL);
       tofile.add("");
       tofile.add("Url to crawl: "+URL);
       tofile.add("");
       
       for (Element page : linksOnPage) {
    	   
           getPageLinks(page.attr("abs:href"));
           tofile.add(page.attr("abs:href"));
           
           
           System.out.println(page.attr("abs:href"));
       }
	   } catch (IOException e) {
           System.err.println("For '" + URL + "': " + e.getMessage());
       }
       }
    }
   public void setUrl(String url) {
	    this.url=url;
   }
   public void printArray() {		
	   String FNAME = "testing.txt";
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FNAME))) {
		    for (String line : tofile) {
			    bw.write(line + "\n");
		    }

		    bw.close();

	    } catch (IOException e) {
		    e.printStackTrace();
	      }
   } 
}
  
public class buildwebcrawler {
	public static void main(String[] args) {
		webcrawler wc = new webcrawler();
		wc.getPageLinks("https://wiprodigital.com/");
	

	}
}
   
