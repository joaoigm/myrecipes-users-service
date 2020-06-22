package com.joaoigm.myrecipes.users.service.repositories.user;

import com.joaoigm.myrecipes.users.service.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
}
