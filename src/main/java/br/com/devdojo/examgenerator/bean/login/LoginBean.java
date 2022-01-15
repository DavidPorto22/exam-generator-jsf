package br.com.devdojo.examgenerator.bean.login;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.Token;

public class LoginBean {
	private String username;
	private String password;
	private final LoginDAO loginDAO = new LoginDAO();
	private ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	public String login() throws UnsupportedEncodingException {
		Token token = loginDAO.loginReturningToken("william", "devdojo");
		return token == null ? null : addTokenAndExpirationTimeToCookiesAndReturnIndex(token);
	}
	
	private String addTokenAndExpirationTimeToCookiesAndReturnIndex(Token token) throws UnsupportedEncodingException{
		externalContext.addResponseCookie("token", URLEncoder.encode(token.getToken(), "UTF-8"), null);
		externalContext.addResponseCookie("expirationTime", token.getExpirationTime().toString(), null);
		return "index.xhtml?faces-redirect=true";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
