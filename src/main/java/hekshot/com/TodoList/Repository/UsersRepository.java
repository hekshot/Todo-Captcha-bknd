package hekshot.com.TodoList.Repository;

import hekshot.com.TodoList.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByUserName(String userName);

}
