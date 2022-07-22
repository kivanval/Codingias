package com.example.service;

import com.example.security.model.Role;
import com.example.security.model.User;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser implements TableParser {
    @SneakyThrows
    @Override
    public List<User> parseToUsersList(File file) {
        List<User> users = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            int i = 0;
            String lastName = null, firstName = null, email = null;
            for (Cell cell : row) {
                if (i == 0) lastName = cell.toString();
                if (i == 1) firstName = cell.toString();
                if (i == 2) email = cell.toString();
                i++;
            }
            users.add(new User(Role.STUDENT, firstName, lastName, email));
        }
        return users;
    }
}
