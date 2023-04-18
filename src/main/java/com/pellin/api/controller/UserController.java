package com.pellin.api.controller;

import com.pellin.api.model.User;
import com.pellin.api.repository.UserRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    @PostMapping("/")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("User not found with id " +id) );
        
    }
    
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        User existingUser = userRepository.findById(id).orElseThrow( () -> new EntityNotFoundException("User not found with id " +id) );
        existingUser.setNombre(user.getNombre());
        existingUser.setApellido1(user.getApellido1());
        existingUser.setApellido2(user.getApellido2());
        existingUser.setEmail(user.getEmail());
        existingUser.setTelefono(user.getTelefono());
        existingUser.setTelefono2(user.getTelefono2());
        existingUser.setDni(user.getDni());
        existingUser.setFNacimiento(user.getFNacimiento());
        
        return userRepository.save(existingUser);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}