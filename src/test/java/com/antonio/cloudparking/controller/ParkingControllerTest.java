package com.antonio.cloudparking.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.antonio.cloudparking.controller.dto.ParkingCreateDto;

import io.restassured.RestAssured;

//TESTE DE INTEGRAÇÃO CONTÍNUA, NÃO USA MOCK, POIS TESTA NA PRÓPRIA API.
//IMPORTANTE RODAR EM PORTA RANDOM, PARA NAO CORRER O RISCO DE TENTAR PORTA JÁ OCUPADA.

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

	@LocalServerPort
	private int randomPort;
	
	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult() {
		RestAssured.given()
		.when()
		.get("/parking")
		.then()
		//.extract().response().prettyPrint() //AQUI MOSTRO A RESPOSTA DA API NA TELA
		.statusCode(HttpStatus.OK.value())
		.body("license[0]", Matchers.equalTo("DMS-1111"));
	}

	@Test
	void testCreate() {
		var parkingDto = new ParkingCreateDto();
		parkingDto.setColor("AMARELO");
		parkingDto.setLicense("BZL-1254");
		parkingDto.setModel("UNO");
		parkingDto.setState("MG");
		
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(parkingDto)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("license", Matchers.equalTo("BZL-1254"))
		.body("color", Matchers.equalTo("AMARELO"))
		.body("model", Matchers.equalTo("UNO"))
		.body("state", Matchers.equalTo("MG"));


	}
		

}
