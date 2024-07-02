package com.project.tripmanager.controller;

import com.project.tripmanager.dao.DataBaseManager;
import com.project.tripmanager.dao.Mail;
import com.project.tripmanager.model.UserCredentials;

import javax.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        UserCredentials ucp = new UserCredentials();
        ucp.setUsername(username);
        ucp.setPassword(password);

       

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

        UserCredentials userCredentials = new UserCredentials();
        
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
    public String validateOtp(HttpSession session,
                              @RequestParam("username") String username,
                              @RequestParam("otp") String otp) {
        String generatedOTP = String.valueOf(session.getAttribute("otp"));

        if (generatedOTP.equals(otp)) {
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
}
