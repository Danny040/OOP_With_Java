import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ContactList contactList = new ContactList();

        // Adding some contacts
        contactList.addPerson(new Person("John Doe", "john@example.com", "1234567890", "123 Main St", 30));
        contactList.addPerson(new Person("Jane Smith", "jane@example.com", "0987654321", "456 Elm St", 25));

        // Printing all contacts
        System.out.println("All Contacts:");
        contactList.printAllContacts();

        // Searching for a contact by name
        System.out.println("\nSearch by Name 'John Doe':");
        contactList.searchByName("John Doe").forEach(System.out::println);

        // Writing contacts to JSON
        try {
            contactList.writeToJson("contacts.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading contacts from JSON
        try {
            contactList.readFromJson("contacts.json");
            System.out.println("\nContacts after reading from JSON:");
            contactList.printAllContacts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
