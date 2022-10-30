package com.jameshskoh.ToDoList.controller;

import com.jameshskoh.ToDoList.model.BooleanModel;
import com.jameshskoh.ToDoList.model.ToDoModel;
import com.jameshskoh.ToDoList.repository.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoController {
    private final ToDoRepository repository;

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    // GET http://host:port/todolist
    @GetMapping
    public List<ToDoModel> listAll() {
        return repository.listAll();
    }

    // POST http://host:port/todolist
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDoModel add(@RequestBody ToDoModel toDoModel) {
        return repository.add(toDoModel);
    }

    // DELETE http://host:port/todolist/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

    // PUT http://localhost:8080/todolist/{id}
    @PutMapping("/{id}")
    public ToDoModel setDone(@PathVariable String id, @RequestBody BooleanModel bool) {
        return repository.setDone(id, bool);
    }
}
