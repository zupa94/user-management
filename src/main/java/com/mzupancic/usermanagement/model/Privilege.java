package com.mzupancic.usermanagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(Views.Auto.class)
    private Long id;

    @Column(name = "name")
    @JsonView({Views.Manage.class, User.Views.Manage.class})
    @NotNull
    @JsonProperty("name")
    private String name;

    @Column(name = "description")
    @JsonView(Views.Manage.class)
    @JsonProperty("description")
    private String description;

    public static class Views {
        private interface Auto extends Manage {
        }

        public interface Manage {
        }
    }

}
