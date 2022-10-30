package com.jameshskoh.ToDoList.repository;

import com.jameshskoh.ToDoList.model.BooleanModel;
import com.jameshskoh.ToDoList.model.ToDoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToDoRepository {

    public ToDoRepository() {

    }

    public List<ToDoModel> listAll() {
        return null;
    }

    public ToDoModel add(ToDoModel toDoModel) {
        return null;
    }

    public void delete(String id) {

    }

    public ToDoModel setDone(String id, BooleanModel bool) {
        return null;
    }

}
