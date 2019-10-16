package test.toDeleteAPI.demo.security;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class Cors extends CorsFilter {

    public Cors() {
        super(urlBasedCorsConfigurationSource());
        System.out.println("C   O   R   S");
    }

    private static UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedHeader("Authorization");

        corsConfiguration.addAllowedOrigin("*");

        corsConfiguration.addAllowedMethod(HttpMethod.POST);
        corsConfiguration.addAllowedMethod(HttpMethod.GET);
        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);

        UrlBasedCorsConfigurationSource basedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        basedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return basedCorsConfigurationSource;
    }
}
