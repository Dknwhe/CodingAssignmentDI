package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.TodoRepository;
import se.ecutb.model.AbstractTodoFactory;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.List;


@Component
public class CreateTodoImpl extends AbstractTodoFactory implements CreateTodoService {

    private TodoRepository todoRepository;
    private IdSequencers idSequencers;

    @Autowired
    public CreateTodoImpl(TodoRepository todoRepository, IdSequencers idSequencers) {
        this.todoRepository = todoRepository;
        this.idSequencers = idSequencers;
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine, Person assignee) throws IllegalArgumentException {
        List<Todo> tacos = todoRepository.findAll();
        for (Todo todo : tacos) {
            if (todo.getTaskDescription().equalsIgnoreCase(taskDescription)) {
                throw new IllegalArgumentException("Error");
            }
            return todo;
        }
        return createTodoItem(idSequencers.nextTodoId(),taskDescription,deadLine,assignee);
    }

    @Override
    public Todo createTodo(String taskDescription, LocalDate deadLine) throws IllegalArgumentException {
        List<Todo> tacos = todoRepository.findAll();
        for (Todo todo : tacos) {
            if (todo.getTaskDescription().equalsIgnoreCase(taskDescription)) {
                throw new IllegalArgumentException("Error");
            }
            return todo;
        }
        return createTodoItem(idSequencers.nextTodoId(), taskDescription, deadLine);

    }
}

