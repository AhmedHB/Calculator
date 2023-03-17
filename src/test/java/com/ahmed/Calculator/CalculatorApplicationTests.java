package com.ahmed.Calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CalculatorApplicationTests {
	@Autowired
	private WebTestClient client;

	private Calculator calculator = new Calculator();

	@Test
	void contextLoads() {
	}
	@Test
	public void testSum(){
		assertEquals(5, calculator.sum(2,3));
	}

	private static final int SUM_OK = 5;

	@Test
	void getOKSum() {
		getAndVerifySum(3,2, OK)
				.returnResult().getResponseBody().equals(SUM_OK);
	}

	private WebTestClient.BodyContentSpec getAndVerifySum(Integer a, Integer b, HttpStatus expectedStatus) {
		return client.get()
				.uri("/calculate-sum?a=" + a + "&b="+  b)
				.accept(MediaType.valueOf("application/json; charset=utf-8"))
				.exchange()
				.expectStatus().isEqualTo(expectedStatus)
				.expectHeader().contentType("application/json; charset=utf-8")
				.expectBody();
	}

}
