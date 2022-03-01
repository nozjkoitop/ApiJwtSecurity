package api.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @Author : NozjkoiTop
 * @Date : Created in 12.02.2022
 */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEVELOPERS")
@ApiModel
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ApiModelProperty("Name is ->")
    @Size(min = 3, max = 50, message = "Minimum username length: 3 characters")
    @Column(name = "name")
    private String name;

    @ApiModelProperty("Email is ->")
    @Column(name = "email", unique = true)
    private String email;
}