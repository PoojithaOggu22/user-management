package com.bilvantis.user.api.util;

import com.bilvantis.user.api.exception.ApplicationException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.nio.charset.StandardCharsets;

public class EmailSupport {

    public static MimeMessageHelper settingMimeMessageHelper(EmailDetails emailDetails, MimeMessage mimeMessage, String senderMailId) throws MessagingException {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.toString());
            messageHelper.setFrom(senderMailId);
            messageHelper.setTo(emailDetails.getRecipient());
            messageHelper.setSubject(emailDetails.getSubject());
            return messageHelper;
        } catch (MessagingException e) {
            throw new ApplicationException(e);
        }

    }

    public static EmailDetails settingEmailDetails(String mailId, String subject) {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(mailId);
        emailDetails.setSubject(subject);
        return emailDetails;

    }


}
