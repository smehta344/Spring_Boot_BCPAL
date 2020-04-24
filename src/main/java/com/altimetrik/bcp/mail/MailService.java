package com.altimetrik.bcp.mail;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.altimetrik.bcp.model.Mail;
import com.altimetrik.bcp.util.AppConstants;

@Service
public class MailService {

	@Autowired
	JavaMailSender mailSender;

	@Value("${mail.username}")
	private String userName;

	@Async
	public void sendEmail(Mail mail) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		ClassLoader classLoader = this.getClass().getClassLoader();

		File file = new File(classLoader.getResource("templates/img/Logo.png").getFile());
		try {
			mail.setMailFrom(userName);
			mail.setMailSubject(AppConstants.MAIL_SUBJECT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED);
			mail.setMailContent(AppConstants.MAIL_CONTENT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED_1ST + mail.getName()
					+ AppConstants.MAIL_CONTENT_FOR_NOTIFY_ATTENDANCE_NOT_MARKED_2ST);
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
					MimeMessageHelper.MULTIPART_MODE_RELATED, "UTF-8");
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setFrom(mail.getMailFrom());
			mimeMessageHelper.setTo(mail.getMailTo());
			mimeMessageHelper.setText(mail.getMailContent(), true);
			mimeMessageHelper.setPriority(1);
			mimeMessageHelper.addInline("Logo.png", file);
			mailSender.send(mimeMessageHelper.getMimeMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
