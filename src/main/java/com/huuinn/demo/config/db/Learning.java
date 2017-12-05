package com.huuinn.demo.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryLearning",
        transactionManagerRef = "transactionManagerLearning",
        basePackages = {"com.huuinn.demo.sample.db"}
)
public class Learning {

    @Autowired
    @Qualifier("learningDataSource")
    private DataSource learningDataSource;

    @Autowired
    private Environment env;

    @Bean(name = "entityManagerFactoryLearning")
    public LocalContainerEntityManagerFactoryBean learningEntityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(learningDataSource);
        bean.setPackagesToScan("com.huuinn.demo.sample.db");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        bean.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("spring.jpa.hibernate.ddl-auto"));
        properties.put("hibernate.dialect",
                env.getProperty("spring.jpa.hibernate.dialect"));

        bean.setJpaPropertyMap(properties);

        return bean;
    }

    @Bean(name = "transactionManagerLearning")
    public PlatformTransactionManager learningTransactionManager() {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory((learningEntityManager()).getObject());

        return transactionManager;
    }
}
