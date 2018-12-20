import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBException;

/**
 * Example program to list links from a URL.
 */
public class testcookie {

	public static void main(String[] args) throws IOException, JAXBException {

		String query = "";
		
		HttpURLConnection connection = (HttpURLConnection) new URL("https://r1.upcindex.com/static/css/1544905383.css" + query).openConnection();
		connection.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
		
		connection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36");
		connection.setRequestProperty("Host", "r1.upcindex.com:443");
		connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
		connection.setRequestProperty("Referer", "https://www.upcindex.com/12234112428");
		connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
		connection.setRequestProperty("Cookie", "__cfduid=d11dcaa77b2d1e7551dc67f66edc9f4aa1545068297; _jsuid=1733750867; _jsuid=3649067119; cf_clearance=cba45030387b34c23c4c85925f792d2ec40864f3-1545085151-300-250; _first_pageview=1; __atuvc=27%7C51; __atuvs=5c181985d23c71bf005");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//connection.setRequestProperty("Cookie", "ad-id=A7Xiou6BPEnnuhs8-ZimXUg; ad-privacy=0; __qca=P0-752150681-1542258758339; __idcontext=eyJjb29raWVJRCI6IkhXVFpKTVdSVDNYN0s0NVRRUTNTWkVDSkNaWEM3MkZMSkhaNDdGNEtYS1JBPT09PSIsImRldmljZUlEIjoiSFdUWkpNV1JUVFc3TVdWSlNRS0FOQ0NGREI0UTVaTlZHWEo2Qk9WTFcyVEE9PT09IiwiaXYiOiI1TzZPWUEyWEJNVUNBTUZNSjRNTUtMSkRSND09PT09PSIsInYiOjF9");
		
		connection.setUseCaches(false); 
		
		
		//connection.connect();
		
		InputStream is = null;
		try {
		    is = connection.getInputStream();
		} catch (IOException ioe) {
		    if (connection instanceof HttpURLConnection) {
		        HttpURLConnection httpConn = (HttpURLConnection) connection;
		        int statusCode = httpConn.getResponseCode();
		        if (statusCode != 200) {
		           // is = httpConn.getErrorStream();
		            
		        }
		    }
		}
		
		

		BufferedReader r = new BufferedReader(
				new InputStreamReader(is, Charset.forName("UTF-8")));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
	}
}