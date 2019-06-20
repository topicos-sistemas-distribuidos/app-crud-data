package com.myapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AppTest {

	@Test
	public void contextLoads() {
	}
		
	@Test
	public void TestListaConvidados() {
		assertEquals(0, 0);
	}
	
	@Test
	public void TestInsereConvidado() {
		assertEquals(0, 0);
	}
	
	@Test
	public void TestAlteraConvidado() {
		assertEquals(0, 0);
	}
	
	@Test
	public void TestDeletaConvidado() {
		assertEquals(0, 1);
	}
	
	@Test
	public void TestBuscaConvidado() {
		assertEquals(0, 1);
	}

}
