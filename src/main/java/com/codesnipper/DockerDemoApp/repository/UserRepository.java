package com.codesnipper.DockerDemoApp.repository;

import com.codesnipper.DockerDemoApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
