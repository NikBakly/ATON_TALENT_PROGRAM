package TaskForFirstTeam;

import TaskForFirstTeam.exception.NotFoundException;

import java.util.Optional;
import java.util.TreeMap;
import java.util.logging.Logger;

/*
Структура данных, представленная в ТЗ, описывается классом Person.
Выбрал коллекцию для хранения TreeMap, потому что:
 - Время поиска записи(Person) O[log(n)]. Ограничение из ТЗ
 - TreeMap использует только тот объем памяти, который необходим для хранения ее элементов. Ограничение из ТЗ
 */
public class PersonInMemoryImpl implements PersonInMemory {
    private final TreeMap<Long, Person> personsByAccount = new TreeMap<>();
    private final TreeMap<String, Person> personsByName = new TreeMap<>();
    private final TreeMap<Double, Person> personsByValue = new TreeMap<>();

    private final Logger log = Logger.getLogger(PersonInMemoryImpl.class.getName());

    @Override
    public void addPerson(Person newPerson) {
        checkPersonForNull(newPerson);
        //Если поле Person уже существует, то мы обновим Person.
        if (personsByAccount.containsKey(newPerson.getAccount())) {
            updatePersonByAccount(newPerson.getAccount(), newPerson);
            return;
        }
        if (personsByName.containsKey(newPerson.getName())) {
            updatePersonByName(newPerson.getName(), newPerson);
            return;
        }
        if (personsByValue.containsKey(newPerson.getValue())) {
            updatePersonByValue(newPerson.getValue(), newPerson);
            return;
        }
        personsByAccount.put(newPerson.getAccount(), newPerson);
        personsByName.put(newPerson.getName(), newPerson);
        personsByValue.put(newPerson.getValue(), newPerson);
        log.info("Person was added successfully.");
    }

    @Override
    public void deletePerson(Person person) {
        checkPersonForNull(person);
        personsByAccount.remove(person.getAccount());
        personsByName.remove(person.getName());
        personsByValue.remove(person.getValue());
        log.info("Person was deleted successfully.");
    }

    @Override
    public void updatePersonByAccount(Long oldAccount, Person updatedPerson) {
        checkPersonForNull(updatedPerson);
        Person foundPerson = findPersonByAccount(oldAccount)
                .orElseThrow(() -> {
                    log.warning("Person with account=" + oldAccount + " was not found.");
                    return new NotFoundException("Person with account=" + oldAccount + " was not found.");
                });
        deleteAndAddPerson(foundPerson, updatedPerson);
        log.info("Person was updated by account " + oldAccount + " successfully.");
    }

    @Override
    public void updatePersonByName(String oldName, Person updatedPerson) {
        checkPersonForNull(updatedPerson);
        Person foundPerson = findPersonByName(oldName)
                .orElseThrow(() -> {
                    log.warning("Person with name=" + oldName + " was not found.");
                    return new NotFoundException("Person with name=" + oldName + " was not found.");
                });
        deleteAndAddPerson(foundPerson, updatedPerson);
        log.info("Person was updated by name " + oldName + " successfully.");
    }

    @Override
    public void updatePersonByValue(Double oldValue, Person updatedPerson) {
        checkPersonForNull(updatedPerson);
        Person foundPerson = findPersonByValue(oldValue)
                .orElseThrow(() -> {
                    log.warning("Person with value=" + oldValue + " was not found.");
                    return new NotFoundException("Person with value=" + oldValue + " was not found.");
                });
        deleteAndAddPerson(foundPerson, updatedPerson);
        log.info("Person was updated by value " + oldValue + " successfully.");
    }

    @Override
    public Optional<Person> findPersonByAccount(Long account) {
        Person foundPerson = personsByAccount.get(account);
        log.info("Person was found by account " + account + " successfully.");
        return Optional.ofNullable(foundPerson);
    }

    @Override
    public Optional<Person> findPersonByName(String name) {
        Person foundPerson = personsByName.get(name);
        log.info("Person was found by name + " + name + " successfully.");
        return Optional.ofNullable(foundPerson);
    }

    @Override
    public Optional<Person> findPersonByValue(Double value) {
        Person foundPerson = personsByValue.get(value);
        log.info("Person was found by value + " + value + " successfully.");
        return Optional.ofNullable(foundPerson);
    }

    private void deleteAndAddPerson(Person oldPerson, Person updatedPerson) {
        deletePerson(oldPerson);
        addPerson(updatedPerson);
    }

    private void checkPersonForNull(Person person) {
        if (person == null) {
            log.warning("Person cannot be null.");
            throw new IllegalArgumentException("Person cannot be null.");
        }
    }

}
