package br.com.devdojo.examgenerator.bean.course;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.com.devdojo.examgenerator.persistence.dao.CourseDAO;
import br.com.devdojo.examgenerator.persistence.model.Course;

@Named
@ViewScoped
public class CourseListBean implements Serializable {
	private final CourseDAO courseDAO = new CourseDAO();
	private List<Course> courseList;
	private String name;
	
	@PostConstruct
	public void init() {
		courseList = courseDAO.list("");
	}
	
	public void search() {
		courseList = courseDAO.list(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}	
}
