package com.project.tripmanager.controller;

import com.project.tripmanager.dao.DataBaseManager;
import com.project.tripmanager.dao.Mail;
import com.project.tripmanager.model.Trip;
import com.project.tripmanager.model.User;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.mail.MessagingException;

@Controller
public class MyController {

    @Autowired
    public DataBaseManager DataBaseManager;

    @Autowired
    public Mail mailService;

    @RequestMapping("/")
    public String landingPage() {
        return "Home.jsp"; 
    }

    @PostMapping("/UserValidation")
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        User userCredentials = new User();
        userCredentials.setUsername(username);
        userCredentials.setPassword(password);

       

        return "redirect:/"; 
    }

    @PostMapping("/Registration")
    public String registerUser(HttpSession session,
                               @RequestParam("username") String username,
                               @RequestParam("mobileNumber") String mobileNumber,
                               @RequestParam("mail") String mail,
                               @RequestParam("password") String password,
                               Model model) throws MessagingException {

        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("mail", mail);
        session.setAttribute("mobileNumber", mobileNumber);

        User userCredentials = new User();
        
        userCredentials.setUsername(username);
        userCredentials.setPassword(password);
        userCredentials.setMail(mail);
        userCredentials.setMobileNumber(mobileNumber);
        
       
        DataBaseManager.registerUser(username, mail, mobileNumber, password);
        
        int generateOTP = (int) ((Math.random()*(999999 - 100000))+100000);
        
        userCredentials.setGeneraterOtp(String.valueOf(generateOTP));
        
        Mail mailGeneration = new Mail();
        mailGeneration.setupServerProperties();
        
        MimeMessage message = mailGeneration.draftEmail(mail,userCredentials.getGeneraterOtp());
        mailGeneration.sendEmail(message);
        session.setAttribute("otp", generateOTP);
        
        return "VerifyMail.jsp"; 
    }

    @PostMapping("/ValidateOtp")
    public String validateOtp(HttpSession session , @RequestParam("valOtp") String valOtp) {
        String generatedOTP = String.valueOf(session.getAttribute("otp"));
        String username = (String) session.getAttribute("username");
        session.getAttribute(valOtp);
        if (generatedOTP.equals(valOtp)) {
            try {
            	DataBaseManager.updateVerificationStatus(username);
            } catch (Exception e) {
                
                e.printStackTrace();
            }
        } else {
            
            return "ErrorPage"; 
        }

        return "HomeWprofile.jsp"; 
    }
    
    @PostMapping("/MalaysiaPackage")
    public String malasiyaTripFamilyPackage(HttpSession session, @RequestParam("action") String packageType ) {
    	
    	String username = (String) session.getAttribute("username");
    	String mobileNumber = (String) session.getAttribute("mobileNumber");
    	
    	if(packageType.equals("familypackage")) {
    		String packageName = "Family Package";
    		session.setAttribute("packageName", packageName);
    		DataBaseManager.malasiyaTripPackage(username, mobileNumber, packageName);
    		System.out.println(packageName);
    	}else if(packageType.equals("friendspackage")) {
    		String packageName = "Friends Package";
    		session.setAttribute("packageName", packageName);
    		DataBaseManager.malasiyaTripPackage(username, mobileNumber, packageName);
    		System.out.println(packageName);
    	}else if(packageType.equals("groupPackage")) {
    		String packageName = "Group Package";
    		session.setAttribute("packageName", packageName);
    		DataBaseManager.malasiyaTripPackage(username, mobileNumber, packageName);
    		System.out.println(packageName);	
    	}
		return "Booking.jsp";
    }
    
    @PostMapping("/Booking")
    public String booking (HttpSession session,@RequestParam("fromDate") String fromDate ,@RequestParam("toDate") String toDate ,@RequestParam("no_Of_Adults") String noOfAdults,@RequestParam("no_Of_Childrens") String noOfChildrens ,@RequestParam("flight_Option") String flight_Option , Trip Trip) {
    	
    	String username = (String) session.getAttribute("username");
    	int adult = Integer.parseInt(noOfAdults);
    	int children = Integer.parseInt(noOfChildrens);
    	session.setAttribute("adult", adult);
    	session.setAttribute("children", children);
    	System.out.println("Number of adults "+adult);
    	System.out.println("Number of children"+children);
    	
    	LocalDate from = LocalDate.parse(fromDate);
    	LocalDate to = LocalDate.parse(toDate);
    	
    	int differenceInDays = (int) ChronoUnit.DAYS.between(from, to);
    	session.setAttribute("differenceInDays", differenceInDays);
    	System.out.println(differenceInDays);
    	
    	int totalAmountFlightInc = Trip.getTotalPrice();
    	
    	session.setAttribute("packagePrice", totalAmountFlightInc);
    	
    	
    	if(flight_Option.equals("Include Flight")) {
    		return "BookingWithFlightInc.jsp";
    	}
    		return "";
    }
    
    @PostMapping("/AddUser")
    public String addTraveller(HttpSession session , @RequestParam("textBox[]") String[] textBoxValues , @RequestParam("age[]") String[] ageValues) {
    	String username = (String) session.getAttribute("username");
    	String mobileNumber = (String) session.getAttribute("mobileNumber");
    	String[][] genderValues = new String[textBoxValues.length][];
    	for(int i = 0 ; i < textBoxValues.length; i++) {
    		genderValues[i] = (String[]) session.getAttribute("gender["+i+"][]");
    	}
    	
    	for(int i = 0 ; i < textBoxValues.length;i++) {
    		String travelerName = textBoxValues[i];
    		String travelerAge = ageValues[i];
    		String travelerGender = genderValues[i][0];
    		
    		session.setAttribute("travelerName", travelerName);
    		session.setAttribute("travelerAge", travelerAge);
    		session.setAttribute("travelerGender", travelerGender);
    		
    		String destination = DataBaseManager.retriveDestination(username);
    		session.setAttribute("destination", destination);
    		
    		DataBaseManager.addTravellers(username, mobileNumber, travelerName, travelerAge, travelerGender, destination);
    		
    	}
    	
		return "AddTravellers.jsp";
    	
    }
    
}
