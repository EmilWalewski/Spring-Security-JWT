package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.securityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.method.annotation.AuthenticationPrincipalArgumentResolver;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.templateresolver.ITemplateResolver;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.exceptionsHandlers.JWTAuthenticationEntryPoint;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.exceptionsHandlers.RestAccessDeniedHandler;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTProperties;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtAuthentication.JWTTokenProvider;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.jwtFilter.JWTFilter;
import test.toDeleteAPI.demo.COMPLETLYnewPROJECT.userDetailsConfig.PrincipalDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PrincipalDetailsService userDetailsService;
    private JWTTokenProvider jwtTokenProvider;

    public SecurityConfig(PrincipalDetailsService userDetailsService, JWTTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public JWTFilter jwtFilter(){ return new JWTFilter(jwtTokenProvider);}

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "js/**", "css/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //.cors()
                //.and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(entryPoint()).accessDeniedHandler(accessDeniedHandler())
                .and()
                .formLogin()
                .loginPage("/index")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/out"))
                .deleteCookies(JWTProperties.AUTHORIZATION_HEADER)
                .clearAuthentication(true)
                .logoutSuccessUrl("/index")
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/signin**").permitAll()
                .antMatchers(HttpMethod.GET, "/home").permitAll()//.hasRole("USER")
                .antMatchers(HttpMethod.GET, "/bo").hasRole("ADMIN")
                .anyRequest().permitAll();
    }

//    @Bean
//    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver, SpringSecurityDialect sec) {
//        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver);
//        templateEngine.addDialect(sec); // Enable use of "sec"
//        return templateEngine;
//    }

    @Bean
    public JWTAuthenticationEntryPoint entryPoint(){
        return new JWTAuthenticationEntryPoint();
    }

    @Bean
    public RestAccessDeniedHandler accessDeniedHandler(){
        return new RestAccessDeniedHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(userDetailsService);
        dao.setPasswordEncoder(passwordEncoder());

        return dao;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

}
