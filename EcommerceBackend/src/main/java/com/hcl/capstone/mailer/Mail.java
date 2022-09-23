package com.hcl.capstone.mailer;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

import com.hcl.capstone.model.Order;
import com.hcl.capstone.model.OrderItem;
import com.hcl.capstone.model.User;
import com.hcl.capstone.global.OrderStatus;

public class Mail {

	private final static String PORT = "587";
	private final static String HOST = "smtp.mailtrap.io";
	private final static String USERNAME = "cd19697a5b883e";
	private final static String PASSWORD = "dc0a45adc71c75";
	private final static String EMAIL = "7684ca9139-e5d79e@inbox.mailtrap.io";

	private final static boolean AUTH = true;
	private final static boolean STARTTLS = true;
	public void send(User user) throws MessagingException  {
		Message msg = new MimeMessage(setSession(setProperties()));

		msg.setSentDate(new Date());
		msg.setSubject("Account Registration Confirmation");

		msg.setFrom(new InternetAddress(EMAIL, false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));

		msg.setContent("Hello " + user.getFirstName() + ", you have succesfully created an account on HCL Ecommerce!", "text/html");

		Transport.send(msg);
	}
	
	public void sendConfirmationEmail(User user, Order order, List<OrderItem> orderItems) throws  MessagingException {
		Message msg = new MimeMessage(setSession(setProperties()));

		msg.setSentDate(new Date());
		msg.setSubject("Order " + order.getOrderStatus());

		msg.setFrom(new InternetAddress(EMAIL, false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<style> th, td {padding: 15px} table, th, td {border: 1px solid} table {text-align: center; border-collapse: collapse}</style>");
		builder.append("Hello " + user.getFirstName());
		builder.append("</br>");
		builder.append("</br>");
		if(order.getOrderStatus().equals(OrderStatus.COMPLETED)) {
			builder.append("<b>Order Update!</b>");
			builder.append("</br>");
			builder.append("<b>Your order has been " + order.getOrderStatus() + "!</b>");
		}else {
			builder.append("<b>Order Confirmation</b>");
		}
		builder.append("</br>");
		builder.append("Order Detail");
		builder.append("</br>");
		builder.append("</br>");
		builder.append("<table>");
		builder.append("<tr>");
		builder.append("<th>Product Name</th>");
		builder.append("<th>Quantity</th>");
		builder.append("<th>Price</th>");
		builder.append("<th>Total</th>");
		builder.append("</tr>");
		
		for(OrderItem orderItem : orderItems) {
			builder.append("<tr>");
			builder.append("<td>");
			builder.append(orderItem.getProduct().getProductName());
			builder.append("</td>");
			
			builder.append("<td>");
			builder.append(orderItem.getQuantity());
			builder.append("</td>");
			
			builder.append("<td>");
			builder.append("$" + orderItem.getProduct().getUnitPrice());
			builder.append("</td>");
			
			builder.append("<td>");
			builder.append("$"+ orderItem.getProduct().getUnitPrice() * orderItem.getQuantity());
			builder.append("</td>");
			builder.append("</tr>");
		}
		
		
		builder.append("</table>");
		
		builder.append("</br>");
		builder.append("Order Total: $" + order.getOrderTotal());
		builder.append("</br>");
		builder.append("</br>");
		builder.append("Thank you for your purchase!");

		msg.setContent(builder.toString(), "text/html");
		Transport.send(msg);
	}
	
	
	private Session setSession(Properties props) {
		return Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
	}

	private Properties setProperties() {
		Properties props = new Properties();

		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.starttls.enable", STARTTLS);

		return props;
	}

}
