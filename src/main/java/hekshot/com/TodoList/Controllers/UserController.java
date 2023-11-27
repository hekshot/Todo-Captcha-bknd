package hekshot.com.TodoList.Controllers;

import cn.apiclub.captcha.Captcha;
import hekshot.com.TodoList.CaptchaUtil;
import hekshot.com.TodoList.Entity.CaptchaRes;
import hekshot.com.TodoList.Entity.Login;
import hekshot.com.TodoList.Entity.TodoList;
import hekshot.com.TodoList.Entity.Users;
import hekshot.com.TodoList.Repository.TodoListRepository;
import hekshot.com.TodoList.Services.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hekshot.com.TodoList.Repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TodoListRepository todoListRepository;

    @Autowired
    private CaptchaUtil captchaUtil;

    private void getCaptcha(Users user) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        user.setHiddenCaptcha(captcha.getAnswer());
        user.setCaptcha(""); // value entered by the User
        user.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));
    }

    private HashMap<Integer, String> hm = new HashMap<>();

    //private Captcha captcha;

    private void scheduleCaptchaCleanup(int randomId) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);
        executorService.schedule(() -> {
            hm.remove(randomId);
        }, 1, TimeUnit.MINUTES); // 5 minutes (adjust the delay as needed)
    }


    @GetMapping("/captcha/{randomId}")
    public CaptchaRes generateCaptcha(@PathVariable("randomId") int randomId) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        hm.put(randomId, captcha.getAnswer());

        // Schedule a task to remove the captcha after a limited time
        scheduleCaptchaCleanup(randomId);

//        for (Map.Entry<Integer, String> entry : hm.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }

        return new CaptchaRes(CaptchaUtil.encodeCaptcha(captcha));
    }

    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable Integer userId){
        return usersRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }


    @PostMapping("/register/{randomId}")
    public ResponseEntity<?> addUser(@PathVariable("randomId") int randomId,@RequestBody Users users ) throws UsernameAlreadyExistsException {

        String code = hm.get(randomId);

        //users.setHiddenCaptcha(captcha.getAnswer());
        Users existingUser = usersRepository.findByUserName(users.getUserName());
        if (existingUser != null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
        //else if(users.getCaptcha().equals(users.getHiddenCaptcha())){
        else if(users.getCaptcha().equals(code)){
            users.setUserName(users.getUserName());
            users.setUserPassword(passwordEncoder.encode(users.getUserPassword()));
            usersRepository.save(users);
            return ResponseEntity.ok("success");
        }else{
            return ResponseEntity.ok("failed");
        }
    }


    @PostMapping("/login/{randomId}")
    public ResponseEntity<?> loginUser(@PathVariable("randomId") int randomId,@RequestBody Login login) {
        Users user = usersRepository.findByUserName(login.getUserName());
        String code = hm.get(randomId);
        if (user != null && passwordEncoder.matches(login.getUserPassword(), user.getUserPassword()) ) {
            if (login.getCaptcha().equals(code)) {
                return ResponseEntity.ok(user);
            }
            throw new  ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong");
        }
        else {
            // Invalid username or password
            throw new  ResponseStatusException(HttpStatus.NOT_FOUND, "Failed");
        }
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Integer userId, @RequestBody TodoList todoList){
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        TodoList todo = new TodoList();
        todo.setTasks(todoList.getTasks());
        user.getTodoList().add(todo);
        usersRepository.save(user);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted( @PathVariable Integer todoId){
        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todoList.setCompleted(!todoList.getCompleted());
        todoListRepository.save(todoList);
    }


    @DeleteMapping("{userId}/todos/{todoId}")
    public Users deleteTodo(@PathVariable Integer userId, @PathVariable Integer todoId) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        TodoList todo = todoListRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodoList().remove(todo);
        todoListRepository.delete(todo);
        return usersRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }
    @PutMapping("/{userId}/todos/{todoId}")
    public TodoList editTodo(
            @PathVariable Integer userId,
            @PathVariable Integer todoId,
            @RequestBody TodoList updatedTodo
    ) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        TodoList todo = todoListRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());

        // Update the task's details with the provided data
        todo.setTasks(updatedTodo.getTasks());
        todo.setCompleted(updatedTodo.getCompleted());

        // Save the updated task
        todoListRepository.save(todo);

        return todo;
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        Users users = usersRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        usersRepository.delete(users);
    }
    @PutMapping("/users/{userId}/todos/{taskId}")
    public ResponseEntity<TodoList> updateTaskStatus(
            @PathVariable("userId") int userId,
            @PathVariable("taskId") int taskId,
            @RequestBody TodoList updatedTask) {
        // Check if the task belongs to the specified user (you should have a way to verify this)

        Optional<TodoList> taskOptional = todoListRepository.findById(taskId);

        if (taskOptional.isPresent()) {
            TodoList existingTask = taskOptional.get();
            existingTask.setCompleted(updatedTask.isCompleted());
            todoListRepository.save(existingTask);

            return ResponseEntity.ok(existingTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

