package springsecurity.quicktestspringsecurity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import springsecurity.quicktestspringsecurity.config.UserInfoUserDetails;
import springsecurity.quicktestspringsecurity.entity.KhachHang;
import springsecurity.quicktestspringsecurity.entity.NhanVien;
import springsecurity.quicktestspringsecurity.entity.UserInfo;
import springsecurity.quicktestspringsecurity.repo.KhachHangRepo;

@Component
@RequiredArgsConstructor
public class KhachHangInfoService implements UserDetailsService {
  private final KhachHangRepo repository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<KhachHang> khachhang = repository.getuser(username);
    if (!khachhang.isPresent()) {
      throw new UsernameNotFoundException("can not find nhan vien with username khachhang");
    }
    String Roles = "USER";
    UserInfo userinfo = new UserInfo(khachhang.get().getEmail(), khachhang.get().getPass(), Roles);
    return new UserInfoUserDetails(userinfo);
  }

}
