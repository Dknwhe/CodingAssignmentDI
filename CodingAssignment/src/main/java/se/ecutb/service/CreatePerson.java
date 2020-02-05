package se.ecutb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import se.ecutb.data.IdSequencers;
import se.ecutb.data.PersonRepository;
import se.ecutb.data.TodoRepository;
import se.ecutb.model.AbstractPersonFactory;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.List;


@Component
public class CreatePerson extends AbstractPersonFactory implements CreatePersonService {

    private IdSequencers idSequencers;
    private PersonRepository personRepository;
    private AbstractPersonFactory abstractPersonFactory;


    @Autowired
    public CreatePerson(IdSequencers idSequencers, PersonRepository personRepository) {
        this.idSequencers = idSequencers;
        this.personRepository = personRepository;
    }

    @Override
    public Person create(String firstName, String lastName, String email) throws IllegalArgumentException {
        List<Person> people = personRepository.findAll();
        for (Person person : people) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalArgumentException ("Error");
            }
        }
        return createNewPerson(idSequencers.nextPersonId(),firstName,lastName,email,null);
    }

    @Override
    public Person create(String firstName, String lastName, String email, Address address) throws IllegalArgumentException {
    return null;
    }
}
