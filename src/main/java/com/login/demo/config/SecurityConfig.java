package com.login.demo.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.login.demo.config.handler.CustomAuthenticationEntryPoint;
import com.login.demo.config.handler.RestAccessDeniedHandler;
import com.login.demo.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] SWAGGER_UI_URL = { //
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/demo/",
            "/communication/",
            "/info/",
            "/images/*",
            "/images//",
            "/webjars/**",
            "/csrf"
    };
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    RestAccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler(mapper);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                //будет управлять сессией юзера в системе спринг секюрит
                // Так как я буду авторизировать пользователя по токену, мне не нужно создавать и хранить для него сессию.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/super-admin/*").hasRole("SUPER_ADMIN")
//                .antMatchers("/api/admin/*").hasAnyRole("ADMIN", "SUPER_ADMIN")
//                .antMatchers("/api/user/*").hasAnyRole("ADMIN", "USER","SUPER_ADMIN")
//
//                .antMatchers(SWAGGER_UI_URL).permitAll()
//
//                .antMatchers("/api/auth/register/**").permitAll()
//                .antMatchers("/api/auth/auth/**").permitAll()
                // TODO: for test
                // index page
               /// .antMatchers(HttpMethod.GET, "/").permitAll()

                .antMatchers("/logins").permitAll()
                .antMatchers("/register").permitAll()

                .antMatchers("/user/*").hasAnyRole("ADMIN", "USER", "SUPER_ADMIN")
                .antMatchers("/admin*").hasAnyRole("ADMIN", "SUPER_ADMIN")
                .antMatchers("/super-admin*").hasRole("SUPER_ADMIN")

                .antMatchers(SWAGGER_UI_URL).permitAll()

                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
