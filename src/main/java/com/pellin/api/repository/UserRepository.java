package com.pellin.api.repository;

import com.pellin.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author eloipelmon
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
