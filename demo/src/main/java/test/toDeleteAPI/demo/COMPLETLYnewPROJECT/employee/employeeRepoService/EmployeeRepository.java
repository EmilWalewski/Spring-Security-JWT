package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("from Employee where account_id =:id")
    Optional<Employee> findEmployeeByUserID(@Param("id") Long id);
}
