package com.mzupancic.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.mzupancic.usermanagement.views.Views.Manage;
import com.mzupancic.usermanagement.views.Views.Auto;
import com.mzupancic.usermanagement.views.Views.Add;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @JsonView(Auto.class)
    private Long id;

    @Column(name = "first_name")
    @JsonProperty("first_name")
    @JsonView(Manage.class)
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    @JsonView(Manage.class)
    @NotNull
    private String lastName;

    @Column(name = "username", unique = true)
    @JsonView(Manage.class)
    @NotNull
    private String username;

    @Column(name = "email", unique = true)
    @JsonView(Manage.class)
    @NotNull
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    @JsonView(Add.class)
    @NotNull
    private String password;

}
