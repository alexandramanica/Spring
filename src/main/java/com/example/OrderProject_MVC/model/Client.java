package com.example.OrderProject_MVC.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    //Se va da exact numele campului de care se leaga din clasa corespondenta
    //CascadeType -> All (sterg un client, se sterg si comenzile lui) + orphanRemoval = true
    //CascadeType -> Persist (sterg un client, NU se sterg si comenzile lui)
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Order> orderList = new ArrayList<>();


    public Client() {
    }

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
