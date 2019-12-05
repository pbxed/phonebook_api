package com.pbx.phonebook.repository;

import com.pbx.phonebook.model.Role;
import com.pbx.phonebook.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
