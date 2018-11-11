package com.paperless.app.controller;
import com.paperless.app.exception.ResourceNotFoundException;
import com.paperless.app.model.UserModel;
import com.paperless.app.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    protected UserRepo userRepo;

    @GetMapping("/user")
    public Page<UserModel> getUsers(Pageable pageable) {
        return userRepo.findAll(pageable);
    }


    @PostMapping("/user")
    public UserModel createUser(@Valid @RequestBody UserModel question) {
        return userRepo.save(question);
    }

    @PutMapping("/user/{userId}")
    public UserModel updateUser(@PathVariable Long userId,
                                   @Valid @RequestBody UserModel userRequest) {
        return userRepo.findById(userId)
                .map(user -> {
                    userRequest.setName(userRequest.getName());
                    userRequest.setPassword(userRequest.getPassword());
                    return userRepo.save(userRequest);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }


    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    userRepo.delete(user);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));
    }
}