package org.example.Resource;

import org.example.Entity.JwtRequest;
import org.example.Entity.JwtResponse;
import org.example.Service.ServiceImpl.UserService;
import org.example.Utils.JWUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
    @Autowired
    private JWUtility jwUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody final JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (final BadCredentialsException e){
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwUtility.generateToken(userDetails);
        return new JwtResponse(token);
    }

    @GetMapping("/")
    public String home() {
        return "User found. Welcome to Raven Cinema!";
    }

    @GetMapping("/get-test")
    public String getCinema() {
        return "/get";
    }

    @GetMapping("api/v1/admin/get-test")
    public String getCinemaBy() {
        return "api/v1/admin/get";
    }
}
