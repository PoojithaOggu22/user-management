package com.bilvantis.user.dao.data.repository;

import com.bilvantis.user.dao.data.model.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType,Long> {
}
