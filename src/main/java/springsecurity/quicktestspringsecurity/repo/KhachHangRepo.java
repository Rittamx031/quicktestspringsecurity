package springsecurity.quicktestspringsecurity.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springsecurity.quicktestspringsecurity.entity.KhachHang;

import java.util.Optional;

public interface KhachHangRepo extends JpaRepository<KhachHang, UUID> {
  @Query("SELECT kh from KhachHang kh WHERE kh.email = :username")
  Optional<KhachHang> getuser(@Param("username") String username);
}
