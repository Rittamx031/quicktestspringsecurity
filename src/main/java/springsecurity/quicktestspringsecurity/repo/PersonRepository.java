package springsecurity.quicktestspringsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import springsecurity.quicktestspringsecurity.entity.Person;

/**
 * UerRepo
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {

}