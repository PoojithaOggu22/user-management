package com.bilvantis.user.api.service;


import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.service.impl.EmployeeTypeServiceImpl;
import com.bilvantis.user.api.util.RewardsProperties;
import com.bilvantis.user.dao.data.model.EmployeeType;
import com.bilvantis.user.dao.data.repository.EmployeeTypeRepository;
import com.bilvantis.user.data.model.EmployeeTypeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.bilvantis.user.api.util.EmployeeTypeTestSupport.*;
import static com.bilvantis.user.api.util.RewardsApiTestConstant.ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employee Type Service Test")
public class EmployeeTypeServiceImplTest {
    @InjectMocks
    EmployeeTypeServiceImpl employeeTypeService;
    @Mock
    private EmployeeTypeRepository employeeTypeRepository;
    @Mock
    private RewardsProperties rewardsProperties;


    @Test
    @DisplayName("Test Get All Employee Type")
    void testGetAllEmployeeTypes() {

        // given
        EmployeeType employeeType = createEmployeeType(ID);
        List<EmployeeType> mockEmployeeType = Collections.singletonList(employeeType);
        when(employeeTypeRepository.findAll()).thenReturn(mockEmployeeType);

        // when
        List<EmployeeTypeDTO> employeeTypeDTOList = employeeTypeService.getAllEmployeeTypes();

        //then
        assertEmployeeTypeListDTO(employeeTypeDTOList);
    }

