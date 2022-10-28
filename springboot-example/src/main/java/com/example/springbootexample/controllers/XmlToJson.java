package com.example.springbootexample.controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootexample.exception.ResourceNotFoundException;
import com.example.springbootexample.model.Student;
import com.example.springbootexample.model.User;
import com.example.springbootexample.repository.StudentRepository;
import com.example.springbootexample.repository.UserRepository;
import com.example.springbootexample.repository.UserprofileRepository;

@RestController
@RequestMapping("/api")
public class XmlToJson {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserprofileRepository userprofileRepo;

	public XmlToJson() {
	}

	@GetMapping("/xmlfileinputjsonfileoutput")
	public String xmlfileinputjsonfileoutput() throws IOException {
		Reader stringXml = new FileReader("src/test/resources/xmlFile1.xml");
		JSONObject json = XML.toJSONObject(stringXml);
		System.out.println(json.toString(4));
		FileWriter file1 = new FileWriter("src/test/resources/jsonOutput.json");
		file1.write(json.toString(4));
		file1.flush();
		file1.close();
		return "The program ran and converted XML file to JSON File";
	}

	// get all students
	@GetMapping("/getstudents")
	public List<Student> getAllStudents() {
		return this.studentRepo.findAll();
	}

	// get students by id
	@GetMapping("/getstudents/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable(value = "id") Integer studentId)
			throws ResourceNotFoundException {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id " + studentId));
		return ResponseEntity.ok().body(student);
	}

	// save student
	@PostMapping("/savestudents")
	public Student createStudent(@RequestBody Student student) {
		return this.studentRepo.save(student);
	}

	// update student
	@PutMapping("/updatestudents/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable(value = "id") Integer studentId,
			@RequestBody Student studentDetails) throws ResourceNotFoundException {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id " + studentId));
		student.setEmail(studentDetails.getEmail());
		student.setName(studentDetails.getName());
		return ResponseEntity.ok(this.studentRepo.save(student));

	}

	// delete student
	@DeleteMapping("/deletestudents/{id}")
	public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Integer studentId)
			throws ResourceNotFoundException {
		Student student = studentRepo.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not found for this id " + studentId));
		this.studentRepo.delete(student);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted", Boolean.TRUE);
		return response;
	}

	// OneToOne bidirectional mapping -> Save change in anyone will at same time
	// save change in other table
	// save user
	@PostMapping("/saveusers")
	public User createUser(@RequestBody User user) {
		return this.userRepo.save(user);
	}

}