import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.io.IOException;
import java.util.HashSet;

class webCrawler {
    
    private String url;
    private HashSet<String> links;
    private List<String> tofile =  new ArrayList<String>();
    private static final String FNAME = "testing.txt";
   
   
    public webCrawler() {
        links = new HashSet<>();
        }
    

	public void getUrls(String url) {
		//Create an array list to store childurl for each node urls
		ArrayList<String> childUrls = new ArrayList<String>();
		try {
			links.add(url);
			tofile.add("");
			tofile.add("New url to crawl is" + url);
			tofile.add("");
			tofile.add("All URLS in [" + url + "] are ");
			tofile.add("");
            //Use Jsoup to collect HTML source documents of Url
			Document document = Jsoup.connect(url).get();
			//To get all elements of href url with tag <a>
		    Elements linksOnPage = document.select("a[href]");
		    //To get all elements of urf, static links and otherwise
		    Elements alllink = document.select("[href]"); // a without href
		    //To get urls of images
		    Elements img = document.select("img[src]");
			
		   //Get urls that has child nodes and base to arraylist of urls with child nodes 
		   for (Element page : linksOnPage) {
				childUrls.add(page.attr("abs:href"));
			}
           //Get urls of all links and add to sitemap
			for (Element page : alllink) {
				tofile.add(page.attr("abs:href"));
			}
			//Get image urls and add to urls in sitemap
			for (Element page : img) {
				tofile.add(page.attr("abs:src"));
			}
			getLinks(childUrls);

		} catch (IOException e) {
			System.err.println("For '" + url + "': " + e.getMessage());
		  }
	}
    

	public void getPageLinks(String url) {
        setUrl(url);
		tofile.add("First Page " + url);
		//Get child url nodes of first url
		getUrls(url);

	}
    


	public void getLinks(ArrayList<String> childLinks) {
		//check if url has been crawled and if it is not an outgoing link from the site
		//if not, crawl the url
		if (!childLinks.isEmpty()) {
			for (String urls : childLinks) {
				if (!links.contains(urls) && urls.contains(url))
					getUrls(urls);
				else
					System.out.print("");
			}
			
		}
	}

    
    public void setUrl(String url) {
	    this.url=url;
    }

    
    public void printArray() {	
    	//Write to file to generate site map
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

class buildwebcrawler {
	public static void main(String[] args) {
		webCrawler cd = new webCrawler();
		cd.getPageLinks("https://wiprodigital.com/");//Site to crawl
		cd.printArray();

	}
}