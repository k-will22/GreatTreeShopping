package com.greattree.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.greattree.common.entity.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
