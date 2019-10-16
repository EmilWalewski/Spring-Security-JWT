package test.toDeleteAPI.demo.jwtdispatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import test.toDeleteAPI.demo.jwtdispatcher.model.LoginModel;
import test.toDeleteAPI.demo.service.principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class jwtauthentication extends UsernamePasswordAuthenticationFilter{

    private AuthenticationManager menager;

    public jwtauthentication(AuthenticationManager menager){
        this.menager = menager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        LoginModel model = null;

        try{
            model = new ObjectMapper().readValue(request.getInputStream(), LoginModel.class);
        }catch (Exception e){}


            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    model.getUsername(),
                    model.getPassword(),
                    new ArrayList<>()
            );

            Authentication auth = menager.authenticate(usernamePasswordAuthenticationToken);

            return auth;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

            principal principal = (principal) authResult.getPrincipal();

//            String token = JWT.create()
//                    .withSubject(principal.getUsername())
//                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtproperties.EXPIRE_TIME))
//                    .sign(Algorithm.HMAC512(jwtproperties.SECRET.getBytes()));

            //doczytac
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
//        response.addHeader("Access-Control-Allow-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");


       //     response.addHeader(jwtproperties.HEADER_PROPERTY, jwtproperties.HEADER_PREFIX+token);
    }
}
