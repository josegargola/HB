package com.hotelbeds.supplierintegrations.hackertest.detector.converters;

import com.hotelbeds.supplierintegrations.hackertest.detector.domains.Action;
import com.hotelbeds.supplierintegrations.hackertest.detector.domains.LineLog;
import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;
import com.google.common.base.Strings;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLogLine implements Converter<String,LineLog> {

    @Override
    public LineLog convert(String logline) {
        if(Strings.isNullOrEmpty(logline)){
            throw new HackerDetectorException("Linea de log vacia");
        }
        String []datalogline = logline.split(",");
        
        if (datalogline.length != 4) {
           throw new HackerDetectorException("Linea de log incompleta o incorrecta");
        }

        return new LineLog(datalogline[0], Long.parseLong(datalogline[1]), Action.valueOf(datalogline[2]), datalogline[3]);
    }
    
}
