package com.hotelbeds.supplierintegrations.hackertest.detector.services;

public interface ServiceLogs {

    boolean existeIp(String ip);

    boolean detectarIp(String ip, int conteoFallos,long periodoFallos);

    void registrarIp(String ip, long tiempo);

    void eliminarRegistrosIP(String ip);

    void eliminarTodosRegistros();
    
}
