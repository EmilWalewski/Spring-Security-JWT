package test.toDeleteAPI.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import test.toDeleteAPI.demo.jwtdispatcher.model.LoginModel;
import test.toDeleteAPI.demo.userPojo.userPojo;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FrontController {



    @GetMapping(value = "/home", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> home(){


        return new ResponseEntity<>("Home",HttpStatus.OK);
    }


    @GetMapping("/users/{name}")
    private ResponseEntity<String> user(@PathVariable("name") String username, HttpRequest request){



        HttpHeaders headers = new HttpHeaders();
        //headers.add("location", ServletUriComponentsBuilder.fromCurrentRequest().path("/{name}").buildAndExpand(username).toUri().toString());

        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

    @PostMapping("/adduser")
    private ResponseEntity<String> adduser(@Valid @RequestBody LoginModel model){


            return new ResponseEntity<>("alraight", HttpStatus.OK);
    }
}
