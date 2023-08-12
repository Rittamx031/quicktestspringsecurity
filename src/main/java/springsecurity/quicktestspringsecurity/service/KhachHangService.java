package springsecurity.quicktestspringsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springsecurity.quicktestspringsecurity.entity.KhachHang;
import springsecurity.quicktestspringsecurity.repo.KhachHangRepo;

@Service
public class KhachHangService {

  @Autowired
  KhachHangRepo repository;
  @Autowired
  PasswordEncoder passwordEncoder;

  public KhachHang SingUp(KhachHang khachHang) {
    khachHang.setPass(passwordEncoder.encode(khachHang.getPass()));
    return repository.save(khachHang);
  }

  public List<KhachHang> getAll() {
    return repository.findAll();
  }
}
