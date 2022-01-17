package br.com.devdojo.examgenerator.bean.login;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.devdojo.examgenerator.custom.CustomURLEncoder;
import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.Token;

@Named
@ViewScoped
public class LoginBean implements Serializable{
	private String username;
	private String password;
	private final LoginDAO loginDAO = new LoginDAO();
	private ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	
	public String login() throws UnsupportedEncodingException {
		Token token = loginDAO.loginReturningToken("william", "devdojo");
		if(token == null) return null;
		addTokenAndExpirationTimeToCookies(token.getToken(), token.getExpirationTime().toString());
		return "index.xhtml?faces-redirect=true";
	}
	
	public String logout() {
		removeTokenAndExpirationTimeFromCookies();
		return "login.xhtml?faces-redirect=true";
	}
	
	private void addTokenAndExpirationTimeToCookies(String token, String expirationTime) {
		externalContext.addResponseCookie("token", CustomURLEncoder.encodeUTF8(token), null);
		externalContext.addResponseCookie("expirationTime", expirationTime, null);
	}
	
	private void removeTokenAndExpirationTimeFromCookies() {
		addTokenAndExpirationTimeToCookies(null, null);
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
