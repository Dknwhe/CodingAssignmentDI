package se.ecutb.service;

import se.ecutb.dto.PersonDto;
import se.ecutb.dto.PersonDtoWithTodo;
import se.ecutb.model.Person;
import se.ecutb.model.Todo;

import java.util.List;

public class PersonDTOImpl implements PersonDtoConversionService {


    @Override
    public PersonDto convertToPersonDto(Person person) {
        return null;
    }

    @Override
    public PersonDtoWithTodo convertToPersonDtoWithTodo(Person person, List<Todo> assignedTodos) {
        return null;
    }
}
