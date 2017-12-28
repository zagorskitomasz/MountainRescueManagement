package com.zagorskidev.rescuecrm.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.zagorskidev.rescuecrm.entity.Operation;

/**
 * Implements operations related to email sending.
 * 
 * @author tomek
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private Environment env;

	@Autowired
	private HttpSession session;

	@Override
	public void notifyOperationCreated(Operation operation) {

		String message = "You successfully added rescue operation!";
		sendOperationNotification(operation, message);
	}

	@Override
	public void notifyOperationUpdated(Operation operation) {

		String message = "You successfully updated rescue operation!";
		sendOperationNotification(operation, message);
	}
	
	private void sendOperationNotification(Operation operation, String message) {
		
		String userEmail = (String) session.getAttribute("userEmail");
		String operationUrl = createOperationUrl(operation);

		SimpleMailMessage msg = prepareEmail(message, userEmail, operationUrl);

		mailSender.send(msg);
	}

	private String createOperationUrl(Operation operation) {
		int operationId = operation.getId();

		String operationUrl = (String) session.getAttribute("contextUrl") + "/operation/details?operationId="
				+ operationId;
		return operationUrl;
	}

	private SimpleMailMessage prepareEmail(String message, String userEmail, String operationUrl) {
		
		SimpleMailMessage msg = new SimpleMailMessage();

		msg.setSubject(message);
		msg.setText("You can see your operation here: " + operationUrl);
		msg.setTo(userEmail);
		msg.setFrom(env.getProperty("rescue_mail"));
		
		return msg;
	}
}
