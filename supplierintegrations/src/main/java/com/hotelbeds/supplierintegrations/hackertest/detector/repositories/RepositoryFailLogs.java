package com.hotelbeds.supplierintegrations.hackertest.detector.repositories;

import java.util.List;

public interface RepositoryFailLogs {

    boolean existeIp(String ip);

    List<Long> obtenerTiemposIp(String ip);

    void registrarFailLogIp(String ip, long tiempo);

    List<Long> eliminarFailLogsIp(String ip);

    void eliminarTodosFailLogs();
}
