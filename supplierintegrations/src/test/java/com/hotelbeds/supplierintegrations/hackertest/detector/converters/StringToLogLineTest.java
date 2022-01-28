package com.hotelbeds.supplierintegrations.hackertest.detector.converters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.hotelbeds.supplierintegrations.hackertest.detector.domains.Action;
import com.hotelbeds.supplierintegrations.hackertest.detector.domains.LineLog;
import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StringToLogLineTest {


	@Autowired
    private StringToLogLine stringToLogLine;

    @Test
    public void conversionTest(){

        LineLog expected = new LineLog("127.1.0.0", 125166261, Action.SIGNIN_SUCCESS, "Will.Smith");
        
        LineLog conversion = stringToLogLine.convert("127.1.0.0,125166261,SIGNIN_SUCCESS,Will.Smith");

        assertEquals(expected, conversion);
    }

    
    @Test 
    public void conversionExceptionVacioTest(){
        Exception exception = assertThrows(HackerDetectorException.class, () -> {
            stringToLogLine.convert("");
        });

        String expectedMessage = "Linea de log vacia";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test 
    public void conversionExceptionIncompletoTest(){
        Exception exception = assertThrows(HackerDetectorException.class, () -> {
            stringToLogLine.convert("127.1.0.0,125166261");
        });

        String expectedMessage = "Linea de log incompleta o incorrecta";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    
}
