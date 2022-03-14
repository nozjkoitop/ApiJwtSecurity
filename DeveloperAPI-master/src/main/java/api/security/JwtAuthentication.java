package api.security;

import api.model.AuthenticationModel;
import api.model.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtAuthentication {

    public static AuthenticationModel generate(Claims claims) {
        final AuthenticationModel jwtInfoToken = new AuthenticationModel();
        jwtInfoToken.setRoles(getRoles(claims));
        return jwtInfoToken;
    }

    private static List<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .toList();
    }
}