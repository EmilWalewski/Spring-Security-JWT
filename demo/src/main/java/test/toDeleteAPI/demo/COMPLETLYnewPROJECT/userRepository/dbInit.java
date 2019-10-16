package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.function.SingletonSupplier;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Address;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Employee;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService.EmployeeService;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.LoginModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.Role;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.RoleName;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class dbInit implements CommandLineRunner {

    @Autowired
    private UserRepository userRepositoryService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeService employeeService;

    Employee emp;

    @Override
    public void run(String... args) throws Exception {

        roleRepository.deleteAll();
        userRepositoryService.deleteAll();


                //Dodajemy role do bazy
//        Role role1 = new Role(RoleName.ROLE_USER);
//        Role role2 = new Role(RoleName.ROLE_ADMIN);
//
//        roleRepository.save(role1);
//        roleRepository.save(role2);

                //Dodajemy userow do bazy
        Set<Role> userRole = new HashSet<>();
        roleRepository.findByRoleName(RoleName.ROLE_USER).ifPresent(role -> userRole.add(role));

        User user = new User("a",passwordEncoder.encode("a"), userRole);

        userRepositoryService.save(user);

        Set<Role> adminRole = new HashSet<>();
        roleRepository.findByRoleName(RoleName.ROLE_USER).ifPresent(role -> adminRole.add(role));
        roleRepository.findByRoleName(RoleName.ROLE_ADMIN).ifPresent(role -> adminRole.add(role));

        User admin = new User("b",passwordEncoder.encode("b"), adminRole);

        //userRepositoryService.save(admin);

                //Dodajemy pracownikow i przypisujemy i jedno konto w bazie
        Employee employee = new Employee();
        employee.setName("Adam");
        employee.setSurname("Kowalski");
        employee.setEmail("kowalski@gmail.com");
        employee.setPhoneNumber(500658936);

        Address address = new Address("zielna", "34a", "lomza", "18-400", "poland");
        employee.setAddress(address);
        employee.setUser(user);

        //userRepositoryService.findByUsername("a").ifPresent(user1 -> employee.setUser(user1));

        employeeService.saveEmployee(employee);
//
//
//        Employee employee2 = new Employee();
//        employee2.setName("Michal");
//        employee2.setSurname("Nowak");
//        employee2.setEmail("nowak@gmail.com");
//        employee2.setPhoneNumber(600658936);
//
//        Address address2 = new Address("czarna", "17", "grajweo", "20-400", "poland");
//        employee2.setAddress(address2);
//        employee2.setUser(admin);

        //userRepositoryService.findByUsername("a").ifPresent(user1 -> employee.setUser(user1));

        //employeeService.saveEmployee(employee2);



    }
}
