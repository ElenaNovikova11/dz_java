// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что в во входной структуре 
// будут повторяющиеся имена с разными телефонами, их необходимо считать,
// как одного человека с разными телефонами. Вывод должен быть отсортирован 
// по убыванию числа телефонов.

import java.util.*;

public class PhoneBook {
    public static void main(String[] args) {
        Map<String, List<String>> phonBook = new HashMap<>();
        String[][] input = {
                { "Lena", "1565615111" },
                { "Dima", "9876543210" },
                { "Sveta", "9952231125" },
                { "Dima", "95223322111" },
                { "Dima", "55256265255" },
                { "Lena", "5562147788" },
                { "Sveta", "1255586666" },
                { "Lena", "5677788888" },
                { "Dima", "5465565656" },
        };

        System.out.println("Отсортированный список, у кого сколько телефонов");
        for (String[] entry : input) {
            String name = entry[0];
            String phoneNumber = entry[1];

            // Если имя уже есть в книге, добавляем телефон в список
            if (phonBook.containsKey(name)) {
                List<String> phoneNumbers = phonBook.get(name);
                phoneNumbers.add(phoneNumber);
            } else {
                // Иначе создаем новую запись в книге с данным именем и телефоном
                List<String> phoneNumbers = new ArrayList<>();
                phoneNumbers.add(phoneNumber);
                phonBook.put(name, phoneNumbers);
            }
        }

        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phonBook.entrySet());

        // Сортируем список по убыванию числа телефонов
        Collections.sort(entries, new Comparator<Map.Entry<String, List<String>>>() {
            @Override
            public int compare(Map.Entry<String, List<String>> o1, Map.Entry<String, List<String>> o2) {
                return o2.getValue().size() - o1.getValue().size();
            }
        });

        // Выводим отсортированные записи
        for (Map.Entry<String, List<String>> entry : entries) {
            String name = entry.getKey();
            List<String> phonNums = entry.getValue();

            System.out.println(name + ": " + phonNums.size() + " телефона");
            for (String phoneNum : phonNums) {
                System.out.println(phoneNum);
            }
            System.out.println();
        }

        menu(phonBook);
    }

    public static String scanner() {
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.nextLine();
        // scanner.close();
        return scan;
    }

    public static void get(Map<String, List<String>> phonBook) {
        System.out.println("Введите имя: ");
        String name = scanner();
        System.out.println(phonBook.get(name));
    }

    public static void allBook(Map<String, List<String>> phonBook) {
        System.out.println(phonBook);

    }

    public static Map<String, List<String>> add(Map<String, List<String>> phonBook) {
        System.out.println("Чтобы завершить ввод, введите 'stop'");
        System.out.println("Введите имя: ");
        String name = scanner();
        List<String> number = new ArrayList<>();
        while (true) {
            System.out.println("Введите номер телефона: ");
            String phon = scanner();
            if (phon.equals("stop")) {
                break;
            } else {
                number.add(phon);
            }
        }
        phonBook.put(name, number);

        return phonBook;
    }

    public static Map<String, List<String>> menu(Map<String, List<String>> phonBook) {
        System.out.println(
                "Для работы со справочником наберите следующие команды: get - найти контакт, add - добавить контакт, book - показать все данные книги, exit - выйти");
        while (true) {
            String comands = scanner();
            if (comands.equals("exit")) {
                break;
            } else {
                switch (comands) {
                    case "get":
                        get(phonBook);
                        break;
                    case "add":
                        add(phonBook);
                        break;
                    case "book":
                        allBook(phonBook);
                        break;
                    default:
                        break;
                }
            }
        }
        return phonBook;
    }
}

