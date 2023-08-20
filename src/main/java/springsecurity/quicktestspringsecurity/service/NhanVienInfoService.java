package springsecurity.quicktestspringsecurity.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springsecurity.quicktestspringsecurity.config.UserInfoUserDetails;
import springsecurity.quicktestspringsecurity.entity.NhanVien;
import springsecurity.quicktestspringsecurity.entity.UserInfo;
import springsecurity.quicktestspringsecurity.repo.NhanVienRepo;

@Component
@RequiredArgsConstructor
public class NhanVienInfoService implements UserDetailsService {
  private final NhanVienRepo repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<NhanVien> nhanvien = repository.getuser(username);
    if (!nhanvien.isPresent()) {
      throw new UsernameNotFoundException("can not find nhan vien with username nhanvien");
    }
    String Roles = nhanvien.get().getIdCV().getTenCV();
    UserInfo userinfo = new UserInfo(nhanvien.get().getEmail(), nhanvien.get().getPass(), Roles);
    return new UserInfoUserDetails(userinfo);
  }
  // @Autowired

}
