package api;

import api.model.Developer;
import api.repository.DeveloperRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("api")
class ApiApplicationTests {

    private static Developer developer;

    @Autowired
    DeveloperRepository developerRepository;

    @BeforeAll
    static void setUp() {
        developer = new Developer(1L, "Misha", "Nozjkoitop@mail.ru");
    }

    @Test
    @Order(1)
    void testCreate() {
        Developer developerNew = new Developer();
        developerNew.setName("Vasya");
        developerNew.setEmail("VasyaVasin@yandex.ru");
        developerRepository.save(developerNew);
        developerRepository.save(developer);
        assertNotNull(developerRepository.findById(developerNew.getId()).get());
    }

    @Test
    @Order(2)
    void testGetAllDevelopers() {
        developerRepository.save(developer);
        List<Developer> developerList = developerRepository.findAll();
        assertThat(developerList).size().isPositive();
    }

    @Test
    @Order(3)
    void testReadDevelopersEmail() {
        developerRepository.save(developer);
        Developer developerToRead = developerRepository.findById(1L).get();
        assertEquals("Nozjkoitop@mail.ru", developer.getEmail());
    }

    @Test
    @Order(4)
    void findByName() {
        developerRepository.findByName("Misha");
        assertEquals("Nozjkoitop@mail.ru", developer.getEmail());
    }

}



