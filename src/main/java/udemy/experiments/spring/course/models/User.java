package udemy.experiments.spring.course.models;

import com.fasterxml.jackson.annotation.JsonProperty;

//public record User(
//        Integer id,
//        String firstName,
//        String lastName,
//        String email,
//        String role,
//        String createdOnatedOn) {}

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String createdOn;

    public User(
            @JsonProperty("id") Long id,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("role") String role,
            @JsonProperty("createdOn") String createdOn
) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.createdOn = createdOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreatedOn(String now) {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
