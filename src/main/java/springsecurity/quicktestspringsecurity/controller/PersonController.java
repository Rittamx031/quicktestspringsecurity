package springsecurity.quicktestspringsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springsecurity.quicktestspringsecurity.entity.Person;
import springsecurity.quicktestspringsecurity.entity.UserInfo;
import springsecurity.quicktestspringsecurity.repo.PersonRepository;
import springsecurity.quicktestspringsecurity.repo.UserInfoRepository;

@RestController
@RequestMapping("api/v2/person")
public class PersonController {
    @Autowired
    PersonRepository repository;
    @Autowired
    UserInfoRepository usrepository;

    @GetMapping("getalluser")
    public ResponseEntity<List<UserInfo>> getAllUl() {
        return ResponseEntity.ok().body(usrepository.findAll());
    }

    @GetMapping
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok().body(repository.findAll());
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> findPerson(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(repository.findById(id).orElse(null));
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        return ResponseEntity.ok().body(repository.save(person));
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deletePerson(@PathVariable("id") int id) {
        this.repository.deleteById(id);
        return HttpStatus.OK;
    }
}
