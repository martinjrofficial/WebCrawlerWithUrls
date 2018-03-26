import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;


class webcrawler {
	//Hashet to Link Urls 
	private HashSet<String> links = new HashSet<>();
	private String url;

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
       for (Element page : linksOnPage) {
    	   
           getPageLinks(page.attr("abs:href"));
           
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
}  
public class buildwebcrawler {
	public static void main(String[] args) {
		webcrawler wc = new webcrawler();
		wc.getPageLinks("https://wiprodigital.com/");
	

	}
}
   
