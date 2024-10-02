package utils;

import java.util.ArrayList;
import java.util.List;

public final class Menu {

    private final List<String> options = new ArrayList<>();
    private String title;

    public Menu(String title) {
        this.title = title;
    }

    public void add(String option) {
        options.add(option);
    }

    public void show() {
        System.out.println(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
        String endMenu = "";
        for (int i = 0; i < title.length(); i++) {
            endMenu += "=";
        }
        System.out.println(endMenu);
    }

    public int getOption() {
        int option = 0;
        do {
            try {
                option = InputHandler.getInt("Enter option: ");
            } catch (NumberFormatException ex) {
                System.out.println("Invalid option. Try again.");
            }
        } while (option < 1 || option > options.size());
        return option;
    }
}
