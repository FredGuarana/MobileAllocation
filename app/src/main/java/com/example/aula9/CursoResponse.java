package com.example.aula9;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties({"allocations"})
public class CursoResponse {

    private int id;
    private String name;



    public CursoResponse() {
    }

    public CursoResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    @Override
    public String toString() {
        return name;
    }
}

