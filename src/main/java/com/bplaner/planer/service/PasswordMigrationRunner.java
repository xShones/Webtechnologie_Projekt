package com.bplaner.planer.service;/*
package com.bplaner.planer.service;

import com.bplaner.planer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordMigrationRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starte Passwort-Migration...");

        // Alle Benutzer aus der Datenbank abrufen
        userRepository.findAll().forEach(user -> {
            // Prüfen, ob das Passwort verschlüsselt ist
            if (!user.getPassword().startsWith("$2a$")) {
                System.out.println("Passwort für Benutzer " + user.getUsername() + " wird verschlüsselt.");
                // Passwort verschlüsseln
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                // Aktualisierten Benutzer speichern
                userRepository.save(user);
            }
        });

        System.out.println("Passwort-Migration abgeschlossen.");
    }
}
*/
