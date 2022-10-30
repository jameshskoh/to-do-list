package com.jameshskoh.ToDoList.repository;

import com.jameshskoh.ToDoList.model.BooleanModel;
import com.jameshskoh.ToDoList.model.ToDoListModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ToDoListRepository {

    public ToDoListRepository() {

    }

    public List<ToDoListModel> listAll() {
        return null;
    }

    public ToDoListModel add(ToDoListModel toDoListModel) {
        return null;
    }

    public void delete(String id) {

    }

    public ToDoListModel setDone(String id, BooleanModel bool) {
        return null;
    }

}
