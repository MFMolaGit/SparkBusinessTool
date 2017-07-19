package com.gevapps.patientdispatcher.com.bbva.repositories;

import com.gevapps.patientdispatcher.com.bbva.repositories.model.Role;
import com.gevapps.patientdispatcher.com.bbva.repositories.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by manuelmola on 19/07/17.
 */
@SpringBootTest(classes = UserRepositoryTest.class)
@DataJpaTest
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User.UserBuilder userBuilder = new User.UserBuilder();

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testFindOne() {
        User user = userBuilder.withUsername("Pepe").withPassword("1234").withRole(Role.CLIENT).build();
        userRepository.save(user);

        User userFound = userRepository.findOne(1l);
        assertNotNull("Se esperaba usuario", userFound);
        assertThat("Identificador de usuario no esperado", userFound.getId(), is(1l));
        assertThat("Nombre de usuario no esperado", userFound.getUsername(), is("Pepe"));
        assertThat("Password de usuario no esperado", userFound.getPassword(), is("1234"));
        assertThat("Rol de usuario no esperado", userFound.getRole(), is(Role.CLIENT));
    }

    @Test
    public void testFindAllByOrderByUsernameAsc() {
        User user1 = userBuilder.withUsername("Pepe").withPassword("1234").withRole(Role.CLIENT).build();
        User user2 = userBuilder.withUsername("Belen").withPassword("admin").withRole(Role.ADMIN).build();
        userRepository.save(Arrays.asList(user1,user2));

        List<User> usersFound = userRepository.findAllByOrderByUsernameAsc();
        assertThat("Se esperaban usuarios", usersFound, notNullValue());
        User userFound = usersFound.get(0);
        assertNotNull("Se esperaba usuario",userFound);
        assertThat("Identificador de usuario no esperado", userFound.getId(), is(2l));
        assertThat("Nombre de usuario no esperado", userFound.getUsername(), is("Belen"));
        assertThat("Password de usuario no esperado", userFound.getPassword(), is("admin"));
        assertThat("Rol de usuario no esperado", userFound.getRole(), is(Role.ADMIN));

    }

    @Test
    public void testFindByUsernameAndPassword() {
        User user = userBuilder.withUsername("Pepe").withPassword("1234").withRole(Role.CLIENT).build();
        userRepository.save(user);

        List<User> userFound = userRepository.findByUsernameAndPassword("Pepe", "1234");
        assertNotNull(userFound);

    }

}