package com.fitcoach.fitcoach.dao.entity;

import com.fitcoach.fitcoach.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;


@AllArgsConstructor
@Data
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatar;

    private Date createdAt;

    private Date updateAt;

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role=Role.USER;

    public Person() {
        this.setAvatar("http://localhost:9090/content/logo.png");
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

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
}
