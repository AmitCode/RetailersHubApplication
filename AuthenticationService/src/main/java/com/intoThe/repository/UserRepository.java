package com.intoThe.repository;

import com.intoThe.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    public Optional<Users> findByUserEmail(String userEmailId);
//    public Users findByUserEmailIdOrUserContactNumber(String userName,
//                                                      String userContactNumber);
    public Optional<Users> findByUserName(String userName);
    public Optional<Users> findByUserId(Long userId);
    public void deleteByUserName(String userName);
}
