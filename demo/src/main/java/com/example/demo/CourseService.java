package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	
	public Course createCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public List<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public Optional<Course> getCourseById(Long id){
		return courseRepository.findById(id);
	}
	
	public Course updateCourseById(Long id, Course updatedCourse) {
		updatedCourse.setId(id);
		return courseRepository.save(updatedCourse);
	}
	
	public void deleteCourseById(Long id) {
		courseRepository.deleteById(id);
	}
}
