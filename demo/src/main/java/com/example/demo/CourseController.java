package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping
	public ResponseEntity<Course> createCourse(@RequestBody Course course){
		courseService.createCourse(course);
		return new ResponseEntity<>(course, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Course>> getAllCourses(){
		List<Course> courseList = courseService.getAllCourses();
		return new ResponseEntity<>(courseList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable Long id){
		Optional<Course> course = courseService.getCourseById(id);
		return course.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Course> updateCourseById(@PathVariable Long id, @RequestBody Course course) {
		Course updatedCourse = courseService.updateCourseById(id, course);
		if(updatedCourse != null) {
			return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build(); 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
		courseService.deleteCourseById(id);
		return ResponseEntity.noContent().build();
	}
}