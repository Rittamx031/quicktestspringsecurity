// package springsecurity.quicktestspringsecurity.service;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import springsecurity.quicktestspringsecurity.entity.UserInfo;
// import springsecurity.quicktestspringsecurity.repo.UserInfoRepository;
// @Service
// public record UserService(UserInfoRepository repository,
//     PasswordEncoder passwordEncoder) {
//   public String addUser(UserInfo userInfo) {
//     userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//         // userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

//     repository.save(userInfo);
//     return "Thêm user thành công!";
//   }
// }
 