package com.joaoigm.myrecipes.users.service.repositories.user;

import com.joaoigm.myrecipes.users.service.models.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User, Integer> {
}
