package com.bilvantis.user.api.service.impl;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.service.EmployeeTypeService;
import com.bilvantis.user.api.util.EmployeeTypeSupport;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.dao.data.model.EmployeeType;
import com.bilvantis.user.dao.data.repository.EmployeeTypeRepository;
import com.bilvantis.user.data.model.EmployeeTypeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("employeeTypeServiceImpl")
@Slf4j
public class EmployeeTypeServiceImpl implements EmployeeTypeService<EmployeeTypeDTO, Long> {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Autowired
    private UserProperties userProperties;

    /**
     * Retrieves a list of all EmployeeTypes.
     *
     * @return A list of EmployeeTypeDTO
     */
    @Override
    public List<EmployeeTypeDTO> getAllEmployeeTypes() {
        try {
            List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
            if (CollectionUtils.isNotEmpty(employeeTypes)) {
                return employeeTypes.stream()
                        .map(EmployeeTypeSupport::convertEmployeeTypeEntityToEmployeeTypeDto)
                        .collect(Collectors.toList());
            } else {
                throw new ApplicationException(userProperties.getEmployeeTypesNotAvailable());
            }
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeTypesNotAvailable(), e);
        }
    }

    /**
     * Retrieves an employee type
     *
     * @param id Id
     * @return An EmployeeTypeDTO
     */
    @Override
    public EmployeeTypeDTO getEmployeeTypeById(Long id) {
        try {
            // Check if the employee type ID is not null
            if (Objects.nonNull(id)) {
                Optional<EmployeeType> optionalEmployeeType = employeeTypeRepository.findById(id);
                // Check if the employee type exists in the database
                if (optionalEmployeeType.isPresent()) {
                    return EmployeeTypeSupport.convertEmployeeTypeEntityToEmployeeTypeDto(optionalEmployeeType.get());
                }
                throw new ApplicationException(String.format(userProperties.getEmployeeTypeNotAvailableForId(), id));
            }
            throw new ApplicationException(String.format(userProperties.getEmployeeTypeIdNotNull(), id));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeTypesNotAvailable(), e);
        }

    }

    /**
     * Creates a new employee type
     *
     * @param employeeTypeDTO EmployeeTypeDTO
     * @return An EmployeeTypeDTO
     */
    @Override
    public EmployeeTypeDTO createEmployeeType(EmployeeTypeDTO employeeTypeDTO) {
        try {
            EmployeeType employeeType = EmployeeTypeSupport.convertEmployeeTypeDtoToEmployeeTypeEntity(employeeTypeDTO);
            return EmployeeTypeSupport.convertEmployeeTypeEntityToEmployeeTypeDto(employeeTypeRepository.save(employeeType));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeTypeSaveFailed(), e);
        }

    }


    /**
     * Updates an existing employee type
     *
     * @param id              Id
     * @param employeeTypeDTO EmployeeTypeDTO
     * @return An EmployeeTypeDTO
     */
    @Override
    public EmployeeTypeDTO updateEmployeeType(Long id, EmployeeTypeDTO employeeTypeDTO) {
        try {
            // Check if the URL ID and payload ID match
            if (ObjectUtils.notEqual(id, employeeTypeDTO.getId())) {
                throw new ApplicationException(String.format(userProperties.getEmployeeTypeIdMismatch(), id, employeeTypeDTO.getId()));
            }
            EmployeeType existingEmployeeType = employeeTypeRepository.findById(id)
                    .orElseThrow(() -> new ApplicationException(String.format(userProperties.getEmployeeTypesNotAvailable())));
            EmployeeTypeSupport.updateEmployeeTypeFromDTO(existingEmployeeType, employeeTypeDTO);
            return EmployeeTypeSupport.convertEmployeeTypeEntityToEmployeeTypeDto(employeeTypeRepository.save(existingEmployeeType));
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeTypeSaveFailed(), e);
        }
    }

    /**
     * Soft delete an employee type
     *
     * @param id Id
     */
    @Override
    public void deleteEmployeeType(Long id) {
        try {
            Optional<EmployeeType> optionalEmployeeType = employeeTypeRepository.findById(id);
            // Check if the employee type data exists in the database
            if (optionalEmployeeType.isPresent()) {
                EmployeeType employeeType = optionalEmployeeType.get();
                employeeType.setIsActive(false);
                employeeTypeRepository.save(employeeType);
            } else {
                throw new ApplicationException(String.format(userProperties.getEmployeeTypeNotAvailableForId(), id));
            }
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeTypeNotAvailableForId(), e);
        }
    }


}
