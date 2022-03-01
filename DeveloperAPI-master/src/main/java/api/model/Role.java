package api.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, HR, USER;

    public String getAuthority() {
        return name();
    }
}
