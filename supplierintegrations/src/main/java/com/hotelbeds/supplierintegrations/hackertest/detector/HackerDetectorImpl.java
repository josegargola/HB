package com.hotelbeds.supplierintegrations.hackertest.detector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import com.hotelbeds.supplierintegrations.hackertest.detector.converters.StringToLogLine;
import com.hotelbeds.supplierintegrations.hackertest.detector.domains.Action;
import com.hotelbeds.supplierintegrations.hackertest.detector.domains.LineLog;
import com.hotelbeds.supplierintegrations.hackertest.detector.services.ServiceLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HackerDetectorImpl implements HackerDetector{

    @Autowired
    private ServiceLogs serviceLogs;

    @Autowired
    private StringToLogLine stringToLogLine;

    private static final int INTENTOS_FALLIDOS_LOGIN = 5;
    private static final long PERIODO_INTENTOS_FALLIDOS_LOGIN = 300000;


    @Override
    public String parseLine(String line) {

        LineLog logLine = stringToLogLine.convert(line);

        String ip = logLine.getIp();


        if(Action.SIGNIN_SUCCESS.equals(logLine.getAction())){
            if (serviceLogs.existeIp(ip)) {
                serviceLogs.eliminarRegistrosIP(ip);
            }
        }else if(Action.SIGNIN_FAILURE.equals(logLine.getAction())){
            serviceLogs.registrarIp(ip, logLine.getFechaMiliSegundos());

            if(serviceLogs.detectarIp(ip, INTENTOS_FALLIDOS_LOGIN, PERIODO_INTENTOS_FALLIDOS_LOGIN)){
                return ip;
            }
        }

        return null;
        
    }

    @Override
    public int diferenciaMinutos(String fecIni, String fecFin) throws ParseException{
        String patron = "EEE, dd MMM yyyy HH:mm:ss Z";

        SimpleDateFormat format = new SimpleDateFormat(patron, Locale.ENGLISH);        
         

        Date fInicial = format.parse(fecIni);
        Date fFinal = format.parse(fecFin);

        long diferencia  = fFinal.getTime() - fInicial.getTime();

        return (int) TimeUnit.MILLISECONDS.toMinutes(diferencia);

    }
    
}
