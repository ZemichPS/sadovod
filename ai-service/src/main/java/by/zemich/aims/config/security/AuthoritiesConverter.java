package by.zemich.aims.config.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class AuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = source.getClaimAsMap("realm_access");
        if (Objects.nonNull(realmAccess)) {
            List<String> roles = (List<String>) realmAccess.get("roles");
            if (Objects.nonNull(roles)) {
                return roles.stream()
                        .map(rn -> new SimpleGrantedAuthority("ROLE_" + rn))
                        .collect(Collectors.toList());
            }
        }
        return List.of();
    }


    public Collection<SimpleGrantedAuthority> convert2(Jwt source) {
        final List<SimpleGrantedAuthority> simpleGrantedAuthorityList = Optional.ofNullable(source.getClaimAsMap("realm_access"))
                .map(roleMap -> roleMap.values().stream()
                        .map(String::valueOf)
                        .map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                        .collect(Collectors.toList()))
                .orElse(List.of());

        return List.of();
    }

}
