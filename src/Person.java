import java.io.Serializable;

public class Person implements Serializable {

    // object fields
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;


    // constructor without parameters
    public Person() {
    }


    // constructor with parameters
    Person(String name, String surname, String address, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }

    // getters and setters
    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    String getAddress() {
        return address;
    }

    String getPhoneNumber() {
        return phoneNumber;
    }

    // toString method
    @Override
    public String toString() {
        return getName() + ", " + getSurname() + ", " + getAddress() + ", " + getPhoneNumber();
    }
}
