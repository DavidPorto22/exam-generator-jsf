package br.com.devdojo.examgenerator.bean.course;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;

@Named
@ViewScoped
public class CourseEditBean implements Serializable {
	private CourseDAO courseDAO = new CourseDAO();
	private long id;
	private Course course;
	
	public void init() {
		course = courseDAO.findById(id);
	}

	public String update() {
		courseDAO.update(course);
		Messages.create("The course {0} was successfully updated", course.getName()).flash().add();
		return "list.xhtml?faces-redirect=true";
	}
	
	public String delete() {
		courseDAO.delete(id);
		Messages.create("The course {0} was successfully deleted", course.getName()).flash().add();
		return "list.xhtml?faces-redirect=true";
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
	
}
