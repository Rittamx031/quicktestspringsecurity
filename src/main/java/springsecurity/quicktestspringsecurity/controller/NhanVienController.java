package springsecurity.quicktestspringsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springsecurity.quicktestspringsecurity.entity.NhanVien;
import springsecurity.quicktestspringsecurity.service.NhanVienService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("nhan-vien")
public class NhanVienController {
  @Autowired
  NhanVienService service;

  @PostMapping(value = "singin")
  public ResponseEntity<NhanVien> postMethodName(@RequestBody NhanVien entity) {
    return ResponseEntity.ok().body(service.SingUp(entity));
  }
 @GetMapping(value = "getall")
  public ResponseEntity<List<NhanVien>> viewAll() {
    return ResponseEntity.ok().body(service.getAll());
  }
}
