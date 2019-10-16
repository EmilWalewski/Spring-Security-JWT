package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.cookie;

import org.springframework.web.util.WebUtils;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTProperties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieProvider {

    public static void generateCookie(HttpServletResponse response, String token){

        Cookie cookie = new Cookie(JWTProperties.AUTHORIZATION_HEADER,token);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        cookie.setDomain("localhost");
        cookie.setPath("/");

        response.addCookie(cookie);
    }

    public static void clearCookie(HttpServletResponse response){

        Cookie cookie = new Cookie(JWTProperties.AUTHORIZATION_HEADER, null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }

    public static String getTokenFromCookie(HttpServletRequest request){

        return WebUtils.getCookie(request, JWTProperties.AUTHORIZATION_HEADER) != null ?
                WebUtils.getCookie(request, JWTProperties.AUTHORIZATION_HEADER).getValue() : null;

    }
}
