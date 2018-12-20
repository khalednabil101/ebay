package com.ebay.store.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.ebay.store.model.Anonymity;

public class AnonymityAdapter extends XmlAdapter<String, Anonymity> {
	
	@Override
	public Anonymity unmarshal(String s) {
		return Anonymity.getFromValue(s);
	}

	@Override
	public String marshal(Anonymity anonymity) {
		return anonymity.getXmlValue();
	}
}