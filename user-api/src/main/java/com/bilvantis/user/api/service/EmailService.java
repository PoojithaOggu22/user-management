package com.bilvantis.user.api.service;

import com.bilvantis.user.api.util.EmailDetails;
import com.bilvantis.user.dao.data.model.Employee;


public interface EmailService {
    void sendMailOtpGeneration(EmailDetails emailDetails, Employee employeeEntity);
}
