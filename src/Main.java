import java.util.*;

public class Main {
    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }
            if (input.equalsIgnoreCase("LIST")) {
                Set<String> contacts = phoneBook.getAllContacts();
                printContacts(contacts);
            } else if (phoneBook.isPhone(input)) {
                if (phoneBook.ifContainsPhone(input)) {
                    String contact = phoneBook.getContactByPhone(input);
                    System.out.println(contact);
                } else {
                    System.out.println("Такого номера нет в телефонной книге.");
                    System.out.println("Введите имя абонента для номера " + input);
                    String name = scanner.nextLine();
                    phoneBook.addContact(input, name);
                }
            } else if (phoneBook.isName(input)) {
                if (!phoneBook.ifContainsName(input)) {
                    System.out.println("Такого имени в телефонной книге нет.");
                    System.out.println("Введите имя абонента для номера " + "\"" + input + "\":");
                    String phone = scanner.nextLine();
                    phoneBook.addContact(phone, input);
                } else {
                    Set<String> contact = phoneBook.getContactByName(input);
                    printContacts(contact);
                }
            } else {
                System.out.println("Неверный формат ввода");
            }
        }
    }

    public static void printContacts(Set<String> contacts) {
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }
}
