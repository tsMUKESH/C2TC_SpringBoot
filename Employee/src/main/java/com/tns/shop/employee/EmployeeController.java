package com.tns.shop.employee;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	//Retrieve All Employee
	@GetMapping("/emp")
	public List<Employee> list(){
		return service.listAll();
	}
	
	//Retrieve Employee by Id
	@GetMapping("/emp/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id){
		 try {
			Employee emp = service.get(id);
			 return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		} catch (Exception e) {
			 return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Create Employee
	@PostMapping("/emp")
	public void add(@RequestBody Employee emp) {
		service.save(emp);
	}
	
	//Update Employee
	@PutMapping("/emp/{id}")
	public ResponseEntity<?> update(@RequestBody Employee emp, @PathVariable Integer id){
		try {
			@SuppressWarnings("unused")
			Employee existEmp = service.get(id);
			service.save(emp);
			return new ResponseEntity<> (HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete Employee
	@DeleteMapping("/emp/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
}