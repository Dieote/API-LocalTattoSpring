package com.lt.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "comments")
    private String comment;
    @Column(name = "username")
    private String username;

    public Comment() {}

    public Comment(String comment, String username) {
    this.comment = comment;
    this.username = username;
}

}
