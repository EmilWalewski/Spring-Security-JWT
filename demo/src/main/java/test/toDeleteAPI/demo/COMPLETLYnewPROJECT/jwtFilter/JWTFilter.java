package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.cookie.CookieProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTProperties;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTTokenProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig.PrincipalDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    PrincipalDetailsService principalDetailsService;

    private JWTTokenProvider jwtTokenProvider;

    public JWTFilter(JWTTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
             FilterChain filterChain) throws ServletException, IOException {

        String token = jwtTokenProvider.resolveToken(CookieProvider.getTokenFromCookie(httpServletRequest));

        if (token != null && jwtTokenProvider.validateToken(token, httpServletResponse)){

            //userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

//            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
//                    jwtTokenProvider.getUsernameFromToken(token), null, jwtTokenProvider.getRoles(token)));

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    principalDetailsService.loadUserByUsername(jwtTokenProvider.getUsernameFromToken(token)),
                    null, jwtTokenProvider.getRoles(token));

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
//        else
//        httpServletResponse.addHeader("LogError", "Session expired");

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
