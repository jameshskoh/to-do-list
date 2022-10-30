package com.jameshskoh.ToDoList.entity;

import com.jameshskoh.ToDoList.model.ToDoLabelModel;

import javax.persistence.*;

@Entity
@Table(name="todo")
public class ToDo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private String id;

    @Column(name="user_id")
    private String userId;

    @Column(name="label")
    private String label;

    @Column(name="done", nullable=false, columnDefinition="BOOLEAN")
    private boolean done;

    public ToDo() {

    }

    public ToDo(String userId, ToDoLabelModel model) {
        this.userId = userId;
        this.label = model.label();
        this.done = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ToDo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", label='" + label + '\'' +
                ", done=" + done +
                '}';
    }
}
