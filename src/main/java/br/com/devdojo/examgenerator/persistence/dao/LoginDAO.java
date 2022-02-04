package br.com.devdojo.examgenerator.persistence.dao;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import br.com.devdojo.examgenerator.custom.CustomObjectMapper;
import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.persistence.model.support.ErrorDetail;
import br.com.devdojo.examgenerator.persistence.model.support.Token;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;

public class LoginDAO implements Serializable {
	private final CustomRestTemplate restTemplate = new CustomRestTemplate();
	private final ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	private final JsonUtil jsonUtil = new JsonUtil();
	
	
	public Token loginReturningToken(String username, String password) {
		String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
		try {
			ResponseEntity<Token> tokenExchange = restTemplate.exchange(ApiUtil.LOGIN_URL, HttpMethod.POST, new HttpEntity<>(loginJson, jsonUtil.createJsonHeader()), Token.class);
			return tokenExchange.getBody();			
		} catch(HttpClientErrorException e) {
			try {
				ErrorDetail errorDetail = new CustomObjectMapper().readValue(e.getResponseBodyAsString(), ErrorDetail.class);
				errorDetail.setMessage("Authentication Failed: Bad credentials");
				addMessage(FacesMessage.SEVERITY_ERROR, errorDetail.getMessage(), true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	private void addMessage(FacesMessage.Severity severity, String msg, boolean keepMessage) {
		FacesMessage facesMessage = new FacesMessage(severity, msg, "");
		externalContext.getFlash().setKeepMessages(keepMessage);
		externalContext.getFlash().setRedirect(true);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	private String addQuotes(String value) {
		return new StringBuilder(300).append("\"").append(value).append("\"").toString();
	}
	
	
}
