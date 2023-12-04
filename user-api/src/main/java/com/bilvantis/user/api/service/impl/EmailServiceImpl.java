package com.bilvantis.user.api.service.impl;

import com.bilvantis.user.api.service.EmailService;
import com.bilvantis.user.api.util.EmailDetails;
import com.bilvantis.user.api.util.EmailSupport;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.dao.data.model.Employee;
import com.bilvantis.user.api.exception.ApplicationException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserProperties userProperties;

    /**
     * Sends an OTP email to an employee using the provided email details.
     *
     * @param emailDetails   EmailDetails
     * @param employeeEntity EmployeeEntity
     */

    @Override
    public void sendMailOtpGeneration(EmailDetails emailDetails, Employee employeeEntity) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = EmailSupport.settingMimeMessageHelper(emailDetails, mimeMessage, userProperties.getSenderMailId());
            ClassPathResource emailTemplateResource = new ClassPathResource("otpEmail-Template.html");
            String emailTemplateContent = new String(FileCopyUtils.copyToByteArray(emailTemplateResource.getInputStream()), StandardCharsets.UTF_8);
            emailTemplateContent = emailTemplateContent.replace("${otp}", employeeEntity.getOtp());
            emailTemplateContent = emailTemplateContent.replace("${name}", employeeEntity.getFirstName());
            messageHelper.setText(emailTemplateContent, true);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new ApplicationException(e);
        }

    }

}
