package views.menus;

import business.entities.RamModule;
import java.util.List;
import java.util.stream.Collectors;
import services.IService;
import utils.Menu;

public final class SearchMenu {

    private final Menu menu = new Menu("=============== Search Item ===============");
    private IService<RamModule> service;

    public SearchMenu(IService<RamModule> service) {
        this.service = service;
        menu.add("Search by Type.");
        menu.add("Search by Bus.");
        menu.add("Search by Brand.");
        menu.add("Back to Main Menu.");
    }

    public void process() {
        int option;
        do {
            menu.show();
            option = menu.getOption();
            switch (option) {
                case 1:
                    System.out.println("Search by Type.");
                    String type = utils.InputHandler.getString("Enter type: ");
                    try {
                        List<RamModule> items = service.getAll().stream().filter(i -> i.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
                        if (items.size() > 0) {
                            items.forEach(System.out::println);
                        } else {
                            throw new Exception("Item not found.");
                        }
                    } catch (Exception ex) {
                        System.out.println("Error searching item: " + ex.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Search by Bus.");
                    String bus = utils.InputHandler.getString("Enter bus: ");
                    try {
                        List<RamModule> items = service.getAll().stream().filter(i -> i.getBus().equalsIgnoreCase(bus)).collect(Collectors.toList());
                        if (items.size() > 0) {
                            items.forEach(System.out::println);
                        } else {
                            throw new Exception("Item not found.");
                        }
                    } catch (Exception ex) {
                        System.out.println("Error searching item: " + ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Search by Brand.");
                    String brand = utils.InputHandler.getString("Enter brand: ");
                    try {
                        List<RamModule> items = service.getAll().stream().filter(i -> i.getBrand().equalsIgnoreCase(brand)).collect(Collectors.toList());
                        if (items.size() > 0) {
                            items.forEach(System.out::println);
                        } else {
                            throw new Exception("Item not found.");
                        }
                    } catch (Exception ex) {
                        System.out.println("Error searching item: " + ex.getMessage());
                    }
                    break;
            }
        } while (option != 4);
    }
}
