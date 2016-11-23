package com.example.role.repository.jpa;

import com.example.role.domain.jpa.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "roles", collectionResourceRel = "roles")
public interface RoleRepository extends JpaRepository<Role, String> {

    @RestResource(path = "find-by-name", rel = "findByName")
    public Role findByName(String name);

    @RestResource(path = "find-by-title", rel = "findByTitle")
    public Role findByTitle(String title);

    @RestResource(path = "find-by-name-containing-or-title-containing", rel = "findByNameContainingOrTitleContaining")
    public List<Role> findByNameContainingOrTitleContaining(String name, String title);

    @RestResource(exported = false)
    public List<Role> findByNameContainingOrTitleContaining(String name, String title, Sort sort);

    @RestResource(exported = false)
    public Page<Role> findByNameContainingOrTitleContaining(String name, String title, Pageable pageable);

}
