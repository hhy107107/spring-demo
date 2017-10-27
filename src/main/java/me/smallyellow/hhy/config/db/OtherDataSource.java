package me.smallyellow.hhy.config.db;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;

@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OtherDataSource {
	public DatabaseType dataType() default DatabaseType.test2;
}
