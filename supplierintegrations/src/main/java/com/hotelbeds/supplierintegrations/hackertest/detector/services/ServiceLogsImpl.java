package com.hotelbeds.supplierintegrations.hackertest.detector.services;

import java.util.List;

import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;
import com.hotelbeds.supplierintegrations.hackertest.detector.repositories.RepositoryFailLogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceLogsImpl implements ServiceLogs {

    @Autowired
    private RepositoryFailLogs repositoryFailLogs;

    @Override
    public boolean existeIp(String ip) {
        return repositoryFailLogs.existeIp(ip);
    }

    @Override
    public boolean detectarIp(String ip, int conteoFallos, long periodoFallos) {
        try{
            List<Long> tiemposFallosIP = repositoryFailLogs.obtenerTiemposIp(ip);

            if(tiemposFallosIP.size() < conteoFallos){
                return false;
            }

            long tiempoUltimoFallo = tiemposFallosIP.get(tiemposFallosIP.size()-1);
            long tiempoPrimerFallo = tiemposFallosIP.get(tiemposFallosIP.size() - conteoFallos);
            long intervalo = tiempoUltimoFallo - tiempoPrimerFallo;

            if (intervalo < periodoFallos) {
                return true;
            }

        }catch (HackerDetectorException e){
        }

        return false;
    }

    @Override
    public void registrarIp(String ip, long tiempo) {
        repositoryFailLogs.registrarFailLogIp(ip, tiempo);
        
    }

    @Override
    public void eliminarRegistrosIP(String ip) {
        repositoryFailLogs.eliminarFailLogsIp(ip);
        
    }

    @Override
    public void eliminarTodosRegistros() {
        repositoryFailLogs.eliminarTodosFailLogs();
        
    }
    
}
