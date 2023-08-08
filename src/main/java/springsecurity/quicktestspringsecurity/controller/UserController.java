package springsecurity.quicktestspringsecurity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import springsecurity.quicktestspringsecurity.entity.UserInfo;
import springsecurity.quicktestspringsecurity.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  @PostMapping("/new")
  public String addUser(@RequestBody UserInfo userInfo) {
    return userService.addUser(userInfo);
  }
}
