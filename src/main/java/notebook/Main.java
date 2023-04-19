package notebook;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Notebook notebook1 = new Notebook(10);
        notebook1.brand = "Lenono";
        notebook1.model = "IdeaPad 3N";
        notebook1.condition = "new";
        notebook1.color = "Black";
        notebook1.screenSize = 15;
        notebook1.modelCPU = "AMD Ryzen 5 5625U";
        notebook1.modelGPU = "Intel Iris Xe Graphics";
        notebook1.amountRAM = 4;
        notebook1.amountHUD = 256;

        Notebook notebook2 = new Notebook(20);
        notebook2.brand = "Acer";
        notebook2.model = "Nitro 5P4black";
        notebook2.condition = "resale";
        notebook2.color = "Black";
        notebook2.screenSize = 17;
        notebook2.modelCPU = "Intel Core i5";
        notebook2.modelGPU = " NVIDIA GeForce GTX 1650";
        notebook2.amountRAM = 4;
        notebook2.amountHUD = 512;

        Notebook notebook3 = new Notebook(30);
        notebook3.brand = "Xiaomi";
        notebook3.model = "RedmiBook 15";
        notebook3.condition = "new";
        notebook3.color = "Silver";
        notebook3.screenSize = 15;
        notebook3.modelCPU = "Intel Core i5";
        notebook3.modelGPU = "Intel Iris Xe Graphics";
        notebook3.amountRAM = 8;
        notebook3.amountHUD = 512;

        Notebook notebook4 = new Notebook(40);
        notebook4.brand = "Lenono";
        notebook4.model = "IdeaPad 3S";
        notebook4.condition = "resale";
        notebook4.color = "Silver";
        notebook4.screenSize = 15;
        notebook4.modelCPU = "AMD Ryzen 5 5625U";
        notebook4.modelGPU = "Intel Iris Xe Graphics";
        notebook4.amountRAM = 8;
        notebook4.amountHUD = 1024;

        List<Notebook> notebookList = new ArrayList<>();
        notebookList.add(notebook1);
        notebookList.add(notebook2);
        notebookList.add(notebook3);
        notebookList.add(notebook4);

        HashSet<Notebook> hashNotebook = new HashSet(notebookList);
        HashSet<Notebook> result = new HashSet<>();
        HashMap<String, Object> filters = new HashMap<>();

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("""
                    Введите цифру, соответствующую необходимому критерию:
                     1 - диагональ экрана\s
                     2 - цвет ноутбука\s
                     3 - объём жесткого диска\s
                     4 - объём оперативной памяти\s
                     5 - показать результаты поиска\s
                     6 - список всех ноутбуков\s
                     0 - выход из программы""");
            int num = scan.nextInt();
            scan.nextLine();
            switch (num) {
                case 1 -> filterScreenSize(filters);
                case 2 -> filterColor(filters, hashNotebook);
                case 3 -> filterHUD(filters);
                case 4 -> filterRAM(filters);
                case 5 -> {
                    printSearch(getResult(hashNotebook, filters));
                    filters = new HashMap<>();
                }
                case 6 -> printAll(hashNotebook);
                case 0 -> System.exit(1);
                default -> System.out.println("Введено неверное значение");
            }

        }
    }

    private static HashSet<Notebook> getResult(HashSet<Notebook> hashSet, HashMap<String, Object> filter) {
        for (Map.Entry<String, Object> entry : filter.entrySet()) {
            HashSet<Notebook> result;
            result = getResultOfOneIteration(hashSet, entry);
            hashSet = new HashSet<>(result);
        }
        return hashSet;
    }

    private static HashSet<Notebook> getResultOfOneIteration(HashSet<Notebook> hashSet, Map.Entry<String, Object> filter) {
        HashSet<Notebook> result = new HashSet<>();
        if (filter.getKey().equals("screenSize")) {
            for (Notebook item : hashSet) {
                if (item.getScreenSize() >= (Integer) filter.getValue()) {
                    result.add(item);
                }
            }
        }
        if (filter.getKey().equals("color")) {
            for (Notebook item : hashSet) {
                if (item.getColor().equals(filter.getValue())) {
                    result.add(item);
                }
            }
        }
        if (filter.getKey().equals("amountHUD")) {
            for (Notebook item : hashSet) {
                if (item.getAmountHUD() >= (Integer) filter.getValue()) {
                    result.add(item);
                }
            }
        }
        if (filter.getKey().equals("amountRAM")) {
            for (Notebook item : hashSet) {
                if (item.getAmountRAM() >= (Integer) filter.getValue()) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    private static void printSearch(HashSet<Notebook> hashSet) {
        if (hashSet.size() != 0) {
            for (Notebook item : hashSet) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("Поиск не дал результатов. Введите другие параметры");
        }
    }

    private static HashMap<String, Object> filterScreenSize(HashMap<String, Object> tempMap) {
        System.out.println("Введите минимальный размер экрана, дюйм: ");
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        tempMap.put("screenSize", size);
        return tempMap;
    }

    private static HashMap<String, Object> filterColor(HashMap<String, Object> tempMap, HashSet<Notebook> hashSet) {
        Set<String> colors = getAllColorsOfNoteBook(hashSet);
        System.out.println("Выберите цвет из списка:\n " + String.join(",", colors));
        Scanner scan = new Scanner(System.in);
        String color = scan.nextLine();
        tempMap.put("color", color);
        return tempMap;
    }

    private static Set<String> getAllColorsOfNoteBook(HashSet<Notebook> hashSet) {
        Set<String> colors = new HashSet<>();
        for (Notebook item : hashSet) {
            colors.add(item.getColor());
        }
        return colors;
    }

    private static HashMap<String, Object> filterHUD(HashMap<String, Object> tempMap) {
        System.out.println("Введите минимальный объём HUD, ГБ: ");
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        tempMap.put("amountHUD", value);
        return tempMap;
    }

    private static HashMap<String, Object> filterRAM(HashMap<String, Object> tempMap) {
        System.out.println("Введите минимальный объём RAM, ГБ: ");
        Scanner scan = new Scanner(System.in);
        int value = scan.nextInt();
        tempMap.put("amountRAM", value);
        return tempMap;
    }

    private static void printAll(HashSet<Notebook> hashSet) {
        System.out.println("Представленные ноутбуки: ");
        for (var item :
                hashSet) {
            System.out.println(item);
        }
    }
}
