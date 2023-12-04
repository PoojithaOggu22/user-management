package com.bilvantis.user.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReporteesDTO {

    private EmployeeDTO employeeDTO;
    private List<ReporteesDTO> reporteesDTOList;
}
