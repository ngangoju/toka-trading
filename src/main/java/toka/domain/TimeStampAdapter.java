package toka.domain;

import java.sql.Timestamp;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class TimeStampAdapter extends XmlAdapter<String, Timestamp> {
	@Override
	public String marshal(Timestamp v) {

		return v.toString();
	}

	@Override
	public Timestamp unmarshal(String v) {

		return new Timestamp(Long.parseLong(v));
	}
}
