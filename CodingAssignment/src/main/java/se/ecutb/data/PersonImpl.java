package se.ecutb.data;

import org.springframework.stereotype.Component;
import se.ecutb.model.Address;
import se.ecutb.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class PersonImpl implements PersonRepository {


    private List<Person> list = new ArrayList<>();

    @Override
    public Optional<Person> findById(int personId) {
        return list.stream().filter(person -> person.getPersonId() == personId).findFirst();
    }

    @Override
    public Person persist(Person person) throws IllegalArgumentException {
       String email = person.getEmail();
       for(Person person1 : list) {
           if (person1.getEmail().equalsIgnoreCase(email)) {
               throw new IllegalArgumentException("Error");
           }
       }
       list.add(person);
        return person;
    }


    @Override
    public Optional<Person> findByEmail(String email) {
        return list.stream().filter(person -> person.getEmail().equalsIgnoreCase(email)).findAny();
    }

    @Override
    public List<Person> findByAddress(Address address) {
        if (address == null){
            return list.stream().filter(person -> person.getAddress() == null).collect(Collectors.toList());
        }else {
            return list.stream().filter(person -> person.getAddress() != null && person.getAddress().equals(address)).collect(Collectors.toList());
        }
    }

    @Override
    public List<Person> findByCity(String city) {
        List<Person> newCity = new ArrayList<>();
        for (Person person : list) {
            if(person.getAddress() !=null && person.getAddress().getCity().equalsIgnoreCase(city)) {
                newCity.add(person);
            }
        }
        return newCity;
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return list.stream().filter(person -> person.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findByFullName(String fullName) {
        return list.stream().filter(person -> (person.getFirstName()+ " " + person.getLastName()).equalsIgnoreCase(fullName)).collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return list;
    }

    @Override
    public boolean delete(int personId) throws IllegalArgumentException {
        Person remove = findById(personId).get();
        return list.remove(remove);
    }

    @Override
    public void clear() {
        list.clear();
    }
}
