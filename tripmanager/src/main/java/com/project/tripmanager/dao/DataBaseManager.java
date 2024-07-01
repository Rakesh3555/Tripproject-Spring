package com.project.tripmanager.dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseManager {
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	 public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	        this.jdbcTemplate = jdbcTemplate;
	    }

    public void registerUser(String username, String mail, String mobileNumber, String password) {
        String insertUserCred = "INSERT INTO UserCredentials(username, mail, mobileNumber, password, verification) VALUES (?, ?, ?, ?, 'Not Verified')";
        System.out.println(username);
        Object[] params = {username, mail, mobileNumber, password};
        jdbcTemplate.update(insertUserCred, params);
        
    }

    public void updateVerificationStatus(String username) {
        String updateVerified = "UPDATE UserCredentials SET verification = 'Verified' WHERE username = ?";
        jdbcTemplate.update(updateVerified, username);
    }
}
