package com.sunlife.us.mail;

import com.sunlife.us.exception.CMSRunTimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Singleton;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

/**
 * For This class to work in WLP .There should be feature javaMail-1.6 must be installed.
 */
@Singleton
public class EmailSender {
    private static final Logger logger = LoggerFactory.getLogger(EmailSender.class);
    @Resource(lookup = "cmsMailSession", name = "cmsMailSession")
    private Session session;

    @PostConstruct
    public void init() {

    }

    //Method used to send email with synchronous way
    public void send(@Observes @SendEmail EmailEvent emailEvent) throws NamingException {
        runSend(emailEvent);
        logger.info("Email sent!");
    }

    // Method used to send email with asynchronous way this will work only if it is send through event.fireAsync()
    public void sendAsyn(@ObservesAsync @SendEmail EmailEvent emailEvent) throws NamingException {
        runSend(emailEvent);
        logger.info("Email sent Async!");
    }

    private void runSend(EmailEvent emailEvent) throws NamingException {
        try {
            MimeMessage message = new MimeMessage(session);
//            message.setFrom(session.getProperty("mail.address"));
            message.setFrom("usho.ebusiness@sunlife.com");
            message.setRecipients(Message.RecipientType.TO, emailEvent.getEmailTo());
            message.setSubject("CMS scheduled task");
            message.setText(emailEvent.getMessage());
            /**Method to send the created message*/
            Transport.send(message);
            logger.info("Email sent succesful to " + emailEvent.getEmailTo());

        } catch (MessagingException e) {
            logger.error("Email sending failed to " + emailEvent.getEmailTo());
            throw new CMSRunTimeException("Email unsuccesful", e);
        }
    }
}