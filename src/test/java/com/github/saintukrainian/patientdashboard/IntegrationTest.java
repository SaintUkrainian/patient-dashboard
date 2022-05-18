package com.github.saintukrainian.patientdashboard;

import com.github.saintukrainian.patientdashboard.config.DockerInitializationConfiguration;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootTest(classes = DockerInitializationConfiguration.class)
public @interface IntegrationTest {

}