    @Test
    @DisplayName("Test get all Employee Type with empty result")
    public void testGetAllEmployeeTypeEmptyResult() {

        //given
        when(employeeTypeRepository.findAll()).thenReturn(Collections.emptyList());
        when(rewardsProperties.getEmployeeTypesNotAvailable()).thenReturn("EmployeeType details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.getAllEmployeeTypes());

        //then
        assertEquals("EmployeeType details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test get all Employee Type Exception scenario")
    public void testGetAllEmployeeTypeDataIntegrityViolationException() {

        //given
        when(employeeTypeRepository.findAll()).thenThrow(new DataIntegrityViolationException("EmployeeType details not present in the system"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in getAllEmployeeType");
        when(rewardsProperties.getEmployeeTypesNotAvailable()).thenReturn("EmployeeType details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.getAllEmployeeTypes());

        //then
        assertEquals("EmployeeType details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test method for the get  Employee Types By Id")
    public void testGetEmployeeTypesById() {

        //given
        EmployeeType employeeType = createEmployeeType(ID);
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.of(employeeType));

        //when
        EmployeeTypeDTO employeeTypeDTO = employeeTypeService.getEmployeeTypeById(ID);

        //then
        assertEmployeeTypeListDTO(List.of(employeeTypeDTO));
    }

    @Test
    @DisplayName("Test get Employee Types with unavailable id")
    public void testEmployeeTypeWithUnavailableEmployeeId() {

        //given
        when(rewardsProperties.getEmployeeTypeNotAvailableForId()).thenReturn("EmployeeTypes is not available for given id: 2");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.getEmployeeTypeById(ID));

        //then
        assertEquals("EmployeeTypes is not available for given id: 2", exception.getMessage());
    }

    @Test
    @DisplayName("Test get employee by ID")
    public void testEmployeeTypesWithEmployeeTypesId() {

        //given
        when(rewardsProperties.getEmployeeTypeIdNotNull()).thenReturn("EmployeeTypes is not available for given id: null");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.getEmployeeTypeById(null));

        //then
        assertEquals("EmployeeTypes is not available for given id: null", exception.getMessage());
    }

    @Test
    @DisplayName("Test get all EmployeeTypes by id DataIntegrityViolation Exception")
    public void testGetEmployeeTypesByIdResultDataIntegrityViolationException() {

        //given
        when(employeeTypeRepository.findById(ID)).thenThrow(new DataIntegrityViolationException("EmployeeTypes details save failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in repository while fetching the data");
        when(rewardsProperties.getEmployeeTypesNotAvailable()).thenReturn("EmployeeTypes details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.getEmployeeTypeById(ID));

        //then
        assertEquals("EmployeeTypes details not present in the system", exception.getMessage());
    }

    @Test
    @DisplayName("Test create award employee types")
    public void testCreateAwardEmployeeTypes() {

        //given
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        EmployeeType employeeType = createEmployeeType(ID);

        when(employeeTypeRepository.save(any(EmployeeType.class))).thenReturn(employeeType);

        //when
        EmployeeTypeDTO responseEmployeeTypesDTO = employeeTypeService.createEmployeeType(employeeTypeDTO);

        //then
        assertEmployeeTypeListDTO(List.of(responseEmployeeTypesDTO));
    }

    @Test
    @DisplayName("Test create award employee types DataIntegrityViolation Exception")
    public void testCreateAwardEmployeeTypesDataIntegrityViolationException() {

        //given
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        ;
        when(employeeTypeRepository.save(any(EmployeeType.class))).thenThrow(new DataIntegrityViolationException("Failed to save EmployeeType"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in EmployeeType");
        when(rewardsProperties.getEmployeeTypeSaveFailed()).thenReturn("Failed to save EmployeeType");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.createEmployeeType(employeeTypeDTO));

        //then
        assertEquals("Failed to save EmployeeType", exception.getMessage());
    }

    @Test
    @DisplayName("Test update employeetype")
    public void testUpdateAEmployeeType() {

        //given

        EmployeeTypeDTO employeeTypeDTO = updateEmployeeTypeDTO(ID);
        EmployeeType employeeType = createEmployeeType(ID);
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.of(employeeType));
        when(employeeTypeRepository.save(Mockito.any(EmployeeType.class))).thenReturn(employeeType);

        //when
        EmployeeTypeDTO updatedAwardee = employeeTypeService.updateEmployeeType(ID, employeeTypeDTO);

        //then
        assertEquals(1L, updatedAwardee.getId());
        assertEquals(" Programmer Analyst", updatedAwardee.getDesignation());
        assertTrue(true);
        assertEquals("admin", updatedAwardee.getCreatedBy());
        assertEquals("admin", updatedAwardee.getUpdatedBy());
    }

    @Test
    @DisplayName("Test update employeetype with different ID and different payload")
    public void testUpdateEmployeeTypeWithDifferentPayload() {

        //given
        EmployeeTypeDTO employeeTypeDTO = createEmployeeTypeDTO(ID);
        when(rewardsProperties.getEmployeeTypeIdMismatch()).thenReturn("EmployeeType id given in URL 2 and payload 1 should match");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.updateEmployeeType(2L, employeeTypeDTO));

        //then
        assertEquals("EmployeeType id given in URL 2 and payload 1 should match", exception.getMessage());
    }

    @Test
    @DisplayName("Test update awardee DataIntegrityViolation Exception")
    public void testUpdateAwardeeDataIntegrityViolationException() {

        //given
        EmployeeTypeDTO employeeTypeDTO = updateEmployeeTypeDTO(ID);
        EmployeeType employeeType = createEmployeeType(ID);
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.of(employeeType));
        when(employeeTypeRepository.save(any(EmployeeType.class))).thenThrow(new DataIntegrityViolationException("update EmployeeType failed"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred while updating EmployeeType");
        when(rewardsProperties.getEmployeeTypeSaveFailed()).thenReturn("EmployeeType to save Quarter");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.updateEmployeeType(ID, employeeTypeDTO));

        //then
        assertEquals("EmployeeType to save Quarter", exception.getMessage());
    }

    @Test
    @DisplayName("Test delete employee type by id")
    public void testDeleteEmployeeTypeById() {

        //given
        EmployeeType employeeType = createEmployeeType(ID);
        EmployeeType deleteEmployeeType = createEmployeeTypeIsActiveFalse(ID);
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.of(employeeType));
        when(employeeTypeRepository.save(Mockito.any(EmployeeType.class))).thenReturn(employeeType);

        //when and then
        assertDoesNotThrow(() -> employeeTypeService.deleteEmployeeType(ID));
        assertEquals(1L, deleteEmployeeType.getId());
        assertEquals("Senior Programmer Analyst", deleteEmployeeType.getDesignation());
        assertFalse(false);
        assertEquals("SYSTEM", deleteEmployeeType.getCreatedBy());
        assertEquals("SYSTEM", deleteEmployeeType.getUpdatedBy());
    }

    @Test
    @DisplayName("Test delete non-existent employee type by ID")
    public void testDeleteNonExistentEmployeeById() {

        //given
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.empty());
        when(rewardsProperties.getEmployeeTypeNotAvailableForId()).thenReturn("EmployeeType details not present in the system: 2");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.deleteEmployeeType(ID));

        //then
        assertEquals("EmployeeType details not present in the system: 2", exception.getMessage());
    }

    @Test
    @DisplayName("Test delete employeeType DataIntegrityViolation Exception by ID")
    public void testDeleteEmployeeTypeDataIntegrityViolationException() {

        //given
        EmployeeType employeeType = createEmployeeType(ID);
        when(employeeTypeRepository.findById(ID)).thenReturn(Optional.of(employeeType));
        when(employeeTypeRepository.save(employeeType)).thenThrow(new DataIntegrityViolationException("Failed to delete employeeType"));
        when(rewardsProperties.getExceptionErrorMessage()).thenReturn("Exception occurred in deleteEmployeeById");
        when(rewardsProperties.getEmployeeTypeNotAvailableForId()).thenReturn("employeeType details not present in the system");

        //when
        ApplicationException exception = assertThrows(ApplicationException.class, () -> employeeTypeService.deleteEmployeeType(ID));

        //then
        assertEquals("employeeType details not present in the system", exception.getMessage());
    }

    private void assertEmployeeTypeListDTO(List<EmployeeTypeDTO> employeeTypeDTOList) {
        assertEquals(1, employeeTypeDTOList.size());
        assertEquals(1L, employeeTypeDTOList.get(0).getId());
        assertEquals("Senior Programmer Analyst", employeeTypeDTOList.get(0).getDesignation());
        assertTrue(true);
        assertEquals("SYSTEM", employeeTypeDTOList.get(0).getCreatedBy());
        assertEquals("SYSTEM", employeeTypeDTOList.get(0).getUpdatedBy());
    }

}
