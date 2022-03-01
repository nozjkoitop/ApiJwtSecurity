package api.dto;

import api.model.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeveloperDto {
    @ApiModelProperty(position = 3)
    List<Role> roles;
    @ApiModelProperty()
    private Integer id;
    @ApiModelProperty(position = 1)
    private String name;
    @ApiModelProperty(position = 2)
    private String email;
}