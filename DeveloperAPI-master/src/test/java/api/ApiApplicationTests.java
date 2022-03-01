package api;

import api.model.Developer;
import api.repository.DeveloperRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ComponentScan("api")
class ApiApplicationTests {

    @Autowired
    DeveloperRepository developerRepository;

    @Test
    @Order(1)
    public void testCreate() {
        Developer developer = new Developer();
        developer.setName("Vasya");
        developer.setEmail("VasyaVasin@yandex.ru");
        developerRepository.save(developer);
        assertNotNull(developerRepository.findById(developer.getId()).get());
    }

    @Test
    @Order(2)
    public void testGetAllDevelopers() {
        List<Developer> developerList = developerRepository.findAll();
        assertThat(developerList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testRead() {
        Developer developer = developerRepository.findById(1L).get();
        assertEquals("Nozjkoitop@mail.ru", developer.getEmail());
    }

    @Test
    @Order(4)
    public void testSearchByEmail() {
        developerRepository.findDeveloperByEmail("Nozjkoitop@mail.ru");
        assertEquals("Misha", developerRepository.findById(1L).get().getName());
    }

}



