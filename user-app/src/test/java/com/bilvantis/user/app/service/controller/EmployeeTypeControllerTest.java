package com.bilvantis.user.app.service.controller;

import com.bilvantis.user.api.service.EmployeeTypeService;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import com.bilvantis.user.data.model.EmployeeTypeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static com.bilvantis.user.app.service.util.EmployeeTypeControllerTestSupport.createEmployeeTypeDTO;
import static com.bilvantis.user.app.service.util.EmployeeTypeControllerTestSupport.updateEmployeeTypeDTO;
import static com.bilvantis.user.app.service.util.RewardAppTestConstant.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Type Controller Tests")
public class EmployeeTypeControllerTest {
    @InjectMocks
    private EmployeeTypeController employeeTypeController;
    @Mock
    private EmployeeTypeService<EmployeeTypeDTO, Long> employeeTypeService;


    @Test
    @DisplayName("Test get All Employee Types")
    public void testGetAllEmployeeTypes() {

        // given
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        List<EmployeeTypeDTO> employeeTypesDTOList = Collections.singletonList(employeeTypeDTO);
        when(employeeTypeService.getAllEmployeeTypes()).thenReturn(employeeTypesDTOList);

        // when
        ResponseEntity<UserResponseDTO> response = employeeTypeController.getAllEmployeeTypes();
        List<EmployeeTypeDTO> actualEmployeeListType = (List<EmployeeTypeDTO>) response.getBody().getBody();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEmployeeTypeListDTO(actualEmployeeListType);
    }

    @Test
    @DisplayName("Test get Employee Type By Id")
    public void testGetEmployeeTypeById() {

        // given
        Long id = ID;
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        when(employeeTypeService.getEmployeeTypeById(anyLong())).thenReturn(employeeTypeDTO);

        // when
        ResponseEntity<UserResponseDTO> response = employeeTypeController.getEmployeeTypeById(id);
        EmployeeTypeDTO actualEmployeeType = (EmployeeTypeDTO) response.getBody().getBody();

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEmployeeTypeListDTO(List.of(actualEmployeeType));
    }

    @Test
    @DisplayName("Test create Employee Type")
    public void testCreateEmployeeType() {

        // given
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        when(employeeTypeService.createEmployeeType(employeeTypeDTO)).thenReturn(employeeTypeDTO);

        //when
        ResponseEntity<EmployeeTypeDTO> response = employeeTypeController.createEmployeeType(employeeTypeDTO);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        EmployeeTypeDTO actualEmployeeType = (EmployeeTypeDTO) response.getBody();
        assertEmployeeTypeListDTO(List.of(actualEmployeeType));
    }

    @Test
    @DisplayName("Test update Employee Type")
    public void testUpdateEmployeeType() {

        // given
        Long id = ID;
        EmployeeTypeDTO employeeTypeDTO = updateEmployeeTypeDTO(ID);
        when(employeeTypeService.updateEmployeeType(anyLong(), any(EmployeeTypeDTO.class))).thenReturn(employeeTypeDTO);

        // when
        ResponseEntity<UserResponseDTO> response = employeeTypeController.updateEmployeeType(id, employeeTypeDTO);
        EmployeeTypeDTO actualEmployeeType = (EmployeeTypeDTO) response.getBody().getBody();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, actualEmployeeType.getId());
        assertEquals("Developer", actualEmployeeType.getDesignation());
        assertTrue(true);
        assertEquals("admin", actualEmployeeType.getCreatedBy());
        assertEquals("admin", actualEmployeeType.getUpdatedBy());
    }

    @Test
    @DisplayName("Test delete Employee Type By Id")
    public void testDeleteEmployeeTypeById() {

        //given
        Long id = ID;
        assertDoesNotThrow(() -> employeeTypeController.deleteEmployeeTypeById(ID));

        //when and then
        verify(employeeTypeService, times(1)).deleteEmployeeType(id);
    }

    private void assertEmployeeTypeListDTO(List<EmployeeTypeDTO> actualEmployeeListType) {
        assertEquals(1, actualEmployeeListType.size());
        assertEquals(1L, actualEmployeeListType.get(0).getId());
        assertEquals("Programmer Analyst", actualEmployeeListType.get(0).getDesignation());
        assertTrue(true);
        assertEquals("admin", actualEmployeeListType.get(0).getCreatedBy());
        assertEquals("admin", actualEmployeeListType.get(0).getUpdatedBy());

    }
}
