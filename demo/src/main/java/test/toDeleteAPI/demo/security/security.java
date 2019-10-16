package test.toDeleteAPI.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import test.toDeleteAPI.demo.jwtdispatcher.jwtauthentication;
import test.toDeleteAPI.demo.jwtdispatcher.jwtfilter;

import java.util.Arrays;

//@Configuration
//@EnableWebSecurity
public class security extends WebSecurityConfigurerAdapter {

    private UserDetailsService detailsService;

    public security(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("us")
//                .password(passwordEncoder().encode("bolek"))
//                .roles("USER");

        auth.authenticationProvider(daoAuthenticationProvider());
        //auth.userDetailsService(detailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new jwtauthentication(authenticationManager()))
                //.addFilter(new jwtfilter(authenticationManager(), service))
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users").hasAnyAuthority("ROLE_USER, save")
                .antMatchers(HttpMethod.GET, "/hello").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();

    }


    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

     @Bean
     PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
//        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200/"));
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }


            // sprawdza przychodzace requesty
    //@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");
        config.addAllowedHeader("*");
        config.addAllowedMethod(HttpMethod.OPTIONS);
        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.PUT);
        config.addAllowedMethod(HttpMethod.DELETE);
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}

