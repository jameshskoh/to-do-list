package com.jameshskoh.ToDoList.model;

public record ToDoModel(String id,
                        String userId,
                        String label,
                        boolean done) {

    public ToDoModel {
    }
}
