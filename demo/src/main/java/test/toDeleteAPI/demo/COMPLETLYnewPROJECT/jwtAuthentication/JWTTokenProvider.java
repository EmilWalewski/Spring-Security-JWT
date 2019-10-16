package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.cookie.CookieProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.user.Role;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig.PrincipalDetails;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JWTTokenProvider {


    public String generateToken(Authentication authentication)  {

        Claims claims = Jwts.claims();
        claims.put("roles", authentication.getAuthorities());
        //claims.put("userId", ((PrincipalDetails)authentication.getPrincipal()).getUser().getUserID());


            //cele diagnostyczne - dwie petle - zostawic
//        ((Collection<? extends GrantedAuthority>) claims.get("roles")).stream().forEach(r -> System.out.println(r+" "+((GrantedAuthority) r).getAuthority()));
//
//        ((Collection<? extends GrantedAuthority>) claims.get("roles")).stream().forEach(r ->
//                authorities.add(((GrantedAuthority) r).getAuthority()));


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(((PrincipalDetails)authentication.getPrincipal()).getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWTProperties.EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS512, JWTProperties.SECRET.getBytes())
                .compact();
    }

    public String getUsernameFromToken(String token){

        return Jwts.parser()
                .setSigningKey(JWTProperties.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public Long getUserIdFromToken(String token){

        return (Long)Jwts.parser()
                .setSigningKey(JWTProperties.SECRET.getBytes())
                .parseClaimsJws(token)
                .getBody().get("userId");
    }



    public Collection<? extends GrantedAuthority> getRoles(String token){


        List<String> authorities = new ArrayList<String>();

        for (Map<String, String> roles : (List<Map<String, String>>) Jwts.parser()
                                                                    .setSigningKey(JWTProperties.SECRET.getBytes())
                                                                    .parseClaimsJws(token)
                                                                     .getBody().get("roles")){
            for ( Map.Entry rolesMapper : roles.entrySet()){
                authorities.add((String)rolesMapper.getValue());
            }
        }

                            //cele diagnostyczne - zostawic
//        ((List<GrantedAuthority>) claims.get("roles")).stream().forEach(r ->
//                authorities.add(((GrantedAuthority) r).getAuthority()));

        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());


    }

    public String resolveToken(String bearerToken) {
        //String bearerToken = request.getHeader(JWTProperties.AUTHORIZATION_HEADER);
        if (bearerToken != null /*&& bearerToken.startsWith(JWTProperties.TOKEN_BEARER)*/) {
            return bearerToken;
        }
       return null;
    }

    public boolean validateToken(String token, HttpServletResponse response){

        try {
            Jwts.parser().setSigningKey(JWTProperties.SECRET.getBytes()).parseClaimsJws(token);
            return true;

        } catch (JwtException | IllegalArgumentException ex) {

            CookieProvider.clearCookie(response);
        }


        return false;
    }


}
