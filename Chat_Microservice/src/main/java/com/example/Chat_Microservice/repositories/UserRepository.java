package com.example.Chat_Microservice.repositories;

import com.example.Chat_Microservice.entities.UserDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDTO, Long> {
}
