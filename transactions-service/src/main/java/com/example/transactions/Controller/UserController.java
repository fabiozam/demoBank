package com.example.transactions.Controller;

import com.example.transactions.Entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

    @PostMapping
    public User login(@RequestParam("user") String userName, @RequestParam("password") String password) {
        log.info("Getting token for User: {}", userName);
        String token = getJWTToken(userName);
        User user = new User();
        user.setUsername(userName);
        user.setUserToken(token);
        log.info("Token generated: {}", token);
        return user;
    }

    private String getJWTToken(String userName) {
        String secretKey = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(userName)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
