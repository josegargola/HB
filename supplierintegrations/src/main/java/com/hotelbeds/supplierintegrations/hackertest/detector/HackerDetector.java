package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.text.ParseException;

public interface HackerDetector {
    String parseLine(String line);

    int diferenciaMinutos(String fecIni, String fecFin) throws ParseException;
    
}
