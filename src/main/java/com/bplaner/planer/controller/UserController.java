package com.bplaner.planer.controller;

import com.bplaner.planer.dto.UserAccountBalanceDTO;
import com.bplaner.planer.service.TransactionService;
import com.bplaner.planer.service.TransactionServiceImp;
import com.bplaner.planer.service.UserServiceImp;
import com.bplaner.planer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Basis-URL für alle Endpunkte in diesem Controller
public class UserController {

    private final UserServiceImp userServiceImp;
    private final TransactionServiceImp transactionServiceImp;
    private TransactionServiceImp transactionService;

    @Autowired
    public UserController(UserServiceImp userServiceImp, TransactionServiceImp transactionServiceImp) {
        this.userServiceImp = userServiceImp;
        this.transactionServiceImp = transactionServiceImp;
    }

    // GET: Alle Benutzer abrufen
    @GetMapping("/api/benutzer")
    public List<User> getAllUsers() {
        return userServiceImp.getAllUsers();
    }

    // GET: Einen Benutzer nach ID abrufen
    @GetMapping("/api/benutzer/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceImp.getUserById(id));
    }

    // POST: Benutzer registrieren
    @PostMapping("/api/register")
    public ResponseEntity<User> registerUser(@RequestParam String username,
                                             @RequestParam String email,
                                             @RequestParam String password) {
        User registeredUser = userServiceImp.registerUser(username, email, password);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    // GET: Benutzer nach Benutzername suchen
    @GetMapping("/api/benutzer/username/{username}")
    public ResponseEntity<User> findUserByUsername(@PathVariable String username) {
        return userServiceImp.findUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET: Benutzer nach E-Mail suchen
    @GetMapping("/api/benutzer/email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable String email) {
        return userServiceImp.findUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Einen neuen Benutzer hinzufügen
    @PostMapping("/api/benutzer")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userServiceImp.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // PUT: Einen vorhandenen Benutzer aktualisieren
    @PutMapping("/api/benutzer/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userServiceImp.updateUser(id, user));
    }

    // DELETE: Einen Benutzer löschen
    @DeleteMapping("/api/benutzer/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImp.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
