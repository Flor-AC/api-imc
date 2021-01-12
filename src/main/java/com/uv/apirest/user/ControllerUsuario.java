package com.uv.apirest.user;

import com.uv.apirest.user.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.uv.apirest.user.IRepositoryUsuario;

@RestController
@EnableAutoConfiguration
@CrossOrigin()
public class ControllerUsuario {
    
    @Autowired
    private IRepositoryUsuario repositoryUser;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "Login an user", notes = "Login with username and password")
    public Map<String, String> login(@RequestParam("user") String username, @RequestParam("password") String password) {
        List<Usuario> lstUserExits = repositoryUser.findByUsername(username);
        HashMap<String, String> map = new HashMap<>();
        if (lstUserExits.isEmpty()) {
            map.put("message", "El usuario no existe");
            return map;
        } else {
            if (lstUserExits.get(0).getPassword().equals(password)) {
                map.put("message", getJWTToken(username));
                return map;
            }
        }
        map.put("message ", "Password incorrecta");
        return map;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(value = "Registry an user", notes = "Create a new user")
    public Usuario signup(@RequestBody Usuario user) {
        return repositoryUser.save(user);
    }

    private String getJWTToken(String username) {
        String secretKey = "persona_secret";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWT")
                .setSubject(username)
                .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 1 hora
                .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return token;
    }
}
