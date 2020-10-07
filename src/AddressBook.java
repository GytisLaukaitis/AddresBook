import java.io.*;
import java.util.*;

public class AddressBook {
    private static final Scanner in = new Scanner(System.in);

    // initialize address book people array
    private List<Person> people = new ArrayList<>();

    public static void main(String[] args) throws IOException {


        // new main method
        AddressBook addressBook = new AddressBook();
        System.out.println("List of records in address book");
        addressBook.readPeopleFromFile();
        System.out.println();
        addressBook.showMainMenu();

    }


    // sort records by surname and address
    private void order(List<Person> people) {

        people.sort(new Comparator() {

            public int compare(Object o1, Object o2) {

                String x1 = ((Person) o1).getSurname();
                String x2 = ((Person) o2).getSurname();
                int sComp = x1.compareTo(x2);

                if (sComp != 0) {
                    return sComp;
                }

                String x3 = ((Person) o1).getAddress();
                String x4 = ((Person) o2).getAddress();
                return x3.compareTo(x4);
            }
        });
    }

    // call overloaded find methods
    private void findPerson() throws IOException {
        System.out.println("1. Find with name and surname");
        System.out.println("2. Find with name,surname and address");

        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    find("", "");
                    break;
                case "2":
                    find("", "", "");
                    break;
                default:
                    System.out.print("Choose 1 or 2: ");
            }
        } while (!choice.equals("1") && !choice.equals("2"));
        System.out.println();
        showMainMenu();
    }


    // find method based on person name and surname
    private void find(String nameToFind, String surnameToFind) {
        System.out.print("Enter name: ");
        nameToFind = in.nextLine();
        System.out.print("Enter surname: ");
        surnameToFind = in.nextLine();
        int matches = 0;
        for (Person person : people) {
            if (person.getName().equals(nameToFind) && person.getSurname().equals(surnameToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if (matches <= 0) {
            System.out.println("There is no person with this name or surname");
        }
    }

    // find method based on person name, surname and address
    private void find(String nameToFind, String surnameToFind, String addressToFind) {
        System.out.print("Enter name: ");
        nameToFind = in.nextLine();
        System.out.print("Enter surname: ");
        surnameToFind = in.nextLine();
        System.out.print("Enter city: ");
        addressToFind = in.nextLine();
        int matches = 0;
        for (Person person : people) {
            if (person.getName().equals(nameToFind) && person.getSurname().equals(surnameToFind) && person.getAddress().equals(addressToFind)) {
                System.out.println(person);
                matches++;
            }
        }
        if (matches <= 0) {
            System.out.println("There is no person with this name,surname or address ");
        }
    }

    // read objects from file
    private void readPeopleFromFile() throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("resources/input/text.csv"));

            String str;
            String cvsSplitBy = ",".trim();

            while ((str = in.readLine()) != null) {
                String[] chunks = str.split(cvsSplitBy);


                people.add(new Person(chunks[0].trim(), chunks[1].trim(), chunks[2].trim(), chunks[3].trim()));

            }
            people.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }

        }
    }


    // display console use interface (CUI) menu
    private void showMainMenu() throws IOException {
        System.out.println("1. Find person");
        System.out.println("2. Show all contacts");
        System.out.println("3. Check number of records in a certain city");
        System.out.println("4. Close program");

        String choice;
        do {
            choice = in.nextLine();
            switch (choice) {
                case "1":
                    order(people);
                    findPerson();
                    break;
                case "2":
                    order(people);
                    showPerson();
                    System.out.println();
                    showMainMenu();
                    break;
                case "3":
                    order(people);
                    readPeopleFromFile();
                    countPersonsInArea();
                    System.out.println();
                    showMainMenu();
                    System.out.println();
                    break;
                case "4":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter number from 1 to 4");
            }
        } while (!choice.equals("4"));
    }

    // display person record list on demand
    public void showPerson() {
        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
        }
    }

    // count persons in desired area
    public void countPersonsInArea() {
        System.out.print("Enter city name: ");
        String cityToFind = in.nextLine();
        int matches = 0;
        for (Person person : people) {
            if (person.getAddress().equals(cityToFind)) {
                matches++;
            }
        }
        if (matches <= 0) {
            System.out.println("There is no records in this area ");
        } else {
            System.out.println(matches);
        }
    }
}

