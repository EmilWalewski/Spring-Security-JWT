package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.EmpModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    public List<Employee> getAllEmployees(){ return employeeRepository.findAll();}

    public Optional<Employee> getEmployee(Long id){ return employeeRepository.findById(id);}

    public Optional<Employee> getEmployeeByUserID(Long id){ return employeeRepository.findEmployeeByUserID(id);}


    /*
        rest controllers methods
     */
    public String saveEmployee(Employee employee){

        employeeRepository.save(employee);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "New employee "+employee.getName()+" "+employee.getSurname()+" has been added");

        return jsonObject.toString();
    }

    public String editEmployee(Employee employee){

        employeeRepository.save(employee);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Employee "+employee.getId()+" has been edited");

        return jsonObject.toString();
    }

    public String deleteEmployee(Long id){

        employeeRepository.deleteById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "Employee has been deleted");

        return jsonObject.toString();
    }


    /*
        convert methods
     */

    public List<EmpModel> employeeToEmpModelListMapper(List<Employee> optionalEmployee){
        return optionalEmployee.stream().map(EmpModel::new).collect(Collectors.toList());
    }

    public EmpModel employeeToEmpModelMapper(Employee emp){ return new EmpModel(emp);}

    public Employee empModelToEmployeeMapper(EmpModel empModel){ return new Employee(empModel); }
}
