package com.ebay.store.network;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.ebay.store.model.Anonymity;
import com.ebay.store.model.Proxies;
import com.ebay.store.model.Proxy;

public class ProxyManager {

	private List<Proxy> proxyList = new ArrayList<>();
	
	private int proxyListSize;
	
	private static final String PROXIES_FILE = "proxies.xml";
	
	public ProxyManager() throws JAXBException {
		super();
		init();
	}

	private void init() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Proxies.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		String fileName = this.getClass().getClassLoader().getResource(PROXIES_FILE).getFile();
		File proxiesFile = new File(fileName);
		
		Proxies proxies = (Proxies) jaxbUnmarshaller.unmarshal(proxiesFile);
		proxyList = proxies.getProxyList();
		proxyListSize = proxyList.size();
	}
	
	protected List<Proxy> getProxyList() {
		return proxyList;
	}
	
	public Proxy getRandomProxy() {
		int randomProxyIndex = new Random().nextInt(proxyListSize - 1);
		return proxyList.get(randomProxyIndex);
	}
	
	public static void main(String args[]) throws JAXBException {
		ProxyManager pm = new ProxyManager();
		pm.init();
		/*for (int i =0; i <2000 ; i++) {
		System.out.println(pm.getRandomProxy().getIp());
		}*/
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Proxies.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		
		for (Proxy p: pm.getProxyList()) {
			if (p.getAnonymity() == Anonymity.ELITE_PROXY){
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
				jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			    StringWriter sw = new StringWriter();
			    jaxbMarshaller.marshal(p, sw);

			    String result = sw.toString();
			    String header = "http://";
			    
			    if (p.isHttps()) {
			    	header = "https://";
			    }
			    
			    String url = "\"" + header + p.getIp() + ":" + p.getPort() + "/" + "\"" + ",";
			    
			    
			    System.out.println(url);
			}
		}
		
	}
}
