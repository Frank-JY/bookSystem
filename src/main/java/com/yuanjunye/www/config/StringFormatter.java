package com.yuanjunye.www.config;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class StringFormatter implements Formatter<Long>{

	@Override
	public String print(Long object, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long parse(String text, Locale locale) throws ParseException {
		if(!"".equals(text)) {
			return Long.valueOf(text);
		}
		return null;
	}

	

}
