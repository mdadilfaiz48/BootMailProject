package com.adil.service;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;


import jakarta.mail.internet.MimeMessage;

@Service
public class PurchaseMgmtServiceImpl implements IPurchaseMgmtService {
	
	@Autowired
	private JavaMailSender sender;
	@Value("${spring.mail.username}")
	private String fromEmailId;

	@Override
	public String shopping(String[] items, Double[] prices, String[] toEmailIds)throws Exception {
		
		// calculate the billamount
		 double totalAmt = 0.0;
		 for(double p:prices) {
			 totalAmt = totalAmt + p;
		 }
		 String msg1 = Arrays.toString(items)+" are purchased having price "+Arrays.toString(prices)+" with the bill amount"+totalAmt;
		 // trigger the email message
		String msg2 = sendEmail(msg1,toEmailIds,fromEmailId);
		 
		return msg1+"....."+msg2;
	}
	
	private String sendEmail(String msg,String[] toEmailIds,String fromEmailId) throws Exception {
		//create and send multyPartMIME message
		MimeMessage message = sender.createMimeMessage();// represent email message
		MimeMessageHelper helper = new MimeMessageHelper(message,true); // represet eamil message with allowing the attachement
		// setting the Header value
		helper.setSentDate(new Date());
		helper.setFrom(fromEmailId);
		helper.setCc(toEmailIds);
		helper.setSubject("open it to now it");
		// set multi part body
		helper.setText(msg);// Text part
		helper.addAttachment("cloth.jpg", new ClassPathResource("cloth.jpg"));// image part
		//send the message
		sender.send(message);
		
		return "Email message is sent";
		
	}

}
