package com.example.demouser;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service

public class emailService {
	
	  private JavaMailSender javaMailSender;

	    public emailService(JavaMailSender javaMailSender) {
	        this.javaMailSender = javaMailSender;
	    }
	    




		public void sendMail(String email, String res) {
			
			  var mailMessage = new SimpleMailMessage();

		        mailMessage.setTo(email);
		        mailMessage.setText(res);

		        mailMessage.setFrom("kasaanushasrinivasrao@gmail.com");

		        javaMailSender.send(mailMessage);
			// TODO Auto-generated method stub
			
		}

}
