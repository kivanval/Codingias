package com.example;

import com.example.security.Permission;
import com.example.security.Role;
import com.example.security.repo.PermissionRepository;
import com.example.security.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class CodingiasApplication {
	public static void main(String[] args) {
		SpringApplication.run(CodingiasApplication.class, args);
	}

}
