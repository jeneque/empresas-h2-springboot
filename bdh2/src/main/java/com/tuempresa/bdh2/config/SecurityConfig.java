package com.tuempresa.bdh2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("usuario")
                .password("{noop}contraseña") // {noop} para indicar que no se utilizará codificación en la contraseña (solo para fines de prueba)
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().and().authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // Permitir acceso a la consola de H2 sin autenticación
                .antMatchers("/empresas").permitAll() // Permitir acceso sin autenticación para registrar empresas
                .anyRequest().authenticated() // Restringir acceso a otras rutas, requieren autenticación
                .and()
                .httpBasic();
        
        // Desactivar CSRF (Cross-Site Request Forgery) para que funcione la consola de H2
        http.csrf().disable();
        
        // Permitir que la consola de H2 se muestre en un iframe
        http.headers().frameOptions().sameOrigin();
    }

}