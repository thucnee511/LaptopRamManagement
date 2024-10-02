package views.menus;

import java.util.Date;
import java.util.List;

import business.entities.RamModule;
import java.util.stream.Collectors;
import services.IService;
import utils.Menu;

public final class MainMenu {

    private final Menu menu = new Menu("=============== Laptop RAM Management ===============");
    private IService<RamModule> service;

    public MainMenu(IService<RamModule> service) throws Exception {
        this.service = service;
        menu.add("Add Item.");
        menu.add("Search Item.");
        menu.add("Update Item Information.");
        menu.add("Delete Item.");
        menu.add("Show All Items.");
        menu.add("Save Item To File.");
        menu.add("Quit Program.");
    }

    public void process() {
        int option;
        do {
            menu.show();
            option = menu.getOption();
            switch (option) {
                case 1:
                    do {
                        System.out.println("Add Item.");
                        String code = utils.InputHandler.getString("Enter code: ", "[A-Z]{3}[0-9]{3}");
                        String type = utils.InputHandler.getString("Enter type: ");
                        String bus = utils.InputHandler.getString("Enter bus: ");
                        String brand = utils.InputHandler.getString("Enter brand: ");
                        int quantity = utils.InputHandler.getIntPositive("Enter quantity: ");
                        Date production_month_year = utils.InputHandler.getDate("Enter production month/year: ");
                        boolean active = true;
                        RamModule item = new RamModule(code, type, bus, brand, quantity, production_month_year, active);
                        try {
                            service.add(item);
                            System.out.println("Item added successfully.");
                        } catch (Exception ex) {
                            System.out.println("Error adding item: " + ex.getMessage());
                        }
                    } while (utils.InputHandler.getBoolean("Do you want to add another item?"));
                    break;
                case 2:
                    System.out.println("Search Item.");
                    new SearchMenu(service).process();
                    break;
                case 3:
                    System.out.println("Update Item Information.");
                    // update item
                    String codeUpdate = utils.InputHandler.getString("Enter code: ", "[A-Z]{3}[0-9]{3}");
                    try {
                        List<RamModule> items = service.getAll();
                        RamModule itemUpdate = items.stream().filter(i -> i.getCode().equalsIgnoreCase(codeUpdate))
                                .findFirst().orElse(null);
                        if (itemUpdate != null) {
                            String typeUpdate = utils.InputHandler.getString("Enter type: ");
                            String busUpdate = utils.InputHandler.getString("Enter bus: ");
                            String brandUpdate = utils.InputHandler.getString("Enter brand: ");
                            int quantityUpdate = utils.InputHandler.getIntPositive("Enter quantity: ");
                            Date production_month_yearUpdate = utils.InputHandler
                                    .getDate("Enter production month/year: ");
                            itemUpdate.setType(typeUpdate);
                            itemUpdate.setBus(busUpdate);
                            itemUpdate.setBrand(brandUpdate);
                            itemUpdate.setQuantity(quantityUpdate);
                            itemUpdate.setProduction_month_year(production_month_yearUpdate);
                            service.update(itemUpdate);
                            System.out.println("Item updated successfully.");
                        } else {
                            throw new Exception("Item not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error updating item: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Delete Item.");
                    try {
                        List<RamModule> items = service.getAll();
                        items = items.stream().filter(i -> i.isActive()).collect(Collectors.toList());
                        items.forEach(System.out::println);
                        String codeDelete = utils.InputHandler.getString("Enter code: ", "[A-Z]{3}[0-9]{3}");
                        RamModule itemDelete = items.stream().filter(i -> i.getCode().equalsIgnoreCase(codeDelete))
                                .findFirst().orElse(null);
                        if (itemDelete != null) {
                            if (utils.InputHandler.getBoolean("Are you sure you want to delete this item?")) {
                                service.delete(itemDelete);
                                System.out.println("Item deleted successfully.");
                            }
                        } else {
                            throw new Exception("Item not found.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error deleting item: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Show All Items.");
                    try {
                        List<RamModule> items = service.getAll();
                        items = items.stream().filter(i -> i.isActive()).collect(Collectors.toList());
                        items.forEach(System.out::println);
                    } catch (Exception e) {
                        System.out.println("Error showing items: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Save Item To File.");
                    try {
                        service.save();
                        System.out.println("Items saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving items: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Quit Program.");
                    break;
            }
            utils.InputHandler.getString("Press Enter to continue...");
        } while (option != 7);
    }
}
