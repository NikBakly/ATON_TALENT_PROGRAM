import TaskForFirstTeam.Person;
import TaskForFirstTeam.PersonInMemory;
import TaskForFirstTeam.PersonInMemoryImpl;

public class Main {
    public static void main(String[] args) {
        PersonInMemory personInMemory = new PersonInMemoryImpl();

        personInMemory.addPerson(new Person(
                686L,
                "SPiQ",
                974.25
        ));
        personInMemory.addPerson(new Person(
                686L,
                "SPiQ",
                974.25
        ));


    }
}
