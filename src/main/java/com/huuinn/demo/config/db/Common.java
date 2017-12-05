package com.huuinn.demo.config.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "entityManagerFactoryCommon",
        transactionManagerRef = "transactionManagerCommon",
        basePackages = {"com.huuinn.demo.ddd.common"}
)
public class Common {
    @Primary
    @Bean(name = "commonDataSource")
    @Qualifier("commonDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.common")
    public DataSource commonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Autowired
    private Environment env;

    @Primary
    @Bean(name = "entityManagerFactoryCommon")
    public LocalContainerEntityManagerFactoryBean commonEntityManager() {
        LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setDataSource(commonDataSource());
        bean.setPackagesToScan("com.huuinn.demo.ddd.common");

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

    @Primary
    @Bean(name = "transactionManagerCommon")
    public PlatformTransactionManager commonTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory((commonEntityManager()).getObject());

        return transactionManager;
    }
}
