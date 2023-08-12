package springsecurity.quicktestspringsecurity.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springsecurity.quicktestspringsecurity.entity.NhanVien;

public interface NhanVienRepo extends JpaRepository<NhanVien, UUID> {
  @Query("SELECT kh from NhanVien kh WHERE kh.email = :username")
  Optional<NhanVien> getuser(@Param("username") String username);

  Optional<NhanVien> findByEmail(String email);
}
