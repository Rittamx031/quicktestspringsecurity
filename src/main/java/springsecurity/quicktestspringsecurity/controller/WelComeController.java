package springsecurity.quicktestspringsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelComeController {

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(Authentication authentication) {

        String userName = authentication.getName();

        return ResponseEntity.ok("Spring Security In-memory Authentication Example - Welcome " + userName);
    }

    @GetMapping("/print")
    public ResponseEntity<String> prng() {
        return ResponseEntity.ok("Spring Security In-memory Authentication Example - Welcome ");
    }
}