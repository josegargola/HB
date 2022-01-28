package com.hotelbeds.supplierintegrations.hackertest.detector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class HackerDetectorTest {


    @Autowired
    private HackerDetector hackerDetector;
    
    @Test
    public void diferenciaMinutosTest() throws ParseException{
        int dif =hackerDetector.diferenciaMinutos("Thu, 21 Dec 2000 16:01:07 +0200", "Thu, 21 Dec 2000 16:03:40 +0200");
        assertEquals(2, dif);
    }

    @Test
    public void diferenciaMinutosExceptionTest() throws ParseException{
        Exception excepcion = assertThrows(ParseException.class, () -> {
            hackerDetector.diferenciaMinutos("Thu, 21a Dec 2000 16:01:07 +0200", "Thu, 21 Dec 2000 16:03:40 +0200");
        });

        String mensajeEsperado = "Unparseable date: \"Thu, 21a Dec 2000 16:01:07 +0200\"";
        String mensajeRecibido = excepcion.getMessage();
        assertTrue(mensajeRecibido.contains(mensajeEsperado));

    }

    @Test
    public void parseLineFAILSUCCESSNoDetectaTest(){
        hackerDetector.parseLine("127.1.0.0,125166261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125168261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125169261,SIGNIN_FAILURE,Will.Smith");
        String resultadoDetector=hackerDetector.parseLine("127.1.0.0,125176261,SIGNIN_SUCCESS,Will.Smith");

        assertNull(resultadoDetector);
    }

    @Test
    public void parseLineFAILDetectaTest(){
        hackerDetector.parseLine("127.1.0.0,125166261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125168261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125169261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125179261,SIGNIN_FAILURE,Will.Smith");
        hackerDetector.parseLine("127.1.0.0,125189261,SIGNIN_FAILURE,Will.Smith");
        String resultadoDetector=hackerDetector.parseLine("127.1.0.0,125196261,SIGNIN_FAILURE,Will.Smith");

        assertEquals("127.1.0.0", resultadoDetector);

    }
    
    @Test
    public void parseLineSUCCESSNoExisteIPTest(){

        String resultadoDetector=hackerDetector.parseLine("127.1.0.0,125176261,SIGNIN_SUCCESS,Will.Smith");

        assertNull(resultadoDetector);
    }
    

}
