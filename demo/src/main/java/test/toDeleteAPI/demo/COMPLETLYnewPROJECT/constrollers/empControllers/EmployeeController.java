package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.constrollers.empControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.EmpModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService.EmployeeService;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository.UserRepositoryService;

import java.util.List;

@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getEmployees")
    public String users(Model model, @RequestParam(defaultValue = "0") int page){

        List<EmpModel> list = employeeService.employeeToEmpModelListMapper(employeeService.getAllEmployees());

        model.addAttribute("data", list);

        return "planet";
    }

    @GetMapping("/addEmployee")
    public String tw3(Model model){

        model.addAttribute("employeeForm", new EmpModel());

        return "addUser";
    }

    @GetMapping("/editUser/{id}")
    public String tw(Model model, @PathVariable Long id){

        model.addAttribute("edited", true);
        //userRepositoryService.findByID(id).ifPresent(user -> model.addAttribute("userForm", user));
        employeeService.getEmployeeByUserID(id).ifPresent(employee1 -> model.addAttribute("employeeForm", employeeService.employeeToEmpModelMapper(employee1)));

        return "addUser";
    }
}
