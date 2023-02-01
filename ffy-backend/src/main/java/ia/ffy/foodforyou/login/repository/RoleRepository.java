package ia.ffy.foodforyou.login.repository;

import ia.ffy.foodforyou.login.model.Role;
import ia.ffy.foodforyou.login.model.URole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(URole name);
}
