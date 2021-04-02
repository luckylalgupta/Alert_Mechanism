package com.alert.mechanism.repository;

import com.alert.mechanism.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
}
