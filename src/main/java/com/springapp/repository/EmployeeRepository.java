package com.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springapp.dto.EmployeeProjection;
import com.springapp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    // 1. Derived query by eName
    List<Employee> findByEName(String eName);

    // 2. Derived query by department
    List<Employee> findByDepartment(String department);

    // 3. Derived with comparison
    List<Employee> findBySalaryGreaterThan(int salary);

    // 4. Find by email
    Optional<Employee> findByEmail(String email);

    // 5. Existence and count
    boolean existsByEmail(String email);
    long countByDepartment(String department);

    // 6. JPQL query (select full entity)
    @Query("SELECT e FROM Employee e WHERE e.eName = :name")
    List<Employee> fetchByNameJPQL(@Param("name") String name);

    // 7. JPQL projection (select subset fields into interface projection)
    @Query("SELECT e.empId as empId, e.eName as eName, e.department as department FROM Employee e WHERE e.department = :dept")
    List<EmployeeProjection> fetchProjectionByDepartment(@Param("dept") String department);

    // 8. Native query
    @Query(value = "SELECT * FROM employee e WHERE e.salary > :minSalary", nativeQuery = true)
    List<Employee> fetchByMinSalaryNative(@Param("minSalary") int minSalary);

    // 9. Paging & sorting
    Page<Employee> findAll(Pageable pageable);

    // 10. Custom update using @Modifying
    @Modifying
    @Query("UPDATE Employee e SET e.salary = :salary WHERE e.empId = :id")
    int updateSalaryById(@Param("id") String empId, @Param("salary") int salary);

    // 11. Delete by email (derived)
    void deleteByEmail(String email);
}
