package com.jameshskoh.ToDoList.repository;

import com.jameshskoh.ToDoList.model.BooleanModel;
import com.jameshskoh.ToDoList.model.ToDoLabelModel;
import com.jameshskoh.ToDoList.model.ToDoModel;
import com.jameshskoh.ToDoList.entity.ToDo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoRepository {
    private final SessionFactory factory;

    public ToDoRepository(SessionFactory factory) {
        this.factory = factory;
    }

    public List<ToDoModel> listAll(String userId) {
        List<ToDo> toDos;

        Session session = factory.getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ToDo> criteria = builder.createQuery(ToDo.class);
        Root<ToDo> root = criteria.from(ToDo.class);
        criteria.select(root).where(builder.equal(root.get("userId"), userId));

        Query<ToDo> query = session.createQuery(criteria);
        toDos = query.getResultList();
        session.getTransaction().commit();

        List<ToDoModel> models = new ArrayList<>();

        for (ToDo todo : toDos) models.add(new ToDoModel(todo));

        return models;
    }

    public ToDoModel add(String userId, ToDoLabelModel toDoLabelModel) {
        Session session = factory.getCurrentSession();
        ToDo toDo = new ToDo(userId, toDoLabelModel);
        session.beginTransaction();
        session.save(toDo);
        session.getTransaction().commit();

        return new ToDoModel(toDo);
    }

    public void delete(String userId, String id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        ToDo toDo = session.get(ToDo.class, id);

        if (toDo == null) return;

        if (toDo.getUserId().equals(userId)) {
            session.delete(toDo);
        }

        session.getTransaction().commit();
    }

    public ToDoModel setDone(String userId, String id, BooleanModel bool) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        ToDo toDo = session.get(ToDo.class, id);

        if (toDo == null) return null;

        if (toDo.getUserId().equals(userId)) {
            toDo.setDone(bool.value());
        }

        session.getTransaction().commit();

        return new ToDoModel(toDo);
    }

}
