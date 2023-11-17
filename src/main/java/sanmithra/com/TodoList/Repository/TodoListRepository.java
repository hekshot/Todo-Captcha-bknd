package sanmithra.com.TodoList.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sanmithra.com.TodoList.Entity.TodoList;

public interface TodoListRepository extends JpaRepository<TodoList,Integer> {
}
