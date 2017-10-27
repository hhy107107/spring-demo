package me.smallyellow.hhy.config.db;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component
public class DynamicDataSourceAspect {
	
	@Before("@annotation(otherDataSource)")
    public void changeDataSource(JoinPoint point, OtherDataSource otherDataSource){
       //获取当前的指定的数据源;
		DatabaseType dType = otherDataSource.dataType();
		DatabaseContextHolder.setDatabaseType(dType);
    }
	
}
