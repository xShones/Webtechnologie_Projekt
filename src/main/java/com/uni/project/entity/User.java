package com.uni.project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "accountname", nullable = false)
    private String accountName;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

//    public User() {
//        // JPA benötigt einen No-Arg-Konstruktor
//    }
//
//    // Privater Konstruktor für den Builder
//    private User(Long id, String accountName, String password, String email) {
//        this.id = id;
//        this.accountName = accountName;
//        this.password = password;
//        this.email = email;
//    }

    // Getter und Setter
    public Long getId() {
        return id;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Methoden von UserDetails
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    // Statische Methode zur Erstellung des Builders
//    public static UserBuilder builder() {
//        return new UserBuilder();
//    }
//
//    // Innere statische Klasse für den Builder
//    public static class UserBuilder {
//        private Long id;
//        private String accountName;
//        private String password;
//        private String email;
//
//        public UserBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public UserBuilder accountName(String accountName) {
//            this.accountName = accountName;
//            return this;
//        }
//
//        public UserBuilder password(String password) {
//            this.password = password;
//            return this;
//        }
//
//        public UserBuilder email(String email) {
//            this.email = email;
//            return this;
//        }
//
//        public User build() {
//            return new User(id, accountName, password, email);
//        }
//    }
}











//package com.uni.project.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.Builder;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.Collections;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class User implements UserDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @Column(name = "accountname", nullable = false)
//    private String accountName;
//
//    //  @JsonIgnore // Verhindert die Serialisierung
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "email", unique = true)
//    private String email;
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    public String getAccountName() {
//        return accountName;
//    }
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(new SimpleGrantedAuthority("USER"));
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setAccountName(String accountName) {
//        this.accountName = accountName;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//
////    private User(Long id, String accountName, String email, String password) {
////        this.id = id;
////        this.accountName = accountName;
////        this.email = email;
////        this.password = password;
////    }
////
////    public static UserBuilder builder() {
////        return new UserBuilder();
////    }
////
////    public static class UserBuilder {
////        private Long id;
////        private String accountName;
////        private String email;
////        private String password;
////
////        public UserBuilder id(Long id) {
////            this.id = id;
////            return this;
////        }
////
////        public UserBuilder accountName(String accountName) {
////            this.accountName = accountName;
////            return this;
////        }
////
////        public UserBuilder email(String email) {
////            this.email = email;
////            return this;
////        }
////
////        public UserBuilder password(String password) {
////            this.password = password;
////            return this;
////        }
////
////        public User build() {
////            return new User(id, accountName, email, password);
////        }
////    }
//}
//
