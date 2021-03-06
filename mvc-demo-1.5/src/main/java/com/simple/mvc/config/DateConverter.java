package com.simple.mvc.config;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;


@Configuration
public class DateConverter implements Converter<String, Date> {
    private static final String[] FORMAT =
            new String[] { "yyyyMMdd", "yyyy-MM-dd", "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss+0000", "EEE MMM dd HH:mm:ss zzz yyyy" };


    @Override
    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source, Locale.US, FORMAT);
        } catch (ParseException e) {
            throw new IllegalArgumentException(source + " parse Date error ,format " + Arrays.toString(FORMAT));
        }
    }

}
