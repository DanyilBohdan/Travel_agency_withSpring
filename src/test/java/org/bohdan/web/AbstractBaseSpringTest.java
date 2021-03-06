package org.bohdan.web;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

import static org.bohdan.db.DBManager.getDataSource;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration
public abstract class AbstractBaseSpringTest {

    @Autowired
    protected MockMvc mockMvc;

    @Configuration
    @EnableWebMvc
    @EnableTransactionManagement
    @ComponentScan("org.bohdan")
    protected static class TestContextConfiguration {

        private static final Logger logger = Logger.getLogger(TestContextConfiguration.class);

        @Bean
        public MockMvc mockMvc(WebApplicationContext webApplicationContext) {
            return MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .apply(SecurityMockMvcConfigurers.springSecurity())
                    .alwaysDo(print())
                    .build();
        }

        @Bean(name = "jdbcTransactionManager")
        public static DataSourceTransactionManager transactionManager(){
            JdbcTransactionManager transactionManager = new JdbcTransactionManager();

            transactionManager.setDataSource(getDataSource());
            return transactionManager;
        }
    }

}
