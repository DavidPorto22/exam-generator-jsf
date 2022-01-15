import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.model.Token;

@Named
@ViewScoped
public class IndexBean implements Serializable{
	private String message = "Wooooooooorking";
	private final LoginDAO loginDAO = new LoginDAO();
	
	public void login() {
		Token token = loginDAO.loginReturningToken("william", "devdojo");
		System.out.println(token);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
