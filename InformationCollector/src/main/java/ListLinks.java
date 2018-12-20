import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ebay.store.model.Proxy;
import com.ebay.store.network.ProxyManager;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {

	private static final String DT_NODE_NAME = "dt";

	private static final String DD_NODE_NAME = "dd";

	private static final List<String> list = Arrays.asList(new String[]{});

	public static void main(String[] args) throws IOException, JAXBException {
		
		
		ProxyManager pm = new ProxyManager();
		
		CookieManager cm = new CookieManager();
		cm.getCookieStore();
		
		
		 cm.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
		    CookieHandler.setDefault(cm);

		    new URL("https://google.com").openConnection().getContent();

		    List<HttpCookie> cookies = cm.getCookieStore().getCookies();
		    for (HttpCookie cookie : cookies) {
		      System.out.println("Name = " + cookie.getName());
		      System.out.println("Value = " + cookie.getValue());
		      System.out.println("Lifetime (seconds) = " + cookie.getMaxAge());
		      System.out.println("Path = " + cookie.getPath());
		      System.out.println();
		    }
		
		

		for (String upc : list) {
			
			
			Proxy p = pm.getRandomProxy();
			
			while(!p.isHttps()) {
				p = pm.getRandomProxy();
				continue;
			}
			
			System.out.println(p.getIp());
			System.out.println(p.getPort());
			System.out.println(p.isHttps());
			
			// set proxy first
			System.setProperty("https.proxyHost", p.getIp());
			System.setProperty("https.proxyPort", Integer.toString(p.getPort()));
			

			String url = "https://www.upcindex.com/" + upc;
			
			System.out.println("###########################");
			
			print("Fetching %s...", url);

			Document doc = Jsoup.connect(url).userAgent("Dalvik/2.1.0 (Linux; U; Android 7.0; Android SDK built for x86 Build/NYC)")
				    .proxy(p.getIp(), p.getPort())
				    .header("X-Forwarded-For", p.getIp())
				    .header("Connection", "Keep-Alive")
				    .header("Accept-Encoding", "gzip")
				    
				    .referrer("http://www.cnn.com")
				    .timeout(30000)
					.get();
			
			
			Elements productInfoElements = doc.select("#product-info");

			for (Element productInfoElement : productInfoElements) {
				// parse product info
				Elements subElements = productInfoElement.getAllElements();
				
				
				StringBuilder sb = new StringBuilder();
				for (Element subElement : subElements) {
					String nodeName = subElement.nodeName();
					if (DT_NODE_NAME.equals(nodeName)) {
						System.out.println();
						sb.append("\n");
						sb.append(subElement.text());
						sb.append("=");
					} else if (DD_NODE_NAME.equals(nodeName)) {
						sb.append(subElement.text());
						sb.append(" - " );
					}

				}
				System.out.println(sb.toString());

			}
			

		}

		/*
		 * Elements links = doc.select("a[href]"); Elements media = doc.select("[src]");
		 * Elements imports = doc.select("link[href]");
		 * 
		 * print("\nMedia: (%d)", media.size()); for (Element src : media) { if
		 * (src.tagName().equals("img")) print(" * %s: <%s> %sx%s (%s)", src.tagName(),
		 * src.attr("abs:src"), src.attr("width"), src.attr("height"),
		 * trim(src.attr("alt"), 20)); else print(" * %s: <%s>", src.tagName(),
		 * src.attr("abs:src")); }
		 * 
		 * print("\nImports: (%d)", imports.size()); for (Element link : imports) {
		 * print(" * %s <%s> (%s)", link.tagName(),link.attr("abs:href"),
		 * link.attr("rel")); }
		 * 
		 * print("\nLinks: (%d)", links.size()); for (Element link : links) {
		 * print(" * a: <%s>  (%s)", link.attr("abs:href"), trim(link.text(), 35)); }
		 */
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	private static String trim(String s, int width) {
		if (s.length() > width)
			return s.substring(0, width - 1) + ".";
		else
			return s;
	}
	
	private String jsonStringConverter(String stringResponse) {
	    String[] parts = stringResponse.split("\\r\\n");
	    String jsonString = "{\"";
	    for (int i = 0; i < parts.length; i++) {
	        jsonString += parts[i].replace("=", "\":\"");
	        jsonString += (i < parts.length - 1) ? "\", \"" : "";
	    }
	    return jsonString += "\"}";
	}
}