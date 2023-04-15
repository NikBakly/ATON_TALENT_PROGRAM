import TaskForFirstTeam.Person;
import TaskForFirstTeam.PersonInMemory;
import TaskForFirstTeam.PersonInMemoryImpl;
import TaskForFirstTeam.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PersonInMemoryImplTest {
    private PersonInMemory personInMemory;
    private static Person PERSON_FOR_TEST;
    private static Person UPDATED_PERSON_FOR_TEST;

    @BeforeAll
    static void setPersonForTest() {
        PERSON_FOR_TEST = new Person(867L,
                "Test",
                316.03);
    }

    @BeforeAll
    static void setUpdatePerson() {
        UPDATED_PERSON_FOR_TEST = new Person(480L,
                "updatedTest",
                432.11);
    }

    @BeforeEach
    void setPersonInMemory() {
        personInMemory = new PersonInMemoryImpl();
        personInMemory.addPerson(PERSON_FOR_TEST);
    }


    @Test
    void test1_findByAccountPerson() {
        Optional<Person> foundPerson = personInMemory.findPersonByAccount(PERSON_FOR_TEST.getAccount());
        Assertions.assertTrue(foundPerson.isPresent());
        Assertions.assertEquals(PERSON_FOR_TEST, foundPerson.get());
    }

    @Test
    void test2_findByNamePerson() {
        Optional<Person> foundPerson = personInMemory.findPersonByName(PERSON_FOR_TEST.getName());
        Assertions.assertTrue(foundPerson.isPresent());
        Assertions.assertEquals(PERSON_FOR_TEST, foundPerson.get());
    }

    @Test
    void test3_findByValuePerson() {
        Optional<Person> foundPerson = personInMemory.findPersonByValue(PERSON_FOR_TEST.getValue());
        Assertions.assertTrue(foundPerson.isPresent());
        Assertions.assertEquals(PERSON_FOR_TEST, foundPerson.get());
    }

    @Test
    void test4_deletePerson() {
        personInMemory.deletePerson(PERSON_FOR_TEST);
        Assertions.assertTrue(personInMemory.findPersonByAccount(PERSON_FOR_TEST.getAccount()).isEmpty());
        Assertions.assertTrue(personInMemory.findPersonByName(PERSON_FOR_TEST.getName()).isEmpty());
        Assertions.assertTrue(personInMemory.findPersonByValue(PERSON_FOR_TEST.getValue()).isEmpty());
    }

    @Test
    void test5_updateByAccountPerson() {
        personInMemory.updatePersonByAccount(PERSON_FOR_TEST.getAccount(), UPDATED_PERSON_FOR_TEST);
        Assertions.assertTrue(personInMemory.findPersonByAccount(PERSON_FOR_TEST.getAccount()).isEmpty());
        Assertions.assertTrue(personInMemory.findPersonByAccount(UPDATED_PERSON_FOR_TEST.getAccount()).isPresent());
        Assertions.assertEquals(UPDATED_PERSON_FOR_TEST,
                personInMemory.findPersonByAccount(UPDATED_PERSON_FOR_TEST.getAccount()).get());
    }

    @Test
    void test6_updateByNamePerson() {
        personInMemory.updatePersonByName(PERSON_FOR_TEST.getName(), UPDATED_PERSON_FOR_TEST);
        Assertions.assertTrue(personInMemory.findPersonByName(PERSON_FOR_TEST.getName()).isEmpty());
        Assertions.assertTrue(personInMemory.findPersonByName(UPDATED_PERSON_FOR_TEST.getName()).isPresent());
        Assertions.assertEquals(UPDATED_PERSON_FOR_TEST,
                personInMemory.findPersonByName(UPDATED_PERSON_FOR_TEST.getName()).get());
    }

    @Test
    void test7_updateByValuePerson() {
        personInMemory.updatePersonByValue(PERSON_FOR_TEST.getValue(), UPDATED_PERSON_FOR_TEST);
        Assertions.assertTrue(personInMemory.findPersonByValue(PERSON_FOR_TEST.getValue()).isEmpty());
        Assertions.assertTrue(personInMemory.findPersonByValue(UPDATED_PERSON_FOR_TEST.getValue()).isPresent());
        Assertions.assertEquals(UPDATED_PERSON_FOR_TEST,
                personInMemory.findPersonByValue(UPDATED_PERSON_FOR_TEST.getValue()).get());
    }

    @Test
    void test8_updatePersonWhenAccountFieldIsWrong() {
        Long wrongAccount = 123L;
        Exception thrown = Assertions.assertThrows(NotFoundException.class,
                () -> personInMemory.updatePersonByAccount(wrongAccount, UPDATED_PERSON_FOR_TEST));
        Assertions.assertEquals(thrown.getMessage(), "Person with account=" + wrongAccount + " was not found.");
    }

    @Test
    void test9_updatePersonWhenNameFieldIsWrong() {
        String wrongName = "wrong";
        Exception thrown = Assertions.assertThrows(NotFoundException.class,
                () -> personInMemory.updatePersonByName(wrongName, UPDATED_PERSON_FOR_TEST));
        Assertions.assertEquals(thrown.getMessage(), "Person with name=" + wrongName + " was not found.");
    }

    @Test
    void test10_updatePersonWhenValueFieldIsWrong() {
        Double wrongValue = 41.1;
        Exception thrown = Assertions.assertThrows(NotFoundException.class,
                () -> personInMemory.updatePersonByValue(wrongValue, UPDATED_PERSON_FOR_TEST));
        Assertions.assertEquals(thrown.getMessage(), "Person with value=" + wrongValue + " was not found.");
    }

    @Test
    void test11_addPersonWhenAccountPersonIsNotUnique() {
        Person newPerson = new Person(
                PERSON_FOR_TEST.getAccount(),
                "18Dti5m",
                950.59
        );
        personInMemory.addPerson(newPerson);
        Optional<Person> actualPerson = personInMemory.findPersonByAccount(PERSON_FOR_TEST.getAccount());
        Assertions.assertTrue(actualPerson.isPresent());
        Assertions.assertNotEquals(PERSON_FOR_TEST, actualPerson.get());
        Assertions.assertEquals(newPerson, actualPerson.get());
    }

    @Test
    void test12_addPersonWhenNamePersonIsNotUnique() {
        Person newPerson = new Person(
                863L,
                PERSON_FOR_TEST.getName(),
                950.59
        );
        personInMemory.addPerson(newPerson);
        Optional<Person> actualPerson = personInMemory.findPersonByName(PERSON_FOR_TEST.getName());
        Assertions.assertTrue(actualPerson.isPresent());
        Assertions.assertNotEquals(PERSON_FOR_TEST, actualPerson.get());
        Assertions.assertEquals(newPerson, actualPerson.get());
    }

    @Test
    void test13_addPersonWhenAccountPersonIsNotUnique() {
        Person newPerson = new Person(
                259L,
                "yTMlRo",
                PERSON_FOR_TEST.getValue()
        );
        personInMemory.addPerson(newPerson);
        Optional<Person> actualPerson = personInMemory.findPersonByValue(PERSON_FOR_TEST.getValue());
        Assertions.assertTrue(actualPerson.isPresent());
        Assertions.assertNotEquals(PERSON_FOR_TEST, actualPerson.get());
        Assertions.assertEquals(newPerson, actualPerson.get());
    }

    @Test
    void test14_addPersonWhenPersonIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> personInMemory.addPerson(null),
                "Expected addPerson() to throw, but it didn't");
    }

}
