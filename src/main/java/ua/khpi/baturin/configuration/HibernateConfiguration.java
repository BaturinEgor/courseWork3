package ua.khpi.baturin.configuration;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:h2.properties")
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean initSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource hibernateConfig = resourceLoader
                .getResource("classpath:hibernate.cfg.xml");
        sessionFactory.setConfigLocation(hibernateConfig);
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String driver = environment.getRequiredProperty("jdbc.driver");
        dataSource.setDriverClassName(driver);
        String url = environment.getRequiredProperty("jdbc.url");
        dataSource.setUrl(url);
        String uname = environment.getRequiredProperty("jdbc.username");
        dataSource.setUsername(uname);
        String pswd = environment.getRequiredProperty("jdbc.password");
        dataSource.setPassword(pswd);
        return dataSource;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager initTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public IDatabaseTester databaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }

}
