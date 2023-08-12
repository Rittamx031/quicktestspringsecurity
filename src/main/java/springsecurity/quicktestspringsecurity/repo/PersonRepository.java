package springsecurity.quicktestspringsecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import springsecurity.quicktestspringsecurity.entity.Person;
import springsecurity.quicktestspringsecurity.entity.UserInfo;

/**
 * UerRepo
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
  // @Query("SELECT new UserInfo(q.id,q.name,q.password,'nhanvien') from UserInfo q WHERE q.name = :username")
  // Optional<UserInfo> getuser(@Param("username") String username);
  // default Optional<UserInfo> findByName(String username) {
  //   Optional<Object[]> result = findUserInfoByName(username);

  //   return result.map(row -> {
  //     UserInfo userInfo = new UserInfo();
  //     userInfo.setId(Integer.parseInt(String.valueOf(row[0]))); // Cast to Integer
  //     userInfo.setName((String) row[1]);
  //     userInfo.setPassword((String) row[2]);
  //     userInfo.setRoles((String) row[3]);
  //     return userInfo;
  //   });
}