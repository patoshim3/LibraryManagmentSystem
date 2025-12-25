package com.example.librarymanagmentsystem.repository;

import com.example.librarymanagmentsystem.Model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Permission findByName(String name);
}