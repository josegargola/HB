package com.hotelbeds.supplierintegrations.hackertest.detector.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;

import org.springframework.stereotype.Repository;

@Repository
public class RepositoryFailLogsImpl implements RepositoryFailLogs {

    private Map<String, List<Long>> fallos = new HashMap<String, List<Long>>(100000, 0.9F);
    
    @Override
    public boolean existeIp(String ip) {
        return fallos.containsKey(ip);
    }

    @Override
    public List<Long> obtenerTiemposIp(String ip) {
        if(existeIp(ip)) return fallos.get(ip);
        throw new HackerDetectorException("No se encuentran registros de la IP: " + ip);
    }

    @Override
    public void registrarFailLogIp(String ip, long tiempo) {
        if(existeIp(ip)){
            fallos.get(ip).add(tiempo);
        }else{
        List<Long> tiempos = new ArrayList<Long>();
        tiempos.add(tiempo);

        fallos.put(ip, tiempos);
        }
        
    }

    @Override
    public List<Long> eliminarFailLogsIp(String ip) {
        return fallos.remove(ip);
        
    }

    @Override
    public void eliminarTodosFailLogs() {
        fallos.clear();
        
    }
    
}
