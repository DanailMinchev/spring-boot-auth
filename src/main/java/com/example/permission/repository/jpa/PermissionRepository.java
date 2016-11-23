package com.example.permission.repository.jpa;

import com.example.permission.domain.jpa.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "permissions", collectionResourceRel = "permissions")
public interface PermissionRepository extends JpaRepository<Permission, String> {

    @RestResource(path = "find-by-name", rel = "findByName")
    public Permission findByName(String name);

    @RestResource(path = "find-by-name-containing", rel = "findByNameContaining")
    public List<Permission> findByNameContaining(String name);

    @RestResource(exported = false)
    public List<Permission> findByNameContaining(String name, Sort sort);

    @RestResource(exported = false)
    public Page<Permission> findByNameContaining(String name, Pageable pageable);

}
