package com.example.testcontainers;

import org.testcontainers.containers.PostgreSQLContainer;

public class AbstractContainerBaseTest {

    static final PostgreSQLContainer postgresSqlContainer;

    static{
        postgresSqlContainer=new PostgreSQLContainer("postgres:latest");
        postgresSqlContainer.start();

    }


}
