package api.service;


import api.dto.DeveloperDto;
import api.exception.DeveloperNotFoundException;
import api.exception.IncorrectDataException;
import api.model.Developer;
import api.repository.DeveloperRepository;
import api.util.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@RequiredArgsConstructor

public class DeveloperService {


    private final Validator validator;

    private final DeveloperRepository developerRepository;

    public void delete(String name) {
        developerRepository.deleteByName(name);
    }

    public ResponseEntity<Developer> search(@PathVariable String name) {
        Developer developer = developerRepository.findByName(name);
        if (developer != null) {
            return ResponseEntity.ok(developer);
        } else {
            throw new DeveloperNotFoundException("Not Found");
        }
    }

    public List<Developer> findAll() {
        return developerRepository.findAll();
    }

    public ResponseEntity<Developer> create(@RequestBody DeveloperDto developerDto) {
        if (developerRepository.findByName(developerDto.getName()) == (null) && validator.nameValidator(developerDto.getName())) {
            Developer developer = new Developer();
            developer.setName(developerDto.getName());
            developer.setEmail(developerDto.getEmail());
            developerRepository.save(developer);
            return ResponseEntity.ok(developer);
        } else {
            throw new IncorrectDataException("Already exists or incorrect name format");
        }
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





