package udemy.experiments.spring.course.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.experiments.spring.course.models.User;
import udemy.experiments.spring.course.services.IUserService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/health")
    public String health() {
        System.out.println("Health check working");
        return "OK";
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Iterable<User>> getUsers() {
        var users = this.userService.getUsers();
        return ResponseEntity.of(Optional.of(users));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {

        if (!this.userService.userExists(id)) {
            return ResponseEntity.notFound().build();
        }

        var user = this.userService.getUser(id);
        return ResponseEntity.of(Optional.of(user));
    }

    @PostMapping("/createUser")
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {

//        if (this.userService.userExists(user.getId())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }

        this.userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User user) {

        if (!this.userService.userExists(user.getId())) {
            return ResponseEntity.notFound().build();
        }

        var updatedUser = this.userService.updateUser(user);
        return ResponseEntity.accepted().body(updatedUser);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {

        if (!this.userService.userExists(id)) {
            return ResponseEntity.notFound().build();
        }

        this.userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
