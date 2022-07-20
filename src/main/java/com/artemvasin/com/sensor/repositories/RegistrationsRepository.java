package com.artemvasin.com.sensor.repositories;

import com.artemvasin.com.sensor.models.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registration, Integer> {
}
