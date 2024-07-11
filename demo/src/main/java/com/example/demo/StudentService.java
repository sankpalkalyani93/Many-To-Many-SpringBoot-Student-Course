package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}
	
	public Student updateStudentById(Long id, Student updatedStudent) {
		updatedStudent.setId(id);
		return studentRepository.save(updatedStudent);
	}

	public void deleteStudentById(Long id) {
		studentRepository.deleteById(id);
	}
	
	@Transactional
	public Student enrollStudentInCourse(Long studentId, Long courseId) {
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student Not Found"));
		Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found"));
		
		student.addCourse(course);
		return studentRepository.save(student);
	}
}
