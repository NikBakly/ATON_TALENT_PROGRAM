package TaskForFirstTeam;

import java.util.Optional;

/*
Интерфейс позволяет:
- добавлять новые записи;
- удалять более не нужные записи;
- изменять запись по любому из полей;
- получать полный набор записи (объект Person) по любому из полей;
*/

public interface PersonInMemory {

    void addPerson(Person newPerson);

    void deletePerson(Person person);

    void updatePersonByAccount(Long oldAccount, Person updatedPerson);

    void updatePersonByName(String oldName, Person updatedPerson);

    void updatePersonByValue(Double oldValue, Person updatedPerson);

    Optional<Person> findPersonByAccount(Long account);

    Optional<Person> findPersonByName(String name);

    Optional<Person> findPersonByValue(Double value);
}
