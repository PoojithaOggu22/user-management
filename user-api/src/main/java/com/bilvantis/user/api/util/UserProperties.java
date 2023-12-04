package com.bilvantis.user.api.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;



@Getter
@Configuration
@ComponentScan
@PropertySource("classpath:user.properties")
public class UserProperties {

    @Value("${default.page-number}")
    private Integer defaultPageNumber;

    @Value("${default.page-size}")
    private Integer defaultPageSize;

    @Value("${employee.not-found}")
    private String employeeNotAvailableForId;

    @Value("${employee.save-failed}")
    private String employeeSaveFailed;

    @Value("${quarter.save-failed}")
    private String quarterSaveFailed;

    @Value("${month.save-failed}")
    private String monthSaveFailed;

    @Value("${frequency.save-failed}")
    private String frequencySaveFailed;

    @Value("${awardees.save-failed}")
    private String awardeesSaveFailed;

    @Value("${quarter.not-found}")
    private String quarterNotAvailableForId;

    @Value("${month.not-found}")
    private String monthNotAvailableForId;

    @Value("${quarter.not-active}")
    private String quarterNotActive;

    @Value("${month.not-active}")
    private String monthNotActive;

    @Value("${frequency.not-found}")
    private String frequencyNotAvailableForId;

    @Value("${quarterId.not-Null}")
    private String quarterIdNotNull;

    @Value("${frequencyId.not-Null}")
    private String frequencyIdNotNull;

    @Value("${employee.already-exist}")
    private String employeeAlreadyExist;

    @Value("${employee.list-not-available}")
    private String employeesNotAvailable;

    @Value("${awardees.list-not-available}")
    private String awardeesNotAvailable;

    @Value("${awardee.already-nominated}")
    private String awardeeAlreadyNominated;

    @Value("${quarter.list-not-available}")
    private String quartersNotAvailable;

    @Value("${month.list-not-available}")
    private  String monthsNotAvailable;

    @Value("${frequency.list-not-available}")
    private String frequenciesNotAvailable;

    @Value("${employee.id-mismatch}")
    private String employeeIdMismatch;

    @Value("${awardEmployeeTypes.save-failed}")
    private String awardEmployeeTypeSaveFailed;

    @Value("${awardEmployeeTypes.list-not-available}")
    private String awardEmployeeTypesNotAvailable;

    @Value("${awardEmployeeTypes.not-found}")
    private String awardEmployeeNotFound;

    @Value("${awardEmployeeTypes.id-mismatch}")
    private String awardEmployeeTypeIdMismatch;

    @Value("${awardEmployeeTypes.delete-successfully}")
    private String awardEmployeeTypesDeleteSuccess;

    @Value("${awardEmployeeTypes.data-already-exist}")
    private String awardEmployeeTypesAlreadyExist;


    @Value("${login.id-password-mismatch}")
    private String employeeUserIdOrPasswordMismatch;

    @Value("${change.password.err.msg}")
    private String oldNewPasswordSame;

    @Value("${login.otp-sent-mail}")
    private String employeeOtpSentViaMail;

    @Value("${login.otp-Expired}")
    private String employeeOtpExpired;

    @Value("${login.otp-changed}")
    private String employeeOtpAlreadyChanged;

    @Value("${login.id-OTP-mismatch}")
    private String employeeOTPMismatch;

    @Value("${spring.mail.username}")
    private String senderMailId;

    @Value("${email.subject-otp-generation}")
    private String subjectForOtpGeneration;

    @Value("${exception.error.message}")
    private String exceptionErrorMessage;

    @Value("${login.otp-format-percentage}")
    private String otpFormatPercentage;

    @Value("${login.otp-format-numberofdigits-bound}")
    private String otpNoOfDigitsBound;

    @Value("${quarter.id-mismatch}")
    private String quarterIdMismatch;


    @Value("${award.save-failed}")
    private String awardSaveFailed;

    @Value("${award.list-not-available}")
    private String awardNotAvailable;

    @Value("${award.not-found}")
    private String awardNotFound;

    @Value("${award.id-mismatch}")
    private String awardIdMismatch;

    @Value("${award.delete-successfully}")
    private String awardDeleteSuccess;

    @Value("${award.data-already-exist}")
    private String awardAlreadyExist;

    @Value("${frequency.id-mismatch}")
    private String frequencyIdMismatch;

    @Value("${team.save-failed}")
    private  String teamSaveFailed;

    @Value("${team.not-found}")
    private String teamNotAvailableForId;

    @Value("${awardee.not-found}")
    private  String awardeesNotAvailableForId;

    @Value("${awardeeId.not-Null}")
    private String awardeeIdNotNull;

    @Value("${awardee.id-mismatch}")
    private String awardeeIdMismatch;

    @Value("${awardee.save-failed}")
    private String awardeeSaveFailed;

    @Value("${teamId.not-Null}")
    private String teamIdNotNull;

    @Value("${teams.list-not-available}")
    private String teamsNotAvailable;

    @Value("${team.id-mismatch}")
    private String teamIdMismatch;

    @Value("${nominee.save-failed}")
    private String nomineeSaveFailed;

    @Value("${nominee.not-eligible}")
    private String nominatedByNotEligible;

    @Value("${nominee.not-eligible-nominee}")
    private String nomineeNotEligible;

    @Value("${nominee.already-nominated}")
    private String alreadyNominated;

    @Value("${employeeType.save-failed}")
    private String employeeTypeSaveFailed;

    @Value("${employeeTypes.list-not-available}")
    private String employeeTypesNotAvailable;

    @Value("${employeeTypeId.not-Null}")
    private String employeeTypeIdNotNull;

    @Value("${employeeType.not-found}")
    private String employeeTypeNotAvailableForId;

    @Value("${employeeType.id-mismatch}")
    private String employeeTypeIdMismatch;

    @Value("${frequency.already-available}")
    private String frequencyAlreadyAvailable;

    @Value("${employee.id-mandatory}")
    private String employeeIdMandatory;

    @Value("${award.id-mandatory}")
    private String awardIdMandatory;

    @Value("${employeeType.id-mandatory}")
    private String employeeTypeIdMandatory;


    @Value(("${award.id-awardEmployeeType-not-available}"))
    private String awardEmployeeTypeNotExistForAwardId;

    @Value(("${award.id-potentialNominee-not-available}"))
    private String potentialNomineeNotAvailableForGivenAwardId;

    @Value("${login.voting.expired}")
    private String votingExpired;

    public Pageable getPageRequest(Integer pageNumber, Integer size) {
        pageNumber = (pageNumber == null) ? this.getDefaultPageNumber() : pageNumber;
        size = (size == null) ? this.getDefaultPageSize() : size;
        return PageRequest.of(pageNumber,size);
    }
}
