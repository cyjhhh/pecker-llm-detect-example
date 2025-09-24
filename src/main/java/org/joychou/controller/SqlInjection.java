package org.joychou.controller;

import lombok.extern.slf4j.Slf4j;
import org.joychou.util.WebUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
@RestController
public class SqlInjection {

    @GetMapping("/query")
    public String query(String username, String password) throws IOException {
        String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            return resultSet.getNString(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "test";
    }
}
