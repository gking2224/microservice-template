package me.gking2224.mstemplate.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import me.gking2224.securityms.client.CommonSecurityConfiguration;
import me.gking2224.securityms.client.HttpSecurityConfigurer;

@Import(CommonSecurityConfiguration.class)
public class SecurityConfiguration {
    
    @Bean
    HttpSecurityConfigurer httpSecurityConfigurer() {
        return new HttpSecurityConfigurer() {

            @Override
            public void configure(final HttpSecurity http) throws Exception {
                http.authorizeRequests().antMatchers(HttpMethod.GET, "/things/secure").hasRole("SUPER_USER");
            }
            
        };
    }

}