package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.constrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.cookie.CookieProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTAuthenticationResponse;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTProperties;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTTokenProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.LoginModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenProvider jwtTokenProvider;

    @Autowired
    Environment environment;

    @PostMapping("/auth/signin")
    public void authentication(@ModelAttribute LoginModel loginModel, HttpServletResponse response){

        CookieProvider.generateCookie(response, new JWTAuthenticationResponse(jwtTokenProvider.generateToken(authenticationManager
             .authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword())))).getAccessToken());


        try {
            response.sendRedirect(environment.getProperty("server.context.path"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    @PostMapping("/auth/signin")
//    public ResponseEntity authentication( @ModelAttribute LoginModel loginModel){
//
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(
////                        loginModel.getUsername(),
////                        loginModel.getPassword()
////                )
////        );
////
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        String jwt = jwtTokenProvider.generateToken(authentication);
////        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
//
//        return ResponseEntity.ok(new JWTAuthenticationResponse(jwtTokenProvider.generateToken(authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword())))));
//    }


}
