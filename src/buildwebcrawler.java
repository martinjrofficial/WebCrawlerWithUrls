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
    
    private String url;
    private HashSet<String> links;
    private List<String> tofile =  new ArrayList<String>();
   
   
    public webcrawler() {
        links = new HashSet<>();
        }
    public void geturls(String URL) {
       
       try {
           links.add(URL);
           tofile.add("");
           tofile.add("Url to Crawl :"+URL);
           tofile.add("");
           tofile.add("All URLS in ["+ URL + "] are ");
           tofile.add("");
           
    Document document = Jsoup.connect(URL).get();
    Elements linksOnPage = document.select("a[href]");
    for (Element page : linksOnPage) {
       
       tofile.add(page.attr("abs:href"));
       
       System.out.println(page.attr("abs:href"));
       getLinks(page.attr("abs:href"));    
               
    }
   
    
} catch (IOException e) {
    System.err.println("For '" + URL + "': " + e.getMessage());
}
    }
    public void getPageLinks(String URL) {
       seturl(URL);
       
       tofile.add("First Page "+URL);
       // System.out.println();
       geturls(URL);
       
    }
    
    
    public void getLinks(String page) {
       System.out.println("urls");
    
        
       
              geturls(page);
   
          
       
    
    }
public void seturl(String URL) {
       url=URL;
}
public void printarray() {

           final String FNAME = "testing.txt";
              try ( BufferedWriter bw = 
                           new BufferedWriter (new FileWriter (FNAME)) ) 
              {                    
                     for (String line : tofile) {
                           bw.write (line + "\n");
                     }
                     
                     bw.close ();
                     
              } catch (IOException e) {
                     e.printStackTrace ();
              }
       }
}
class buildwebcrawler{
public static void main(String[] args) {
       webcrawler cd = new webcrawler();
    cd.getPageLinks("https://wiprodigital.com/");
    cd.printarray();
    
}
}
