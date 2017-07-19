package com.gevapps.patientdispatcher.com.bbva.repositories;

import com.gevapps.patientdispatcher.com.bbva.repositories.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by manuelmola on 19/07/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findOne(final Long id);

    List<User> findAllByOrderByUsernameAsc();

    List<User> findByUsernameAndPassword(final String username, final String password);

}
