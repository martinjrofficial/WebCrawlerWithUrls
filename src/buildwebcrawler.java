import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;


public class buildwebcrawler {
	//Hashet to Link Urls 
	private HashSet<String> links = new HashSet<>();


   public buildwebcrawler() {
     links = new HashSet<String>();
   }

   public void getPageLinks(String URL) {
       
	   if (!URL.isEmpty()) {
	   try {
	   //Get HTML source document for url using jsoup     
       Document document = Jsoup.connect(URL).get();
       //Parse the HTML to extract links to other URLs
       Elements linksOnPage = document.select("a[href]");

       //Print the urls
       for (Element page : linksOnPage) {
           getPageLinks(page.attr("abs:href"));
       }
	   } catch (IOException e) {
           System.err.println("For '" + URL + "': " + e.getMessage());
       }
       }
    }
}  

   
