package br.com.devdojo.examgenerator.persistence.dao;

import java.io.Serializable;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.devdojo.examgenerator.persistence.model.Token;

public class LoginDAO implements Serializable {
	private final String BASE_URL = "http://localhost:8085/login";
	private final RestTemplate restTemplate = new RestTemplate();
	
	public Token loginReturningToken(String username, String password) {
		String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
		ResponseEntity<Token> tokenExchange = restTemplate.exchange(BASE_URL, HttpMethod.POST, new HttpEntity<>(loginJson, createJsonHeader()), Token.class);
		System.out.println(tokenExchange.getBody());
		return tokenExchange.getBody();
	}
	
	private String addQuotes(String value) {
		return new StringBuilder(300).append("\"").append(value).append("\"").toString();
	}
	
	private HttpHeaders createJsonHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}
}
