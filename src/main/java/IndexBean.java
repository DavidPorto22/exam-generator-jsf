import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.devdojo.examgenerator.persistence.dao.LoginDAO;
import br.com.devdojo.examgenerator.persistence.dao.ProfessorDAO;
import br.com.devdojo.examgenerator.persistence.model.Professor;
import br.com.devdojo.examgenerator.persistence.model.support.Token;

@Named
@ViewScoped
public class IndexBean implements Serializable{
	private String message = "Wooooooooorking";
	private final LoginDAO loginDAO = new LoginDAO();
	private final ProfessorDAO professorDAO = new ProfessorDAO();
	
//	public void login() {
//		Token token = loginDAO.loginReturningToken("william", "devdojo");
//		System.out.println(token);
//	}
	
	public String getMessage() {
		return message;
	}
	
	public void checkProfessor() {
		Professor professor = professorDAO.getProfessorById();
		System.out.println(professor.getName());
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
