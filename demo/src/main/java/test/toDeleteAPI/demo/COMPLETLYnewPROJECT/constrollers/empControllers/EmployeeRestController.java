package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.constrollers.empControllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Address;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.EmpModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService.EmployeeService;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository.UserRepositoryService;

@RestController
public class EmployeeRestController {

    private EmployeeService employeeService;
    private UserRepositoryService userRepositoryService;

    public EmployeeRestController(EmployeeService employeeService, UserRepositoryService userRepositoryService) {
        this.employeeService = employeeService;
        this.userRepositoryService = userRepositoryService;
    }

    @PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addEmp(@RequestBody EmpModel empModel){

        System.out.println(empModel.getId()+" metoda dodajaca pracownika");

        userRepositoryService.findByID(2L).ifPresent(user ->  empModel.setUser(user));

        empModel.setAddress(new Address("111", "222", "333", "444", "555" ));

        return ResponseEntity.ok(employeeService.saveEmployee(employeeService.empModelToEmployeeMapper(empModel)));
    }

    @PostMapping(value = "/editEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> editEmp(@RequestBody EmpModel empModel){

        return ResponseEntity.ok(employeeService.saveEmployee(employeeService.empModelToEmployeeMapper(empModel)));
    }

    @PostMapping(value = "/deleteUser/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmp(@PathVariable("id") Long id){


//        employeeService.getEmployeeByUserID(id).ifPresent(employee -> {
//            employeeService.deleteEmployee(employee.getId());
//        });
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }


}
