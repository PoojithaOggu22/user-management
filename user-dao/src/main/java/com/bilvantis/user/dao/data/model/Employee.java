package com.bilvantis.user.dao.data.model;

import com.bilvantis.user.dao.service.util.BaseEntity;
import com.bilvantis.user.dao.service.util.CommonEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(CommonEntityListener.class)
public class Employee extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type_id")
    private EmployeeType employeeType;

    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "employee_id")
    private String employeeId;

    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "password", length = 20)
    private String password;

    @Column(name = "first_time_user")
    private Boolean firstTimeUser;

    @Column(name = "otp_gen", length = 6)
    private String otp;

    @Column(name = "otp_gen_time")
    private Long otpGenerationTime;

    @Column(name = "is_admin")
    private Boolean isAdmin;

}
