package com.cognizant.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cognizant.model.Student;
import com.cognizant.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService = new StudentService();


	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value="insert" ,method=RequestMethod.GET)
	public String insertPage() {	
		
		return "insert";
		
	}
	
	@RequestMapping(value="insert" ,method=RequestMethod.POST)
	public String insert(HttpServletRequest request) {
		//Read the request parameter
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		//Create the student
		Student student=new Student(id, name);
		
		System.out.println(student);
		//insert student to db
		String result = studentService.insert(student);
		if(result.equals("SUCCESS")) {
			request.setAttribute("msg", "Record Inserted");
		}else {
			request.setAttribute("msg", "Record Not Inserted");
		}
		
		return "insert";
	}
	
	
	
	@RequestMapping(value="update" ,method=RequestMethod.GET)
	public String update() {
		return "update";
	}
	
	@RequestMapping(value="update" ,method=RequestMethod.POST)
	public String update(HttpServletRequest request) {
		//Read the request parameter
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		//Create the student
		Student student=new Student(id, name);
		String result= studentService.update(student);
		System.out.println(student);
		//insert student to db
		if(result.equals("SUCCESS"))
			request.setAttribute("msg", "Record Updated");
		else
			request.setAttribute("msg", "Record Not Updated");
		return "update";
	}
	
	@RequestMapping(value="delete" ,method=RequestMethod.GET)
	public String delete() {
		return "delete";
	}
	
	@RequestMapping(value="delete" ,method=RequestMethod.POST)
	public String delete(HttpServletRequest request) {
		//Read the request parameter
		String id=(request.getParameter("id"));
		//Create the student
		Student student=new Student(id);
		String str= studentService.delete(student);
		
		//insert student to db
		
		if(str.equals("SUCCESS")){
			request.setAttribute("msg", "Record Deleted");
		}else {
			request.setAttribute("msg", "Record Not Deleted");
		}
		return "delete";
	}
	
	@RequestMapping(value="getall" ,method=RequestMethod.GET)
	public String getAll(HttpServletRequest request) {
		List<Student> list= studentService.getAll();
		request.setAttribute("List", list);
		return "display";
	}

	
}
