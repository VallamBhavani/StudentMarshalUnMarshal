package com.nendrasys.dao;

import com.nendrasys.model.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    JdbcTemplate template;

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> list = getTemplate().query("SELECT * FROM students", new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                return student;
            }
        });
        return list;
    }

    @Override
    public Student getStudentById(int id) {
        Student student = (Student) getTemplate().queryForObject("SELECT * FROM students where id=?", new BeanPropertyRowMapper(Student.class), id);
        System.out.println(student.getAge() + ":" + student.getId());
        return student;
    }
}
