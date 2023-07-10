package com.wallet.security.repository;


import com.wallet.security.model.AccountUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<AccountUser,String> {

    Optional<AccountUser> findByEmail(String email);
}
