package com.bilvantis.user.dao.data.repository;

import com.bilvantis.user.dao.data.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>, CrudRepository<Employee, Long>,
        JpaSpecificationExecutor<Employee> {
    Page<Employee> findAll(Pageable pageable);

    boolean existsByEmployeeId(String employeeId);

    Optional<Employee> findByEmployeeId(String employeeId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"team", "employeeType"})
    List<Employee> findByManagerId(Long managerId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"team", "employeeType"})
    List<Employee> findByEmployeeType_IdIn(List<Long> employeeTypeId);

}

