package org.bohdan.configs;

import org.bohdan.db.ConnectionFactory;
import org.bohdan.db.ConnectionPool;
import org.bohdan.db.DBManager;
import org.bohdan.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    private UserDetailsService userDetailsService;

    private AuthenticationSuccessHandler authSuccessHandler;

    private PasswordEncoder customEncoderPassword;

    @Autowired
    public SecurityConfig(ConnectionFactory connectionFactory,
                          @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                          AuthenticationSuccessHandler authSuccessHandler, PasswordEncoder customEncoderPassword) {
        if (connectionFactory.getClass() == ConnectionPool.class) {
            this.dataSource = ConnectionPool.getDataSource();
        }
        if (connectionFactory.getClass() == DBManager.class) {
            this.dataSource = DBManager.getDataSource();
        }
        this.userDetailsService = userDetailsService;
        this.authSuccessHandler = authSuccessHandler;
        this.customEncoderPassword = customEncoderPassword;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/tours/admin/view", "/tours/admin/tour/editView", "/tours/admin/tour/edit",
                        "/tours/admin/tour/delete", "/tours/admin/tour/createView", "/tours/admin/tour/create",
                        "/user/admin/account", "/user/admin/account", "/user/admin/account", "/order/view", "/order/view/update/status",
                        "/order/view/update/discount", "/order/view/delete", "/order/view/searchByStatus", "/user/list,/user/list/search",
                        "/user/update/role", "/user/update/status").hasRole(Role.ADMIN.getName())
                .antMatchers("/user/account", "/order/canceled", "/order/register/view",
                        "/order/register", "/user/view/account", "/tours/view", "/tours/view/tour", "/user/registerCheck").hasRole(Role.USER.getName())
                .antMatchers("/user/registerCheck", "/user/login", "/user/logout", "/user/account/edit/view",
                        "/user/account",  "/tours/view", "/tours/view/tour").permitAll()
                .antMatchers("/", "/user/register", "/user/registerActive", "/user/registerCheck").anonymous()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(authSuccessHandler)
                .and().logout().logoutUrl("/user/logout");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(customEncoderPassword);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

}