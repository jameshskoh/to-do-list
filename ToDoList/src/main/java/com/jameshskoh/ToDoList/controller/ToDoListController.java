package com.jameshskoh.ToDoList.controller;

import com.jameshskoh.ToDoList.model.BooleanModel;
import com.jameshskoh.ToDoList.model.ToDoListModel;
import com.jameshskoh.ToDoList.repository.ToDoListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {
    private final ToDoListRepository repository;

    public ToDoListController(ToDoListRepository repository) {
        this.repository = repository;
    }

    // GET http://host:port/todolist
    @GetMapping
    public List<ToDoListModel> listAll() {
        return repository.listAll();
    }

    // POST http://host:port/todolist
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDoListModel add(@RequestBody ToDoListModel toDoListModel) {
        return repository.add(toDoListModel);
    }

    // DELETE http://host:port/todolist/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

    // PUT http://localhost:8080/todolist/{id}
    @PutMapping("/{id}")
    public ToDoListModel setDone(@PathVariable String id, @RequestBody BooleanModel bool) {
        return repository.setDone(id, bool);
    }
}
