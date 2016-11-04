package ru.stqa.pft.mantis.model;

/**
 * Created by aleksandr.petrov on 04.11.16.
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class User {
    @Id
    @Column(name = "id")
    public int id;
    @Column(name = "username")
    public String username;
    @Column(name = "email")
    public String email;

    public User(int id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public User() {
    }



    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public User withEmail(String email) {
        this.email = email;
        return this;
    }

    public User withUsername(String username) {
        this.username = username;
        return this;
    }
}