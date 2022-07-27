package com.example.data;

import com.example.model.Element;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaElementRepository extends JpaRepository<Element, Long> {
}
