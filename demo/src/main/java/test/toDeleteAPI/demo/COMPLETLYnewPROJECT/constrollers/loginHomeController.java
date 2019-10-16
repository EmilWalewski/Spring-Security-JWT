package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.annotations.CurrentUser;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.cookie.CookieProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Address;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.EmpModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.Employee;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.employee.employeeRepoService.EmployeeService;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTTokenProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.LoginModel;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.User;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig.PrincipalDetails;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userRepository.UserRepositoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class loginHomeController{

    private UserRepositoryService userRepositoryService;

    public loginHomeController(UserRepositoryService userRepositoryService) {
       this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/index")
    public String login(Model model, HttpSession session, HttpServletRequest response){

        if(response.getHeader("User-Agent").contains("Edge")){
            model.addAttribute("sessionError", "Internet Explorer is not supported");
            model.addAttribute("ieDisable", true);
        }
        else{

        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) return "redirect:/home";


            if (session.getAttribute("wrong-credentials") != null) {
                model.addAttribute("sessionError", session.getAttribute("wrong-credentials"));
                session.removeAttribute("wrong-credentials");
            }

            if (session.getAttribute("session-expired") != null) {
                model.addAttribute("sessionError", session.getAttribute("session-expired"));
                session.removeAttribute("session-expired");
            }
        }

        model.addAttribute("loginTemplate", new LoginModel());
        return "index";
    }

    @GetMapping("/home")
    public String tw(/*@AuthenticationPrincipal(expression = "user")@CurrentUser User user,*/  Model model){

        //userRepositoryService.findByID(user.getUserID()).ifPresent(user1 -> model.addAttribute("helloNotification", "Hello "+user1.getEmployee().getName()));

        return "home";
    }

}
