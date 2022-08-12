package com.example.service;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    public String hashEmail(String email) {
        return Hashing.sha256()
                .hashString(email, StandardCharsets.UTF_8)
                .toString();
    }

}
