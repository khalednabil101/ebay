package com.ebay.store.model;

public enum Anonymity {
	ELITE_PROXY("elite proxy"), TRANSPARENT("transparent"), ANONYMOUS("anonymous");

	private String xmlValue;

	Anonymity(String xmlValue) {
		this.xmlValue = xmlValue;
	}

	public  String getXmlValue() {
		return xmlValue;
	}

	public static Anonymity getFromValue(String xmlValue) {
		Anonymity result = Anonymity.ANONYMOUS;
		for (Anonymity a : Anonymity.values()) {
			if (a.getXmlValue().equals(xmlValue)) {
				result = a;
				break;
			}
		}
		return result;
	}
}
