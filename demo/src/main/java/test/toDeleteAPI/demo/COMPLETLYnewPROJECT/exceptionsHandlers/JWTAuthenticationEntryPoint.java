package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.exceptionsHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Service;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.messageConfig.MessageConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

//401 unauthorized error

@Service
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    MessageConfig messageConfig;

        @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        if (e.getClass().getSimpleName().equals(BadCredentialsException.class.getSimpleName())) {
            httpServletRequest.getSession().setAttribute("wrong-credentials", messageConfig.getMessage("user.invalid.credentials"));
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/index");
        }

        if (e.getClass().getSimpleName().equals(InsufficientAuthenticationException.class.getSimpleName())){
            httpServletRequest.getSession().setAttribute("session-expired", messageConfig.getMessage("user.session.expired"));
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/index");
        }

        System.out.println("odwiedzamy entry point "+e.getClass().getSimpleName());
        //httpServletResponse.addHeader("LogError", "Session expired");
        //httpServletResponse.sendRedirect("/index");
        //httpServletResponse.sendError(HttpServletResponse.SC_FOUND,e.getMessage() );
        //httpServletResponse.flushBuffer();

    }
}
