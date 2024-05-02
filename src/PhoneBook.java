import java.util.*;

public class PhoneBook {

    private HashMap<String, Set<String>> phoneBookMap = new HashMap<>();

    public void addContact(String phone, String name) {
        if (phone.isEmpty() || name.isEmpty()) {
            System.out.println("Введена пустая строка!");
        } else if (!isPhone(phone) || !isName(name)) {
            System.out.println("Неверный формат ввода!");
        } else if (ifContainsPhone(phone)) {
            for (Map.Entry<String, Set<String>> entry : phoneBookMap.entrySet()) {
                Set<String> phoneNumbers = entry.getValue();
                if (phoneNumbers.contains(phone)) {
                    String key = entry.getKey();
                    phoneBookMap.remove(key);
                    phoneBookMap.put(name, phoneNumbers);
                    System.out.println("Контакт сохранен!");
                }
            }
        } else {
            if (phoneBookMap.containsKey(name)) {
                phoneBookMap.get(name).add(phone);
                System.out.println("Контакт сохранен!");
            } else {
                Set<String> phones = new TreeSet<>();
                phones.add(phone);
                phoneBookMap.put(name, phones);
                System.out.println("Контакт сохранен!");
            }
        }
    }

    public String getContactByPhone(String phone) {
        String contact = "";
        for (Map.Entry<String, Set<String>> entry : phoneBookMap.entrySet()) {
            Set<String> phoneNumbers = entry.getValue();
            if (phoneNumbers.contains(phone)) {
                contact = entry.getKey().concat(" - ").concat(phone);
            }
        }
        return contact;
    }

    public Set<String> getContactByName(String name) {
        if (phoneBookMap.containsKey(name)) {
            Set<String> contacts = new TreeSet<>();
            String contact = name + " - " + String.join(", ", phoneBookMap.get(name));
            contacts.add(contact);
            return contacts;
        }
        return new TreeSet<>();
    }

    public Set<String> getAllContacts() {
        Set<String> contacts = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : phoneBookMap.entrySet()) {
            String name = entry.getKey();
            Set<String> phones = entry.getValue();
            String contact = name + " - " + String.join(", ", phones);
            contacts.add(contact);
        }
        return contacts;
    }

    public boolean isPhone(String phone) {
        String phoneRegex = "79[0-9]{9}";
        return phone.matches(phoneRegex);
    }

    public boolean isName(String name) {
        String nameRegex = "^[a-zA-Zа-яА-Я]+$";
        return name.matches(nameRegex);
    }

    public boolean ifContainsName(String name) {
        return phoneBookMap.containsKey(name);
    }

    public boolean ifContainsPhone(String phone) {
        for (Map.Entry<String, Set<String>> entry : phoneBookMap.entrySet()) {
            Set<String> phoneNumbers = entry.getValue();
            if (phoneNumbers.contains(phone)) {
                return true;
            }
        }
        return false;
    }
}