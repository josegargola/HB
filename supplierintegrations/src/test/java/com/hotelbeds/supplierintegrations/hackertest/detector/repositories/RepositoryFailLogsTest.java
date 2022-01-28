package com.hotelbeds.supplierintegrations.hackertest.detector.repositories;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;

@SpringBootTest
public class RepositoryFailLogsTest {

	@Autowired
	private RepositoryFailLogs repositoryFailLogs;
	
	@Test
	public void noExisteIpTest() {
		repositoryFailLogs.eliminarTodosFailLogs();
		boolean existe = repositoryFailLogs.existeIp("127.1.0.0");
		assertFalse(existe);
	}
	
	@Test
	public void registrarIPNoExistenteExisteTest() {
		repositoryFailLogs.registrarFailLogIp("127.1.0.0", 0);
		boolean existe = repositoryFailLogs.existeIp("127.1.0.0");
		assertTrue(existe);
	}
	
	@Test
	public void registrarIPExistenteExisteTest() {
		repositoryFailLogs.eliminarTodosFailLogs();
		repositoryFailLogs.registrarFailLogIp("127.1.0.0", 0);
		List<Long> tiempo = repositoryFailLogs.obtenerTiemposIp("127.1.0.0");
		assertEquals(1, tiempo.size());
	}
	
	
	@Test
	public void registrarEliminarObtenerIPTest() {
		repositoryFailLogs.eliminarFailLogsIp("127.1.0.0");
		
		Exception exception = assertThrows(HackerDetectorException.class, () -> {
			  repositoryFailLogs.obtenerTiemposIp("127.1.0.0");
	        });
		
        String expectedMessage = "No se encuentran registros de la IP: 127.1.0.0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        
		repositoryFailLogs.registrarFailLogIp("127.1.0.0", 2);
		List<Long> tiempo = repositoryFailLogs.obtenerTiemposIp("127.1.0.0");
		assertEquals(1, tiempo.size());
		
	}
	
	@Test
	public void eliminarTodoTest() {
		repositoryFailLogs.eliminarTodosFailLogs();
		repositoryFailLogs.registrarFailLogIp("127.1.0.0", 10);
		List<Long> tiempo = repositoryFailLogs.obtenerTiemposIp("127.1.0.0");
		assertEquals(1, tiempo.size());
		
		repositoryFailLogs.eliminarTodosFailLogs();
		
		Exception exception = assertThrows(HackerDetectorException.class, () -> {
			  repositoryFailLogs.obtenerTiemposIp("127.1.0.0");
	        });
		
        String expectedMessage = "No se encuentran registros de la IP: 127.1.0.0";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        

		
	}


}
