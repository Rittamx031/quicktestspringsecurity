package springsecurity.quicktestspringsecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import springsecurity.quicktestspringsecurity.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
  Optional<UserInfo> findByName(String username);
}
