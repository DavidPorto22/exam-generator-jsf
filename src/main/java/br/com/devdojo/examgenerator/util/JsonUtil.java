package br.com.devdojo.examgenerator.util;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import br.com.devdojo.examgenerator.custom.CustomURLEncoderDecoder;

public class JsonUtil implements Serializable{
	private Map<String, Object> cookieMap = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
	
	public HttpHeaders createJsonHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		return header;
	}
	
	public HttpHeaders createTokenizedHeader() {
		HttpHeaders header = createJsonHeader();
		Cookie tokenCookie = (Cookie) cookieMap.get("token");
		header.add("Authorization", CustomURLEncoderDecoder.decodeUTF8(tokenCookie.getValue()));
		return header;
	}
	
	public HttpEntity<?> tokenizedHttpEntityHeader() {
		return new HttpEntity<>(createTokenizedHeader());
	}
	
	public <E> HttpEntity<E> tokenizedHttpEntityHeader(E e) {
		return new HttpEntity<>(e, createTokenizedHeader());
	}
}
