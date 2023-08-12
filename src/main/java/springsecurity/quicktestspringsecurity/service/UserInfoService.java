// package springsecurity.quicktestspringsecurity.service;

// import java.util.Optional;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Component;

// import lombok.RequiredArgsConstructor;
// import springsecurity.quicktestspringsecurity.config.UserInfoUserDetails;
// import springsecurity.quicktestspringsecurity.entity.UserInfo;
// import springsecurity.quicktestspringsecurity.repo.UserInfoRepository;

// @Component
// @RequiredArgsConstructor
// public class UserInfoService implements UserDetailsService {

//   private final UserInfoRepository repository;

//   @Override
//   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//     Optional<UserInfo> userInfo = repository.findByName(username);
//     return userInfo.map(UserInfoUserDetails::new)
//         .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
//   }
// }
