package com.cognizant.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.cognizant.model.Student;

@Service
@Repository("Student")
public class StudentService {
	@Autowired
	private JdbcTemplate jdbcTemplate;

//	private List<Student> stList = new ArrayList<Student>(
//			Arrays.asList(new Student("1", "Anuj"), new Student("2", "Kanishka"), new Student("3", "Pabitra")));

	public String insert(Student student) {
//		for (Student st : stList) {
//			if (st.getId()==student.getId()) {
//				return "FAILED";
//			}
//		}
//		if (stList.add(student)) {
//			return "SUCCESS";
//		} else {
//			return "FAILED";
//		}
		String sqlInsert = "insert into student values(?,?)";
		int result = jdbcTemplate.update(sqlInsert, student.getId(), student.getName());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}

	public String update(Student student) {
//		for (Student s1 : stList) {
//			if (s1.getId()==student.getId()) {
//				s1.setName(student.getName());
//				return "SUCCESS";
//			}
//		}
//		return "FAILED";
		String sqlUpdate = "update student set name = ? where id = ?";
		int result = jdbcTemplate.update(sqlUpdate,  student.getName(), student.getId());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}

	public String delete(Student student) {
//		for (Student s1 : stList) {
//			if (s1.getId()==student.getId()) {
//				stList.remove(s1);
//				return "SUCCESS";
//			}
//		}
//		return "FAILED";
		String sqlDelete = "delete from student where id = ?";
		int result = jdbcTemplate.update(sqlDelete, student.getId());
		if(result == 1) {
			return "SUCCESS";
		}else {
			return "FAILED";
		}
	}


	public List<Student> getAll() {
		String sql = "select * from student";
		
		//Studentrowmapper rmp = new Studentrowmapper();
		List<Student> students = jdbcTemplate.query(sql, (rs, rownum)->
									new Student(rs.getString(1), rs.getString(2)));
		return students;
		
		//		
		//System.out.println(stList);
//		return stList;
//		List<Student> students = jdbcTemplate.query("SELECT * FROM student", new RowMapper<Student>() {
//			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Student student = new Student();
//				student.setId(rs.getString("id"));
//				student.setName(rs.getString("name"));
//				return student;
//			}});
//		return students;
		
	}

}
