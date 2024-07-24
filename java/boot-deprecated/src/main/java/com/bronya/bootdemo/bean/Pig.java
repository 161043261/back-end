package com.bronya.bootdemo.bean;

import org.springframework.stereotype.Component;

@Component
// @ConfigurationProperties(prefix = "pig")
public class Pig {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pig{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
