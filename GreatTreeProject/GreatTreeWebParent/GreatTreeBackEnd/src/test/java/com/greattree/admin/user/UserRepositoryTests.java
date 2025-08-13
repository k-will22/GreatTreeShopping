package com.greattree.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.greattree.common.entity.Role;
import com.greattree.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateNewUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User userJoe = new User("joe@gmail.com", "joe2025", "Joe", "Jones");
		userJoe.addRole(roleAdmin);
		
		User savedUser = repo.save(userJoe);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateNewUserWithTwoRoles() {
		User userTom = new User("tom@gmail.com", "tom2025", "Tom", "Smith");
		Role roleEditor = new Role(3);
		Role roleAssistant = new Role(5);
		
		userTom.addRole(roleEditor);
		userTom.addRole(roleAssistant);
		
		User savedUser = repo.save(userTom);
		
		assertThat(savedUser.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> listUsers = repo.findAll();
		listUsers.forEach(user -> System.out.println(user));
	}
	
	@Test
	public void testGetUserById() {
		User userJoe = repo.findById(1).get();
		System.out.println(userJoe);
		assertThat(userJoe).isNotNull();
	}
	
	@Test
	public void testUpdateUsserDetails() {
		User userJoe = repo.findById(1).get();
		userJoe.setEnabled(true);
		userJoe.setEmail("tom2@gmail.com");
		
		repo.save(userJoe);
	}
	
	@Test
	public void testUpdateUserRoles() {
		User userTom = repo.findById(2).get();
		Role roleEditor = new Role(3);
		Role roleSalesperson = new Role(2);
		
		userTom.getRoles().remove(roleEditor);
		userTom.addRole(roleSalesperson);
		
		repo.save(userTom);
	}
	
	@Test
	public void testDeleteUser() {
		Integer userId = 2;
		repo.deleteById(userId);
	}

}
