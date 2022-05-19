package com.github.saintukrainian.patientdashboard.config;

import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@Slf4j
@TestConfiguration
public class DockerInitializationConfiguration {

  private static final int POSTGRES_PORT = 5432;
  private static final String POSTGRESQL_DRIVER_CLASS_NAME = "org.postgresql.Driver";
  private static final String POSTGRES_IMAGE_NAME = "postgres";
  private static final String POSTGRES_USERNAME = POSTGRES_IMAGE_NAME;
  private static final String POSTGRES_PASSWORD = "00zomifi";
  private static final String POSTGRES_DATABASE_NAME = "patient-dashboard";
  private static final String POSTGRES_DB_LABEL = "POSTGRES_DB";
  private static final String POSTGRES_PASSWORD_LABEL = "POSTGRES_PASSWORD";
  private static final String SCRIPTS_SOURCE = "scripts/.";
  private static final String POSTGRES_INSIDE_CONTAINER_SOURCE = "/docker-entrypoint-initdb.d/";

  private GenericContainer postgres = new GenericContainer(DockerImageName.parse(
      POSTGRES_IMAGE_NAME))
      .withExposedPorts(POSTGRES_PORT)
      .withEnv(Map.of(POSTGRES_DB_LABEL, POSTGRES_DATABASE_NAME))
      .withEnv(Map.of(POSTGRES_PASSWORD_LABEL, POSTGRES_PASSWORD))
      .withClasspathResourceMapping(SCRIPTS_SOURCE, POSTGRES_INSIDE_CONTAINER_SOURCE,
          BindMode.READ_WRITE);

  @Bean
  public DataSource getDataSource() {
    // viewing mapped port
    Integer mappedPort = postgres.getMappedPort(POSTGRES_PORT);
    log.info("Mapped port: {}", mappedPort);

    // setting datasource url value dynamically
    String datasourceUrl = "jdbc:postgresql://localhost:" + mappedPort
        + "/patient-dashboard?useSSL=false&serverTimezone=UTC";

    return DataSourceBuilder.create()
        .driverClassName(POSTGRESQL_DRIVER_CLASS_NAME)
        .url(datasourceUrl)
        .username(POSTGRES_USERNAME)
        .password(POSTGRES_PASSWORD)
        .build();
  }

  @PostConstruct
  public void startContainer() {
    postgres.start();
  }

  @PreDestroy
  public void stopContainer() {
    log.info("Stopping container: {}", postgres.getContainerId());
    postgres.stop();
  }
}
