package com.example.testcontainers;

import com.example.testcontainers.entity.Student;
import com.example.testcontainers.rep.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import java.util.List;

@SpringBootTest
        (
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
                properties = {}
        )
@AutoConfigureMockMvc
class TestcontainersApplicationTests extends AbstractContainerBaseTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllStudents_when_students_exist_return_all_students() throws Exception {


        //Arrange
        List<Student> studentList =
                List.of(Student.builder().name("rupesh").age("22").address("chabhail").build(),
                        Student.builder().name("lebron").age("36").address("usa").build());


        //Act
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/api/students"));

        //Assert
        response.andExpect(MockMvcResultMatchers.status().isOk());
    }


    //Customer container
    static GenericContainer<?> redis = new GenericContainer<>("redis:6-alpine").withExposedPorts(6379);

    static KafkaContainer kafkaContainer = new KafkaContainer(
            DockerImageName.parse("confluentic/cp-kafka:5.4.6")
    );

    @DynamicPropertySource
    public static void setupThings(DynamicPropertyRegistry registry) {
        Startables.deepStart(redis).join();

        registry.add("spring.redis.host", redis::getHost);
        registry.add("spring.redis.port", redis::getFirstMappedPort);
    }

}
