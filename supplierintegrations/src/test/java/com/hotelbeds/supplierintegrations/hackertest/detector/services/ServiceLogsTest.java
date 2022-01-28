package com.hotelbeds.supplierintegrations.hackertest.detector.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotelbeds.supplierintegrations.hackertest.detector.exceptions.HackerDetectorException;

@SpringBootTest
public class ServiceLogsTest {

	@Autowired
	private ServiceLogs serviceLogs;
	
	@Test
	public void existeIpTest() {
		serviceLogs.eliminarTodosRegistros();
		boolean existe = serviceLogs.existeIp("127.1.0.0");
		assertFalse(existe);
	}
	
	@Test
	public void registrarEliminarIpTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		boolean existe = serviceLogs.existeIp("127.1.0.0");
		assertTrue(existe);
		
	}
	
	@Test
	public void eliminarIpTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		boolean existe = serviceLogs.existeIp("127.1.0.0");
		assertTrue(existe);
		serviceLogs.eliminarRegistrosIP("127.1.0.0");
		boolean noexiste = serviceLogs.existeIp("127.1.0.0");
		assertFalse(noexiste);
		
	}
	
	@Test
	public void eliminarTodoTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		boolean existe = serviceLogs.existeIp("127.1.0.0");
		assertTrue(existe);
		serviceLogs.eliminarTodosRegistros();
		boolean noexiste = serviceLogs.existeIp("127.1.0.0");
		assertFalse(noexiste);
		
	}
	
	@Test
	public void detectarIpNoConteoTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		
		boolean detectar = serviceLogs.detectarIp("127.1.0.0", 5, 1);
		
		assertFalse(detectar);
		
	}
	
	@Test
	public void detectarIpConteoNoPeriodoTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		serviceLogs.registrarIp("127.1.0.0", 6);
		
		boolean detectar = serviceLogs.detectarIp("127.1.0.0", 2, 5);
		
		assertFalse(detectar);
		
	}
	
	@Test
	public void detectarIpTest() {
		serviceLogs.registrarIp("127.1.0.0", 0);
		serviceLogs.registrarIp("127.1.0.0", 5);
		
		boolean detectar = serviceLogs.detectarIp("127.1.0.0", 1, 5);
		
		assertTrue(detectar);
		
	}

	
	@Test
	public void detectarIpExceptionTest() {
		serviceLogs.eliminarTodosRegistros();	
		boolean detectar = serviceLogs.detectarIp("127.1.0.0", 1, 5);
		
		assertFalse(detectar);
		
	}
}
