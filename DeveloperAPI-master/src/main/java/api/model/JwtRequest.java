package api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor

public class JwtRequest {
    private String issuer;
    private String subject;
    private List<Role> roles;
}

