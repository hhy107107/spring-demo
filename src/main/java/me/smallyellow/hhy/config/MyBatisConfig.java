package me.smallyellow.hhy.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import me.smallyellow.hhy.config.db.DatabaseType;
import me.smallyellow.hhy.config.db.DynamicDataSource;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MyBatisConfig implements EnvironmentAware{

	@Autowired
	private DBConfig dbConfig;
	
	private Environment environment;
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.environment = arg0;
	}

	 /**
     * 创建数据源(数据源的名称：方法名可以取为XXXDataSource(),XXX为数据库名称,该名称也就是数据源的名称)
     */
    @Bean
    public DataSource test1DataSource() throws Exception {
        Properties props = new Properties();
//        props.put("driverClassName", environment.getProperty("test1-datasource.driverClassName"));
//        props.put("url", environment.getProperty("test1-datasource.url"));
//        props.put("username", environment.getProperty("test1-datasource.username"));
//        props.put("password", environment.getProperty("test1-datasource.password"));
	      props.put("driverClassName", dbConfig.getTest1DriverClassName());
	      props.put("url", dbConfig.getTest1Url());
	      props.put("username", dbConfig.getTest1Username());
	      props.put("password", dbConfig.getTest1Password());
        return DruidDataSourceFactory.createDataSource(props);
    }
    
    @Bean
    public DataSource test2DataSource() throws Exception {
        Properties props = new Properties();
//        props.put("driverClassName", environment.getProperty("test2-datasource.driverClassName"));
//        props.put("url", environment.getProperty("test2-datasource.url"));
//        props.put("username", environment.getProperty("test2-datasource.username"));
//        props.put("password", environment.getProperty("test2-datasource.password"));
	      props.put("driverClassName", dbConfig.getTest2DriverClassName());
	      props.put("url", dbConfig.getTest2Url());
	      props.put("username", dbConfig.getTest2Username());
	      props.put("password", dbConfig.getTest2Password());
        return DruidDataSourceFactory.createDataSource(props);
    }
    
    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("test1DataSource") DataSource test1DataSource,
                                        @Qualifier("test2DataSource") DataSource test2DataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.test1, test1DataSource);
        targetDataSources.put(DatabaseType.test2, test2DataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(test1DataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }
    
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("me.smallyellow.hhy.mapper");
        Properties props = new Properties();
        props.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
        props.setProperty("IDENTITY", "MYSQL");
        props.setProperty("notEmpty", "true");
        scannerConfigurer.setProperties(props);
        return scannerConfigurer;
    }
    
    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage("me.smallyellow.hhy.model");// 指定基包
        fb.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));//

        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    
}
