package com.example.data;

import com.example.model.Coding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCodingRepository extends JpaRepository<Coding, Long> {
}
