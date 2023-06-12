package com.mezza.app.components;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTime implements Converter<String, Date> {

    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    @Override
    public Date convert(String hora) {
        try {
            return format.parse(hora);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formato de tiempo inválido: " + hora);
        }
    }
}