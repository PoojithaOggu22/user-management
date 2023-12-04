package com.bilvantis.user.app.service.controller;

import com.bilvantis.user.api.service.EmployeeService;
import com.bilvantis.user.data.model.EmployeeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Controller Tests")
public class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService<EmployeeDTO, Long> employeeService;

//    @Test
//    @DisplayName("Test create Employee")
//    public void testCreateEmployee() {
//
//        //given
//        EmployeeDTO employeeDTO = createEmployeeDTOForControllerTest(ID);
//        when(employeeService.createEmployee(employeeDTO)).thenReturn(employeeDTO);
//
//        ///when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.createEmployee(employeeDTO);
//
//        //then
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        EmployeeDTO actualEmployee = (EmployeeDTO) response.getBody().getBody();
//        assertEmployeeListDTO(actualEmployee);
//    }
//
//    @Test
//    @DisplayName("Test delete employee")
//    public void testDeleteEmployee() {
//
//        //given and when
//        Long empId = ID;
//        assertDoesNotThrow(() -> employeeController.deleteEmployee(empId));
//
//        //then
//        verify(employeeService, times(1)).deleteEmployeeById(empId);
//    }
//
//    @Test
//    @DisplayName("Test update employee")
//    public void testUpdateEmployee() {
//
//        //given
//        Long empId = ID;
//        EmployeeDTO employeeDTO = updateEmployeeDTOForControllerTest(empId);
//        when(employeeService.updateEmployeeById(empId, employeeDTO)).thenReturn(employeeDTO);
//
//        //when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.updateEmployee(empId, employeeDTO);
//        EmployeeDTO actualEmployee = (EmployeeDTO) response.getBody().getBody();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1L, actualEmployee.getId());
//        assertEquals("JayaSree", actualEmployee.getFirstName());
//        assertEquals("bora", actualEmployee.getLastName());
//        assertEquals("test.hello@bivantis.io", actualEmployee.getEmail());
//        assertEquals("9988776655", actualEmployee.getPhoneNumber());
//        assertEquals(1L, actualEmployee.getTeam().getId());
//        assertEquals(1L, actualEmployee.getEmployeeType().getId());
//        assertEquals("654321", actualEmployee.getEmployeeId());
//        assertEquals(1L, actualEmployee.getManagerId());
//        assertEquals("654321", actualEmployee.getPassword());
//        assertEquals(true, actualEmployee.getIsActive());
//        assertEquals(false, actualEmployee.getFirstTimeUser());
//        assertEquals(false, actualEmployee.getIsAdmin());
//        assertEquals("1234", actualEmployee.getOtp());
//        assertEquals(12345L, actualEmployee.getOtpGenerationTime());
//        assertEquals("admin", actualEmployee.getCreatedBy());
//        assertEquals("admin", actualEmployee.getUpdatedBy());
//    }
//
//    @Test
//    @DisplayName("Test get All employees")
//    public void testGetAllEmployees() {
//
//        //given
//        Integer pageNumber = ONE;
//        Integer size = TWO;
//        String searchTerm = TEST;
//        EmployeeDTO employeeDTO = createEmployeeDTOForControllerTest(ID);
//        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
//        employeeDTOList.add(employeeDTO);
//        PageRequest pageRequest = PageRequest.of(pageNumber, size);
//        Page<EmployeeDTO> employeeDTOS = new PageImpl<>(employeeDTOList, pageRequest, size);
//        when(employeeService.getAllEmployees(pageNumber, size, searchTerm)).thenReturn(employeeDTOS);
//
//        //when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.getAllEmployees(pageNumber, size, searchTerm);
//        List<EmployeeDTO> actualEmployeeList = (List<EmployeeDTO>) response.getBody().getBody();
//
//        //then
//        assertEmployeeListDTO(actualEmployeeList);
//    }
//
//    @Test
//    @DisplayName("Test get Managers Reportees")
//    public void testGetManagersReportees() {
//
//        // given
//        Long managerId = ID;
//        EmployeeDTO employeeDTO = createEmployeeDTOForControllerTest(ID);
//        when(employeeService.getManagersDirectAndIndirectReportees(managerId)).thenReturn(Collections.singletonList(employeeDTO));
//
//        // when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.getManagersReportees(managerId);
//        List<EmployeeDTO> actualEmployeeList = (List<EmployeeDTO>) response.getBody().getBody();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEmployeeListDTO(actualEmployeeList);
//    }
//
//    @Test
//    @DisplayName("Test get Employee Awards")
//    public void testGetEmployeeAwards() {
//
//        // given
//        Long employeeId = ID;
//        Long quarterId = ID_2;
//        EmployeeAwardsResponseDTO employeeAwardsResponseDTO = createEmployeeAwardsResponseDTO(ID);
//        List<EmployeeAwardsResponseDTO> employeeAwardsResponseDTOS = new ArrayList<>();
//        employeeAwardsResponseDTOS.add(employeeAwardsResponseDTO);
//        when(employeeService.getEmployeeAwardsById(employeeId, quarterId,ID)).thenReturn(employeeAwardsResponseDTOS);
//
//        //when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.getEmployeeAwards(ID, quarterId,ID);
//        List<EmployeeAwardsResponseDTO> actualEmployeeList = (List<EmployeeAwardsResponseDTO>) response.getBody().getBody();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(1L, actualEmployeeList.get(0).getAward().getId());
//        assertFalse(actualEmployeeList.get(0).isVoteStatus());
//        assertEquals("12345", actualEmployeeList.get(0).getNomineeId());
//        assertEquals("qwerty", actualEmployeeList.get(0).getNomineeName());
//        assertEquals(4.5f, actualEmployeeList.get(0).getAverageRating());
//        assertEquals("sdfghjk", actualEmployeeList.get(0).getComments());
//        assertEquals("ertyui", actualEmployeeList.get(0).getQuarterName());
//    }
//
//    @Test
//    @DisplayName("Test get Potential Nominees By AwardId")
//    public void testGetPotentialNomineesByAwardId() {
//
//        // given
//        Long awardId = ID;
//        Long employeeTypeId = ID_2;
//        Long employeeId = ID_3;
//        EmployeeDTO employeeDTO = createEmployeeDTOForControllerTest(ID);
//        when(employeeService.getAllPotentialNomineesByAwardId(awardId, employeeTypeId, employeeId)).thenReturn(Collections.singletonList(employeeDTO));
//
//        // when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.getPotentialNomineesByAwardId(awardId, employeeTypeId, employeeId);
//        List<EmployeeDTO> actualEmployeeList = (List<EmployeeDTO>) response.getBody().getBody();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEmployeeListDTO(actualEmployeeList);
//    }
//
//    @Test
//    @DisplayName("Test get Employee")
//    public void testGetEmployee() {
//
//        //given
//        Long empId = ID;
//        EmployeeDTO employeeDTO = createEmployeeDTOForControllerTest(empId);
//        when(employeeService.getEmployeeById(empId)).thenReturn(employeeDTO);
//        // when
//        ResponseEntity<RewardsResponseDTO> response = employeeController.getEmployee(empId);
//        EmployeeDTO actualEmployee = (EmployeeDTO) response.getBody().getBody();
//
//        // then
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEmployeeListDTO(actualEmployee);
//
//    }
//
//    private void assertEmployeeListDTO(EmployeeDTO actualEmployee) {
//        assertEquals(1L, actualEmployee.getId());
//        assertEquals("arjun", actualEmployee.getFirstName());
//        assertEquals("pula", actualEmployee.getLastName());
//        assertEquals("test.hello@bivantis.io", actualEmployee.getEmail());
//        assertEquals("0987654321", actualEmployee.getPhoneNumber());
//        assertEquals(1L, actualEmployee.getTeam().getId());
//        assertEquals(1L, actualEmployee.getEmployeeType().getId());
//        assertEquals("123456", actualEmployee.getEmployeeId());
//        assertEquals(1L, actualEmployee.getManagerId());
//        assertEquals("123456", actualEmployee.getPassword());
//        assertEquals(true, actualEmployee.getIsActive());
//        assertEquals(false, actualEmployee.getFirstTimeUser());
//        assertEquals(false, actualEmployee.getIsAdmin());
//        assertEquals("1234", actualEmployee.getOtp());
//        assertEquals(12345L, actualEmployee.getOtpGenerationTime());
//        assertEquals("admin", actualEmployee.getCreatedBy());
//        assertEquals("admin", actualEmployee.getUpdatedBy());
//    }
//
//    private void assertEmployeeListDTO(List<EmployeeDTO> actualEmployeeList) {
//        assertEquals(1L, actualEmployeeList.get(0).getId());
//        assertEquals("arjun", actualEmployeeList.get(0).getFirstName());
//        assertEquals("pula", actualEmployeeList.get(0).getLastName());
//        assertEquals("test.hello@bivantis.io", actualEmployeeList.get(0).getEmail());
//        assertEquals("0987654321", actualEmployeeList.get(0).getPhoneNumber());
//        assertEquals(1L, actualEmployeeList.get(0).getTeam().getId());
//        assertEquals(1L, actualEmployeeList.get(0).getEmployeeType().getId());
//        assertEquals("123456", actualEmployeeList.get(0).getEmployeeId());
//        assertEquals(1L, actualEmployeeList.get(0).getManagerId());
//        assertEquals("123456", actualEmployeeList.get(0).getPassword());
//        assertEquals(true, actualEmployeeList.get(0).getIsActive());
//        assertEquals(false, actualEmployeeList.get(0).getFirstTimeUser());
//        assertEquals(false, actualEmployeeList.get(0).getIsAdmin());
//        assertEquals("1234", actualEmployeeList.get(0).getOtp());
//        assertEquals(12345L, actualEmployeeList.get(0).getOtpGenerationTime());
//        assertEquals("admin", actualEmployeeList.get(0).getCreatedBy());
//        assertEquals("admin", actualEmployeeList.get(0).getUpdatedBy());
//    }
}

