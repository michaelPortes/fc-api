package com.example.fc_api.domains.users.repository;

import com.example.fc_api.domains.users.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

    @Query(
        value = "select * from users users where id = :#{#userId.getId()}",
            nativeQuery = true
    )

    public Collection<UsersEntity> getUserById(Object userId);


    @Query(
            value = "select * from users",
            nativeQuery = true
    )
    public Collection<UsersEntity> getUsersList();

}
