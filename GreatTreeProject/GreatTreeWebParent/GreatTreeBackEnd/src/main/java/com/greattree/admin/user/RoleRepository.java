package com.greattree.admin.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.greattree.common.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{

}
