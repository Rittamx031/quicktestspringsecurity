package springsecurity.quicktestspringsecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springsecurity.quicktestspringsecurity.entity.NhanVien;
import springsecurity.quicktestspringsecurity.repo.NhanVienRepo;

@Service
public class NhanVienService {
  @Autowired
  NhanVienRepo repository;
  @Autowired
  PasswordEncoder passwordEncoder;

  public NhanVien SingUp(NhanVien nhanvien) {
    nhanvien.setPass(passwordEncoder.encode(nhanvien.getPass()));
    return repository.save(nhanvien);
  }
  public List<NhanVien> getAll() {
    return repository.findAll();
  }
}
