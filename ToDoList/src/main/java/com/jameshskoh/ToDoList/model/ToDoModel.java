package com.jameshskoh.ToDoList.model;

import com.jameshskoh.ToDoList.entity.ToDo;

public record ToDoModel(String id,
                        String userId,
                        String label,
                        boolean done) {

    public ToDoModel(String id, String userId, String label, boolean done) {
        this.id = id;
        this.userId = userId;
        this.label = label;
        this.done = done;
    }

    public ToDoModel(ToDo toDo) {
        this(toDo.getId(), toDo.getUserId(), toDo.getLabel(), toDo.isDone());
    }
}
