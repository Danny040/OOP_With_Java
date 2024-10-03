import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

class Person {
    private String name;
    private String email;
    private String phone;
    private String address;
    private int age;

    // Constructor
    public Person(String name, String email, String phone, String address, int age) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public int getAge() { return age; }

    // Convert Person to String format for display
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone +
                ", Address: " + address + ", Age: " + age;
    }
}

class ContactListApp {
    private List<Person> contactList = new ArrayList<>();

    // Add a person to the contact list
    public void addContact(Person person) {
        contactList.add(person);
    }

    // Print the entire contact list
    public void printContactList() {
        for (Person person : contactList) {
            System.out.println(person);
        }
    }

    // Search for contacts by different parameters
    public List<Person> searchByName(String name) {
        List<Person> result = new ArrayList<>();
        for (Person person : contactList) {
            if (person.getName().equalsIgnoreCase(name)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> searchByEmail(String email) {
        List<Person> result = new ArrayList<>();
        for (Person person : contactList) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                result.add(person);
            }
        }
        return result;
    }

    public List<Person> searchByPhone(String phone) {
        List<Person> result = new ArrayList<>();
        for (Person person : contactList) {
            if (person.getPhone().equals(phone)) {
                result.add(person);
            }
        }
        return result;
    }

    // Save contact list to XML file
    public void saveToXML(String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("Contacts");
        doc.appendChild(root);

        for (Person person : contactList) {
            Element contact = doc.createElement("Contact");

            Element name = doc.createElement("Name");
            name.appendChild(doc.createTextNode(person.getName()));
            contact.appendChild(name);

            Element email = doc.createElement("Email");
            email.appendChild(doc.createTextNode(person.getEmail()));
            contact.appendChild(email);

            Element phone = doc.createElement("Phone");
            phone.appendChild(doc.createTextNode(person.getPhone()));
            contact.appendChild(phone);

            Element address = doc.createElement("Address");
            address.appendChild(doc.createTextNode(person.getAddress()));
            contact.appendChild(address);

            Element age = doc.createElement("Age");
            age.appendChild(doc.createTextNode(String.valueOf(person.getAge())));
            contact.appendChild(age);

            root.appendChild(contact);
        }

        // Write to XML file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File(fileName));
        transformer.
        setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, streamResult);

        System.out.println("Contacts saved to " + fileName);
    }

    // Load contacts from XML file
    public void loadFromXML(String fileName) throws Exception {
        contactList.clear();

        File xmlFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        NodeList nodeList = doc.getElementsByTagName("Contact");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String name = element.getElementsByTagName("Name").item(0).getTextContent();
                String email = element.getElementsByTagName("Email").item(0).getTextContent();
                String phone = element.getElementsByTagName("Phone").item(0).getTextContent();
                String address = element.getElementsByTagName("Address").item(0).getTextContent();
                int age = Integer.parseInt(element.getElementsByTagName("Age").item(0).getTextContent());

                Person person = new Person(name, email, phone, address, age);
                contactList.add(person);
            }
        }

        System.out.println("Contacts loaded from " + fileName);
    }

    // Main program
    public static void main(String[] args) {
        ContactListApp app = new ContactListApp();
        Scanner scanner = new Scanner(System.in);

        // Sample data for testing
        app.addContact(new Person("Alice", "alice@example.com", "1234567890", "123 Apple St", 30));
        app.addContact(new Person("Bob", "bob@example.com", "0987654321", "456 Orange St", 25));

        boolean running = true;
        while (running) {
            System.out.println("1. Add Contact");
            System.out.println("2. Print Contact List");
            System.out.println("3. Search Contact by Name");
            System.out.println("4. Search Contact by Email");
            System.out.println("5. Search Contact by Phone");
            System.out.println("6. Save Contacts to XML");
            System.out.println("7. Load Contacts from XML");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    // Add a new contact
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    app.addContact(new Person(name, email, phone, address, age));
                    break;
                case 2:
                    // Print the contact list
                    app.printContactList();
                    break;
                case 3:
                    // Search by name
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    List<Person> nameResults = app.searchByName(name);
                    if (nameResults.isEmpty()) {
                        System.out.println("No contacts found with name " + name);
                    } else {
                        for (Person p : nameResults) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 4:


// Search by email
                System.out.print("Enter email to search: ");
                    email = scanner.nextLine();
                    List<Person> emailResults = app.searchByEmail(email);
                    if (emailResults.isEmpty()) {
                        System.out.println("No contacts found with email " + email);
                    } else {
                        for (Person p : emailResults) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 5:
                    // Search by phone
                    System.out.print("Enter phone to search: ");
                    phone = scanner.nextLine();
                    List<Person> phoneResults = app.searchByPhone(phone);
                    if (phoneResults.isEmpty()) {
                        System.out.println("No contacts found with phone " + phone);
                    } else {
                        for (Person p : phoneResults) {
                            System.out.println(p);
                        }
                    }
                    break;
                case 6:
                    // Save contacts to XML
                    try {
                        System.out.print("Enter XML file name to save: ");
                        String fileName = scanner.nextLine();
                        app.saveToXML(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    // Load contacts from XML
                    try {
                        System.out.print("Enter XML file name to load: ");
                        String fileName = scanner.nextLine();
                        app.loadFromXML(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}

