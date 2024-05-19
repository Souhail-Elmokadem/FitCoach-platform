package com.fitcoach.fitcoach.securityConfig;




import com.fitcoach.fitcoach.dao.Repository.ClientRepository;
import com.fitcoach.fitcoach.dao.Repository.CoachRepository;
import com.fitcoach.fitcoach.dao.Repository.PersonRepository;
import com.fitcoach.fitcoach.dao.entity.Client;
import com.fitcoach.fitcoach.dao.entity.Coach;
import com.fitcoach.fitcoach.dao.entity.Person;
import com.fitcoach.fitcoach.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.web.bind.annotation.*;


import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityController {


    @Autowired
    private JwtEncoder jwtEncoder;


    @Autowired

    private JwtDecoder jwtDecoder;



    @Autowired

    private UserDetailsService userDetailsService;


    @Autowired

    private AuthenticationManager authenticationManager;

    JwtClaimsSet jwtClaimsSet,jwtClaimsSetRefresh;


    @Autowired

    private  PasswordEncoder passwordEncoder;

    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private ClientRepository clientRepository;


    @Autowired

    PersonRepository personRepository;
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        Instant now = Instant.now();
        Map<String, String> tokens = new HashMap<>();
        String subject = "";
        String scope = "";

        try {
            if ("pass".equals(loginRequest.getLogintype())) {
                Authentication authenticate = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
                subject = loginRequest.getEmail();
                scope = authenticate.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));
            } else if ("refreshToken".equals(loginRequest.getLogintype())) {
                Jwt jwtDecoded = jwtDecoder.decode(loginRequest.getRefreshToken());
                String email = jwtDecoded.getSubject();

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                scope = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
            }

            JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                    .issuedAt(now)
                    .expiresAt(now.plus(Duration.ofDays(31)))
                    .subject(subject)
                    .claim("scope", scope)
                    .build();

            JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS256).build(),
                    jwtClaimsSet
            );
            String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
            tokens.put("access-token", jwt);

            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .issuedAt(now)
                    .expiresAt(now.plus(Duration.ofDays(30)))
                    .subject(subject)
                    .build();
            JwtEncoderParameters jwtEncoderParametersRefresh = JwtEncoderParameters.from(
                    JwsHeader.with(MacAlgorithm.HS256).build(),
                    jwtClaimsSetRefresh
            );
            String jwtRefresh = jwtEncoder.encode(jwtEncoderParametersRefresh).getTokenValue();
            tokens.put("refresh-token", jwtRefresh);

            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public Map<String,String> Register(@RequestBody RegisterRequest request){
        var user = Person.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .avatar("http://localhost:9090/content/logo.png")
                .role(request.getRole())
                .createdAt(new Date())
                .updateAt(new Date())
                .build();


        if (request.getRole().equals(Role.COACH)) {
            coachRepository.save(
                    new Coach(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),"",user.getAvatar(),new Date(),new Date(),user.getPassword(),user.getRole()));

        }else if (request.getRole().equals(Role.USER)){
            clientRepository.save(
                    new Client(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail(),"",user.getAvatar(),new Date(),new Date(),user.getPassword(),user.getRole()));
        }
        Instant instant = Instant.now();
        jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(Duration.ofMinutes(10)))
                .subject(user.getUsername())
                .claim("scope",user.getAuthorities())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                jwtClaimsSet
        );
        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
        return Map.of("access-token",jwt);
    }

//    @PostMapping("/login")
//    public Map<String,String> Login(String LoginType,String username, String password ,String refresh_token){
//        String subject = "";
//        String scope = "";
//        Instant instant= Instant.now();
//        Map<String,String> tokens = new HashMap<>();
//        if (LoginType.equals("pass")){
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//            subject = username;
//            scope = authenticate.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        }else if(LoginType.equals("refreshToken")){
//            Jwt jwtDecoded = jwtDecoder.decode(refresh_token);
//            String email = jwtDecoded.getSubject();
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            scope = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
//        }
//        jwtClaimsSet = JwtClaimsSet.builder()
//                .issuedAt(instant)
//                .expiresAt(instant.plus(Duration.ofMinutes(1)))
//                .subject(subject)
//                .claim("scope",scope)
//                .build();
//        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
//                JwsHeader.with(MacAlgorithm.HS256).build(),
//                jwtClaimsSet
//        );
//        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
//        tokens.put("access-token",jwt);
//
//
//        jwtClaimsSetRefresh = JwtClaimsSet.builder()
//                .issuedAt(instant)
//                .expiresAt(instant.plus(Duration.ofMinutes(30)))
//                .subject(subject)
//                .build();
//        JwtEncoderParameters jwtEncoderParametersRefresh = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(),
//                jwtClaimsSetRefresh);
//        String jwtRefresh = jwtEncoder.encode(jwtEncoderParametersRefresh).getTokenValue();
//        tokens.put("refresh-token",jwtRefresh);
//
//
//        return tokens;
//    }

}
