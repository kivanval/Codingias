package com.example.service;

import com.example.model.User;

import java.io.File;
import java.util.List;

public interface TableParser {
    List<User> parseToUsersList(File file);
}
