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
    
//    public void malasiyaTripFamilyPackage(String username , String phoneNumber) {
//    	
//    	String malFamPackage = "insert into userTripDetails(username , mobileNumber , Package ,Destination, Package_Type ) values ('"+username+"' , '"+phoneNumber+"' , 'International Trip' , ' Malaysia ', 'Family Package');";
//    	jdbcTemplate.update(malFamPackage);
//    }
//    
//    public void malasiyaTripFriendsPackage(String username , String phoneNumber) {
//    	
//    	String malFriPackage = "insert into userTripDetails(username , mobileNumber , Package ,Destination, Package_Type ) values ('"+username+"' , '"+phoneNumber+"' , 'International Trip' , ' Malaysia ', 'Friends Package');";
//    	jdbcTemplate.update(malFriPackage);
//    }
//    
//    public void malasiyaTripGroupPackage() {
//    	
//    }
    
    public void malasiyaTripPackage(String username , String phoneNumber , String packageName) {
    	
    	String malasiyaTripPackageUpdater = "insert into userTripDetails(username , mobileNumber , Package ,Destination, Package_Type ) values ('"+username+"' , '"+phoneNumber+"' , 'International Trip' , ' Malaysia ', '"+packageName+"');";
    	jdbcTemplate.update(malasiyaTripPackageUpdater);
    }
    
//    public String bookingTitleUpdater(String phoneNumber) {
//    	
//    	String bookingTitleUpdater = "select Package_Type from userTripDetails where mobileNumber = ?";
//    	return jdbcTemplate.queryForObject(bookingTitleUpdater,String.class,phoneNumber);
//    	
//    }
    
    public String retriveDestination(String username) {
    	
    	String Destination = "select Destination from userTripDetails where username = '?'";
    	
    	return jdbcTemplate.queryForObject(Destination,String.class,username);
    }
    
    public void addTravellers(String username , String mobileNumber , String travelerName , String travelerAge , String travelerGender , String destination) {
    	
        String addTravellerInfo = "INSERT INTO travellerinformantion (username, mobilenumber, Destination, Travellers, Gender, Age) VALUES ('"+username+"','"+mobileNumber+"','"+destination+"','"+travelerName+"','"+travelerGender+"','"+travelerAge+"');";
        jdbcTemplate.update(addTravellerInfo);                    

    }
}
