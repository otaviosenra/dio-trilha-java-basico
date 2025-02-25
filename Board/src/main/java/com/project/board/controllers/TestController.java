package com.project.board.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "Conectado ao banco: " + connection.getCatalog();
        } catch (SQLException e) {
            return "Erro na conexão: " + e.getMessage();
        }
    }
}
