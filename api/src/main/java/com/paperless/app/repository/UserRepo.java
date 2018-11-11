package com.paperless.app.repository;

import com.paperless.app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
    @Repository
    public interface UserRepo extends JpaRepository<UserModel, Long> {

}
