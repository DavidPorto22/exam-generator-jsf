package br.com.devdojo.examgenerator.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.devdojo.examgenerator.custom.CustomRestTemplate;
import br.com.devdojo.examgenerator.custom.CustomTypeReference;
import br.com.devdojo.examgenerator.persistence.model.Course;
import br.com.devdojo.examgenerator.util.ApiUtil;
import br.com.devdojo.examgenerator.util.JsonUtil;

public class CourseDAO implements Serializable {
	private final String LIST_URL = ApiUtil.BASE_URL + "/professor/course/list";
	private final String DELETE_FIND_BY_ID_URL = ApiUtil.BASE_URL + "/professor/course/{id}";
	private final String CREATE_UPDATE_URL = ApiUtil.BASE_URL + "/professor/course";
	private final CustomRestTemplate restTemplate = new CustomRestTemplate();
	private JsonUtil jsonUtil = new JsonUtil();
	private CustomTypeReference<List<Course>> listCourse = new CustomTypeReference<List<Course>>();
	
	public List<Course> list(String name) {
		UriComponents url = UriComponentsBuilder.fromUriString(LIST_URL).queryParam("name", name).build();
		ResponseEntity<List<Course>> exchange = restTemplate.exchange(url.toUriString(), HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), listCourse.typeReference());
		return exchange.getBody();
	}
	
	public Course findById(long id) {
		return restTemplate.exchange(DELETE_FIND_BY_ID_URL, HttpMethod.GET, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id).getBody();
	}
	
	public Course update(Course course) {
		return createOrUpdate(HttpMethod.PUT, course);
	}
	
	public Course create(Course course) {
		return createOrUpdate(HttpMethod.POST, course);
	}
	
	private Course createOrUpdate(HttpMethod httpMethod, Course course) {
		return restTemplate.exchange(CREATE_UPDATE_URL, httpMethod, jsonUtil.tokenizedHttpEntityHeader(course), Course.class).getBody();
	}
	
	public void delete(long id) {
		restTemplate.exchange(DELETE_FIND_BY_ID_URL, HttpMethod.DELETE, jsonUtil.tokenizedHttpEntityHeader(), Course.class, id);
	}
}















