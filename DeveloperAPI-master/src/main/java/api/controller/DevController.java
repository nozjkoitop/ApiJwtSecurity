package api.controller;


import api.dto.DeveloperDto;
import api.exception.CustomException;
import api.model.Developer;
import api.model.JwtRequest;
import api.security.JwtTokenProvider;
import api.service.DeveloperService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = "developers")
public class DevController {

    @Autowired
    private final DeveloperService developerService;
    private final ModelMapper modelMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/developers")
    @PreAuthorize("hasAnyAuthority('USER','HR','ADMIN')")
    @ApiOperation(value = "${DevController.findAll}", authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Unauthorized"),//
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")}) //
    public List<Developer> getAllDevelopers() {
        return developerService.findAll();
    }


    @DeleteMapping(value = "/{name}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "${DevController.delete}", authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Unauthorized"),//
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(@ApiParam("name") @PathVariable String name) {
        developerService.delete(name);
        return name;
    }

    @GetMapping(value = "developers/{name}")
    @PreAuthorize("hasAnyAuthority('USER','HR','ADMIN')")
    @ApiOperation(value = "${DevController.search}", response = DeveloperDto.class, authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"), //
            @ApiResponse(code = 401, message = "Unauthorized"),//
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public DeveloperDto search(@ApiParam("name") @PathVariable String name) {
        return modelMapper.map(developerService.search(name), DeveloperDto.class);
    }

    @PostMapping(value = "developers/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "{$Developer's modifying}", authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {//
            @ApiResponse(code = 401, message = "Unauthorized"),//
            @ApiResponse(code = 403, message = "Access denied"), //
            @ApiResponse(code = 404, message = "The user doesn't exist"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public ResponseEntity<Developer> updateDeveloper(@PathVariable long id, @RequestBody DeveloperDto developerDto) {
        return developerService.updateDeveloper(id, developerDto);
    }

    @PostMapping(value = "/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "{$DevController.create}", authorizations = {@Authorization(value = "Authorization")})
    @ApiResponses(value = {//
            @ApiResponse(code = 400, message = "Something went wrong"),
            @ApiResponse(code = 401, message = "Unauthorized"),//
            @ApiResponse(code = 403, message = "Access denied"),
            @ApiResponse(code = 422, message = "Developer already exists"), //
            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public Developer developer(@RequestBody DeveloperDto developerDto) {
        if (!developerDto.getName().substring(0, 1).matches("[a-zA-Z]")) {
            throw new CustomException("Incorrect name", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return developerService.create(developerDto);
    }

    @PostMapping("/builder-jwt")
    @ApiOperation(value = "${Generate token}")
    public ResponseEntity<String> builderJWT(@RequestBody JwtRequest jwtRequest) {

        String token = jwtTokenProvider.createToken(jwtRequest);
        return ResponseEntity.ok(token);
    }


}
