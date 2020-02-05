package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class TodoImpl implements TodoRepository {

    private List<Todo> todos = new ArrayList<>();

    @Override
    public Todo persist(Todo todo) {
        if(!todos.contains(todo)) {
            todos.add(todo);
        }
        return todo;
    }

    @Override
    public Optional<Todo> findById(int todoId) {
        return todos.stream().filter(todo -> todo.getTodoId() == todoId).findFirst();
    }

    @Override
    public List<Todo> findByTaskDescriptionContains(String taskDescription) {
        return todos.stream().filter(todo -> todo.getTaskDescription().toLowerCase().contains(taskDescription)).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineBefore(LocalDate endDate) {
        return todos.stream().filter(todo -> todo.getDeadLine().isBefore(endDate)).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineAfter(LocalDate startDate) {
        return todos.stream().filter(todo -> todo.getDeadLine().isAfter(startDate)).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDeadLineBetween(LocalDate start, LocalDate end) {
        return todos.stream().filter(todo -> todo.getDeadLine().isBefore(end) && todo.getDeadLine().isAfter(start)).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByAssigneeId(int personId) {
        return todos.stream().filter(todo -> todo.getAssignee() != null).filter(todo -> todo.getAssignee().getPersonId() == personId).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAllUnassignedTasks() {
        return todos.stream().filter(todo -> todo.getAssignee() == null).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAllAssignedTasks() {
        return todos.stream().filter(todo -> todo.getAssignee() != null).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findByDone(boolean isDone) {
        return todos.stream().filter(Todo::isDone).collect(Collectors.toList());
    }

    @Override
    public List<Todo> findAll() {
        return todos;
    }

    @Override
    public boolean delete(int todoId) throws IllegalArgumentException {
        Todo remove = findById(todoId).get();
        return todos.remove(remove);
    }

    @Override
    public void clear() {
        todos.clear();
    }
}
