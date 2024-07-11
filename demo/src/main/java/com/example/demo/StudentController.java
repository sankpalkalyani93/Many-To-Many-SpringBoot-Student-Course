package com.example.demo;

import java.util.ArrayList;
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
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student stduent){
		studentService.createStudent(stduent);
		return new ResponseEntity<>(stduent, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> studentList = studentService.getAllStudents();
		return new ResponseEntity<>(studentList, HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		Optional<Student> student = studentService.getStudentById(id);
		return student.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudentById(@PathVariable Long id, @RequestBody Student student){
		Student updatedStudent = studentService.updateStudentById(id, student);
		if(updatedStudent != null) {
			return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/{studentId}/enroll/{courseId}")
	public ResponseEntity<Student> enrollStudentInCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
		Student student = studentService.enrollStudentInCourse(studentId, courseId);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}
}