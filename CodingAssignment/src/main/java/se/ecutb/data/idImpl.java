package se.ecutb.data;


import org.springframework.stereotype.Component;

@Component
public class idImpl implements IdSequencers {

    private static int personId;
    private static int todoId;

    @Override
    public int nextPersonId() {
        return ++personId;
    }

    @Override
    public int nextTodoId() {
        return ++todoId;
    }

    @Override
    public void clearPersonId() {
        personId = 0;
    }

    @Override
    public void clearTodoId() {
        todoId = 0;
    }
}
