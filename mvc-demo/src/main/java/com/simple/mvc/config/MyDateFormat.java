package com.simple.mvc.config;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class MyDateFormat extends StdDateFormat {

	private DateFormat dateFormat;

	private SimpleDateFormat format1 = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

	public MyDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
		return dateFormat.format(date, toAppendTo, fieldPosition);
	}

	@Override
	public Date parse(String source, ParsePosition pos) {

		Date date;
		try {
			date = format1.parse(source, pos);
		} catch (Exception e) {
			date = dateFormat.parse(source, pos);
		}
		return date;
	}

	// 主要还是装饰这个方法
	@Override
	public Date parse(String source) throws ParseException {
		Date date;
		try {
			// 先按我的规则来
			date = format1.parse(source);
		} catch (Exception e) {
			// 不行，那就按原先的规则吧
			date = dateFormat.parse(source);
		}

		return date;
	}

	@Override
	protected void _format(TimeZone tz, Locale loc, Date date, StringBuffer buffer) {
		super._format(tz, loc, date, buffer);
	}

	// 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
	@Override
	public MyDateFormat clone() {
		Object format = dateFormat.clone();
		return new MyDateFormat((DateFormat) format);
	}
}