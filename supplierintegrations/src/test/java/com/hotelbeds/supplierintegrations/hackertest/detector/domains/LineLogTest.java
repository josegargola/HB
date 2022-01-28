package com.hotelbeds.supplierintegrations.hackertest.detector.domains;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LineLogTest {

	@Test
	public void lineLogTest() {
		LineLog esperado = new LineLog("127.1.0.0", 1, Action.SIGNIN_FAILURE, "prueba");
		LineLog linealog = new LineLog(null, 0, null, null);
		
		linealog.setIp("127.1.0.0");
		linealog.setAction(Action.SIGNIN_FAILURE);
		linealog.setFechaMiliSegundos(1);
		linealog.setUsername("prueba");
		
		
		assertEquals(esperado.getIp(), linealog.getIp());
		assertEquals(esperado.getAction(), linealog.getAction());		
		assertEquals(esperado.getFechaMiliSegundos(), linealog.getFechaMiliSegundos());
		assertEquals(esperado.getUsername(), linealog.getUsername());
		
		assertTrue(esperado.equals(linealog));
		assertEquals(esperado, linealog);
		assertEquals(linealog, linealog);
		assertNotEquals(esperado, "");
		

		linealog.setUsername("test");
		assertNotEquals(esperado, linealog);
		linealog.setIp("127.1.0.1");
		assertNotEquals(esperado, linealog);
		linealog.setFechaMiliSegundos(127);
		assertNotEquals(esperado, linealog);
		linealog.setAction(Action.SIGNIN_SUCCESS);
		assertNotEquals(esperado, linealog);
	}

}
