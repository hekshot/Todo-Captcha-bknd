package sanmithra.com.TodoList.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanmithra.com.TodoList.Entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByUserName(String userName);
//    Users findByUserPassword(String userPassword);
//
//    Users findByUsername(String userName);
}
