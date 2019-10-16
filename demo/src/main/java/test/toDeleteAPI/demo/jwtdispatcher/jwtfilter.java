package test.toDeleteAPI.demo.jwtdispatcher;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import test.toDeleteAPI.demo.service.principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class jwtfilter extends BasicAuthenticationFilter {



    public jwtfilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(jwtproperties.HEADER_PROPERTY);

        if (header == null || !header.startsWith(jwtproperties.HEADER_PREFIX)){
            chain.doFilter(request, response);
            return;
        }

        Authentication auth = getuser(request);

        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);

    }

    private Authentication getuser(HttpServletRequest request) {

        String header = request.getHeader(jwtproperties.HEADER_PROPERTY);

        if (header != null) {

//            String token = JWT.require(Algorithm.HMAC512(jwtproperties.SECRET.getBytes()))
//                    .build()
//                    .verify(header.replace(jwtproperties.HEADER_PREFIX, ""))
//                    .getSubject();

//            if (token != null) {
//                userPojo user = service.getuser(token);
//                principal principal = new principal(user);
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(token, null, principal.getAuthorities());
//                return auth;
//            }

            return null;
        }
        return null;
    }


}
