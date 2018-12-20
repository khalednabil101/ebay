package com.ebay.store.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.ebay.store.xml.AnonymityAdapter;
import com.ebay.store.xml.BooleanAdapter;

@XmlRootElement
public class Proxy {
	private String ip;
	private int port;
	private String countryCode;
	private String country;
	private Anonymity anonymity;
	private boolean google;
	private boolean https;
	
	
	public String getIp() {
		return ip;
	}
	
	@XmlElement
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getPort() {
		return port;
	}
	@XmlElement
	public void setPort(int port) {
		this.port = port;
	}
	
	public String getCountryCode() {
		return countryCode;
	}
	
	@XmlElement
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	@XmlElement
	public void setCountry(String country) {
		this.country = country;
	}
	
	public Anonymity getAnonymity() {
		return anonymity;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter( AnonymityAdapter.class )
	public void setAnonymity(Anonymity anonymity) {
		this.anonymity = anonymity;
	}
	
	public Boolean isGoogle() {
		return google;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter( BooleanAdapter.class )
	public void setGoogle(Boolean google) {
		this.google = google;
	}
	
	public Boolean isHttps() {
		return https;
	}
	
	@XmlElement
	@XmlJavaTypeAdapter( BooleanAdapter.class )
	public void setHttps(Boolean https) {
		this.https = https;
	}

	@Override
	public String toString() {
		return "Proxy [ip=" + ip + ", port=" + port + ", countryCode=" + countryCode + ", country=" + country
				+ ", anonymity=" + anonymity + ", google=" + google + ", https=" + https + "]";
	}
	
}
