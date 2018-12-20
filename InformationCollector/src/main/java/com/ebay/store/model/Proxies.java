package com.ebay.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="proxies")
public class Proxies {
	
	private List<Proxy> proxyList = new ArrayList<Proxy>();

	public List<Proxy> getProxyList() {
		return proxyList;
	}

	@XmlElement(name="proxy")
	public void setProxyList(List<Proxy> proxyList) {
		this.proxyList = proxyList;
	}
}
