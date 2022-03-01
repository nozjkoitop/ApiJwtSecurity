package api.service;


import api.dto.DeveloperDto;
import api.exception.CustomException;
import api.exception.DeveloperNotFoundException;
import api.model.Developer;
import api.repository.DeveloperRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@RequiredArgsConstructor

public class DeveloperService {

    private final DeveloperRepository developerRepository;

    public void delete(String Name) {
        developerRepository.deleteByName(Name);
    }

    public Developer search(String Name) {
        Developer developer = developerRepository.findByName(Name);
        if (developer == null) {
            throw new CustomException("Developer doesn't exist", HttpStatus.NOT_FOUND);
        }
        return developer;
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public Developer create(@RequestBody DeveloperDto developerDto) {
        Developer developer = new Developer();
        developer.setName(developerDto.getName());
        developer.setEmail(developerDto.getEmail());
        developerRepository.save(developer);
        return developer;
    }

    public ResponseEntity<Developer> updateDeveloper(@PathVariable long id, @RequestBody DeveloperDto developerDto) {
        Developer updateDeveloper = developerRepository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException("Developer doesn't exist with id: " + id));
        updateDeveloper.setName(developerDto.getName());
        updateDeveloper.setEmail(developerDto.getEmail());
        developerRepository.save(updateDeveloper);
        return ResponseEntity.ok(updateDeveloper);
    }

}





