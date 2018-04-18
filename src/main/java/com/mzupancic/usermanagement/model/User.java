package com.mzupancic.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString(exclude = {"id", "password", "username"})
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonView(Views.Auto.class)
    private Long id;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    @JsonView(Views.Manage.class)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    @JsonView(Views.Manage.class)
    @NotNull
    private String lastName;

    @Column(name = "username", unique = true)
    @JsonView(Views.Manage.class)
    @NotNull
    private String username;

    @Column(name = "email", unique = true)
    @JsonView(Views.Manage.class)
    @NotNull
    private String email;

    @Column(name = "privileges")
    @JsonView(Views.Manage.class)
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_privileges",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    @JsonView(Views.Add.class)
    @NotNull
    private String password;

    public static class Views {
        private interface Auto extends Manage {
        }

        public interface Add extends Manage {
        }

        public interface Manage {
        }
    }

}
