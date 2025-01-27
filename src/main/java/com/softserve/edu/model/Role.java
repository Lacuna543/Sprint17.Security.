package com.softserve.edu.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

//    //?
//    @ManyToMany(mappedBy = "roles")
//   private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
