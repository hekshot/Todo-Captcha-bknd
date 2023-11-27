package hekshot.com.TodoList.Repository;

import hekshot.com.TodoList.Entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<TodoList,Integer> {
}
