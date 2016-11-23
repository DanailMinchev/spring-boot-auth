package com.example.user.repository.jpa;

import com.example.user.domain.jpa.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRepository extends JpaRepository<User, String> {

    @RestResource(path = "find-by-email", rel = "findByEmail")
    public User findByEmail(String email);

    @RestResource(path = "find-by-phone-number", rel = "findByPhoneNumber")
    public User findByPhoneNumber(String phoneNumber);

    @RestResource(path = "find-by-username", rel = "findByUsername")
    public User findByUsername(String username);

    @RestResource(path = "find-by-username-containing-or-name-containing", rel = "findByUsernameContainingOrNameContaining")
    public List<User> findByUsernameContainingOrNameContaining(String username, String name);

    @RestResource(exported = false)
    public List<User> findByUsernameContainingOrNameContaining(String username, String name, Sort sort);

    @RestResource(exported = false)
    public Page<User> findByUsernameContainingOrNameContaining(String username, String name, Pageable pageable);

}
