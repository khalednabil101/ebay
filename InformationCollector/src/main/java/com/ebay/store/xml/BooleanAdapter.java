package com.ebay.store.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<String, Boolean> {
	@Override
	public Boolean unmarshal(String s) {
		return s == null ? null : s.equalsIgnoreCase("yes");
	}

	@Override
	public String marshal(Boolean c) {
		return c == null ? null : c ? "yes" : "no";
	}
}