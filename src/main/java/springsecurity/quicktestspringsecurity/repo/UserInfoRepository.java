// package springsecurity.quicktestspringsecurity.repo;

// import java.util.Optional;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import springsecurity.quicktestspringsecurity.entity.UserInfo;

// public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
//   // @Query(value = "  SELECT u.id as 'id',u.username as 'name',u.password,u.roles from UserInfo u WHERE  username=:username", nativeQuery = true)
//   Optional<UserInfo> findByName( String username);
  
// }
