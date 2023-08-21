package springsecurity.quicktestspringsecurity.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springsecurity.quicktestspringsecurity.config.JwtTokenProvider;
import springsecurity.quicktestspringsecurity.config.UserInfoUserDetails;
import springsecurity.quicktestspringsecurity.entity.KhachHang;
import springsecurity.quicktestspringsecurity.response.jwtResponse;
import springsecurity.quicktestspringsecurity.service.KhachHangService;

/**
 * KhachHangController
 */
@RestController
@RequestMapping("khach-hang")
public class KhachHangController {
  @Autowired
  KhachHangService service;

  @Autowired
  JwtTokenProvider jwtTokenProvider;

  @Autowired
  AuthenticationManager authenticationManager;

  @PostMapping(value = "singup")
  public ResponseEntity<KhachHang> singUp(@RequestBody KhachHang entity) {
    return ResponseEntity.ok().body(service.SingUp(entity));
  }

  @PostMapping(value = "singin")
  public ResponseEntity<?> singIn(@RequestBody KhachHang entity) {
    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPass()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // System.out.println(authentication);
    String jwt = jwtTokenProvider.generateToken(authentication);
    System.out.println(jwt);
    UserInfoUserDetails userDetails = (UserInfoUserDetails) authentication.getPrincipal();
    return ResponseEntity.ok().body(
        new jwtResponse(jwt,
            userDetails.getUsername()));
  }

  @GetMapping(value = "getall")
  public ResponseEntity<List<KhachHang>> viewAll() {
    return ResponseEntity.ok().body(service.getAll());
  }
}
