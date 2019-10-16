package test.toDeleteAPI.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import test.toDeleteAPI.demo.jwtdispatcher.model.LoginModel;;
import test.toDeleteAPI.demo.userPojo.userPojo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Controller
public class thymeleafController {

    List<LoginModel> list = new ArrayList<>();

    @GetMapping("/hello")
    public String hell(Model model){

        LoginModel loginModel = new LoginModel();
        loginModel.setUsername("adam");
        loginModel.setPassword("kowalski");

        LoginModel loginModel2 = new LoginModel();
        loginModel2.setUsername("sebastian");
        loginModel2.setPassword("nowak");

        LoginModel loginModel3 = new LoginModel();
        loginModel3.setUsername("acki");
        loginModel3.setPassword("backi");


//        list.add(loginModel);
//        list.add(loginModel2);
//        list.add(loginModel3);

        model.addAttribute("name","tw bolek");
        //model.addAttribute("user", loginModel);

        model.addAttribute("loginTemplate", new LoginModel());
        model.addAttribute("list", list);

        return "index";
    }

    @GetMapping("/login")
    public String log(Model model){

       return "login";
    }

    @GetMapping("/tw")
    public String tw(Model model){

        return "planet";
    }

    @PostMapping("/add-user")
    public String adduser(@Valid @ModelAttribute LoginModel loginmodel){
       list.add(loginmodel);
        return "redirect:hello";
    }



    @GetMapping("/")
    public String showpage(Model model, @RequestParam(defaultValue = "0") int page){

   //     model.addAttribute("data", repo.findAll(PageRequest.of(page, 4)));
        model.addAttribute("currentPage", page);
        return "planet";
    }




}
