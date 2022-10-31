package com.jameshskoh.ToDoList.controller;

import com.jameshskoh.ToDoList.model.DoneModel;
import com.jameshskoh.ToDoList.model.ToDoLabelModel;
import com.jameshskoh.ToDoList.model.ToDoModel;
import com.jameshskoh.ToDoList.repository.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {
    private final ToDoRepository repository;

    public ToDoController(ToDoRepository repository) {
        this.repository = repository;
    }

    // GET http://host:port/todo
    @GetMapping
    public List<ToDoModel> listAll(OAuth2AuthenticationToken token) {
        String userId = token.getPrincipal().getName();
        return repository.listAll(userId);
    }

    // POST http://host:port/todo
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ToDoModel add(OAuth2AuthenticationToken token, @RequestBody ToDoLabelModel toDoLabelModel) {
        String userId = token.getPrincipal().getName();
        return repository.add(userId, toDoLabelModel);
    }

    // DELETE http://host:port/todo/{id}
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(OAuth2AuthenticationToken token, @PathVariable String id) {
        String userId = token.getPrincipal().getName();
        repository.delete(userId, id);
    }

    // PUT http://localhost:8080/todo/{id}
    @PutMapping("/{id}")
    public ToDoModel setDone(OAuth2AuthenticationToken token, @PathVariable String id, @RequestBody DoneModel bool) {
        String userId = token.getPrincipal().getName();
        return repository.setDone(userId, id, bool);
    }
}
