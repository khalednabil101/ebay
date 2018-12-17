import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {

	private static final String DT_NODE_NAME = "dt";

	private static final String DD_NODE_NAME = "dd";

	private static final List<String> list = Arrays.asList(new String[]{"848467046519"});

	public static void main(String[] args) throws IOException {

		for (String upc : list) {

			String url = "https://www.upcindex.com/" + upc;
			print("Fetching %s...", url);

			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36")
				      .get();
			Elements productInfoElements = doc.select("#product-info");

			for (Element productInfoElement : productInfoElements) {
				// parse product info
				Elements subElements = productInfoElement.getAllElements();
				for (Element subElement : subElements) {
					String nodeName = subElement.nodeName();
					if (DT_NODE_NAME.equals(nodeName)) {
						System.out.println();
						System.out.print(subElement.text());
						System.out.print(":  ");
					} else if (DD_NODE_NAME.equals(nodeName)) {
						System.out.print(subElement.text());
						System.out.print(" - ");
					}

				}

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
}