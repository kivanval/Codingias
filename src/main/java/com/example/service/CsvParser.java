package com.example.service;

import com.example.security.model.Role;
import com.example.security.model.User;
import lombok.SneakyThrows;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvParser implements TableParser {
    @SneakyThrows
    @Override
    public List<User> parseToUsersList(File file) {
        List<User> users = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] userInfo = line.split(",");
                users.add(new User(Role.STUDENT, userInfo[1], userInfo[0], userInfo[2]));
            }
        }
        return users;
    }
}
