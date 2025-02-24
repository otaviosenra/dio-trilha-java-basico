package dio.JWTProjectExample.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dio.JWTProjectExample.models.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    public Users findByUsername(@Param("username") String username);

    boolean existsByUsername(String username);
}
