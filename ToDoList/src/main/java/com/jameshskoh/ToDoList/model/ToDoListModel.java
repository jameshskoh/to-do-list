package com.jameshskoh.ToDoList.model;

public record ToDoListModel(String id,
                            String userId,
                            String label,
                            boolean done) {

    public ToDoListModel {
    }
}
