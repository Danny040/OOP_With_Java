import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;

public class ContactList {
    private List<Person> contacts;

    public ContactList() {
        this.contacts = new ArrayList<>();
    }

    public void addPerson(Person person) {
        contacts.add(person);
    }

    public List<Person> searchByName(String name) {
        return contacts.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Person> searchByEmail(String email) {
        return contacts.stream()
                .filter(person -> person.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public void printAllContacts() {
        contacts.forEach(System.out::println);
    }

    public void writeToJson(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), contacts);
    }

    public void readFromJson(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        contacts = mapper.readValue(new File(filename), new TypeReference<List<Person>>(){});
    }
}
