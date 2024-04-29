package com.manytomany;

import com.manytomany.entity.RoleEntity;
import com.manytomany.entity.UserEntity;
import com.manytomany.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void testCreateRoles() {
        RoleEntity police = new RoleEntity("police");
        RoleEntity pilot = new RoleEntity("pilot");
        RoleEntity teacher = new RoleEntity("teacher");

        testEntityManager.persist(police);
        testEntityManager.persist(pilot);
        testEntityManager.persist(teacher);
    }


    @Test
    void testCreateNewUserWithOneRole() {
        RoleEntity admin = testEntityManager.find(RoleEntity.class, 1);
        UserEntity userEntity = new UserEntity("yusup@gmail.com", "password");
        userEntity.addRole(admin);
        userRepository.save(userEntity);
    }

    @Test
    void testCreateNewUserWithTwoRole() {
        RoleEntity admin = testEntityManager.find(RoleEntity.class, 1);
        RoleEntity customer = testEntityManager.find(RoleEntity.class, 2);
        UserEntity userEntity = new UserEntity("yusup2@gmail.com", "password2");
        userEntity.addRole(admin);
        userEntity.addRole(customer);
        userRepository.save(userEntity);
    }

    @Test
    void testAssignRoleToExistingUser() {
        UserEntity userEntity = userRepository.findById(1L).get();
        RoleEntity roleGuest = testEntityManager.find(RoleEntity.class, 3);
        userEntity.addRole(roleGuest);
    }

    @Test
    void testRemoveRoleFromExistingUser() {
        UserEntity userEntity = userRepository.findById(1L).get();
        RoleEntity roleEntity = new RoleEntity(2L);
        userEntity.removeRole(roleEntity);
    }

    @Test
    void testCreateNewUserWithNewRole() {
        RoleEntity roleSalePerson = new RoleEntity("salesperson");
        UserEntity userEntity = new UserEntity("yusup7@gmail.com","password7");
        userEntity.addRole(roleSalePerson);

        userRepository.save(userEntity);
    }

    @Test
    void testGetUser() {
        UserEntity userEntity = userRepository.findById(1L).get();
        testEntityManager.detach(userEntity);
        System.out.println(userEntity.getEmail());
        System.out.println(userEntity.getPassword());
    }

    @Test
    void testRemoveUser() {
        userRepository.deleteById(4L);
    }
}
