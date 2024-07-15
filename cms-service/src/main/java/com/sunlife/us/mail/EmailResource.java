package com.sunlife.us.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

//@Path("email")
@Named
@ApplicationScoped
public class EmailResource {

    private static final Logger logger = LoggerFactory.getLogger(EmailResource.class);
    @Inject
    @SendEmail
    private Event<EmailEvent> emailEvent;

    @Resource(name = "cell/persistent/cms/emails", lookup = "cell/persistent/cms/emails")
    private String defaultEmailAddress;


    public void init() {
        logger.info("initiating email service ");

    }


    /**
     * This will send message to mentioned recipents in a single email.
     * @param email = emailRecipent mail id. add comma seperated emails to send email to multiple
     * @param message
     * @return
     */
    public boolean sendEmail(String email, String message)  {

        logger.info("email:" + email + " message:" + message);

        emailEvent.fire(EmailEvent.build(email, message));
       /* try {
            emailSender.send(EmailEvent.build(email, message));
        }catch (NamingException e){
            logger.info("unable to send email");
        }*/
        return true;
    }

    /**
     * This will send the email to default emailIds.
     * To send email to multiple people just add comma seperated emails
     * default is
     * @param message
     * @return
     */
    public boolean sendEmail(String message ){
        logger.info("Default email " + defaultEmailAddress);
        return sendEmail(defaultEmailAddress,message);
    }

    public boolean sendEmailAsync(String email, String message) {

        logger.info("email:" + email + " message:" + message);

        emailEvent.fire(EmailEvent.build(email, message));

        return true;

    }
}
