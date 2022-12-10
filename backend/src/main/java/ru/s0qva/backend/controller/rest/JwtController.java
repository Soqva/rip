package ru.s0qva.backend.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

import static java.util.stream.Collectors.joining;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/tokens")
@CrossOrigin
@SecurityRequirement(name = "token-access")
public class JwtController {
    private final JwtEncoder encoder;

    @PostMapping
    public String token(Authentication authentication) {
        var claims = getJwtClaimsSet(authentication);

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    private JwtClaimsSet getJwtClaimsSet(Authentication authentication) {
        var startDate = Instant.now();
        var expiryTimeSeconds = 36000L;
        var scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(joining(" "));
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(startDate)
                .expiresAt(startDate.plusSeconds(expiryTimeSeconds))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
    }
}
