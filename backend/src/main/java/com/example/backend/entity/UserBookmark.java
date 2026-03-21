package com.example.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_bookmarks")
public class UserBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "scheme_id", nullable = false)
    private Scheme scheme;

    public UserBookmark(Long id, User user, Scheme scheme) {
        this.id = id;
        this.user = user;
        this.scheme = scheme;
    }

    public UserBookmark() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Scheme getScheme() { return scheme; }
    public void setScheme(Scheme scheme) { this.scheme = scheme; }
}
