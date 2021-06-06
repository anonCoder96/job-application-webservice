package com.example.interview.demo.model;

import javax.persistence.*;

@MappedSuperclass
public class Identifiable {
    /**
     * The unique identifier for this Entity.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
