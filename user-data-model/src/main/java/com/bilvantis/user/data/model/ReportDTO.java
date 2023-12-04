package com.bilvantis.user.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportDTO {

    private Integer pageNumber;
    private Integer pageSize;
    private String employeeId;
    private Long awardId;
    private Long quarterId;
    private Long month;
    private Date startDate;
    private Date endDate;
    private String data;
    private String year;
}
