package views;

import business.entities.RamModule;
import services.IService;
import services.RamModuleService;
import views.menus.MainMenu;

public class Main {

    public static void main(String[] args) {
        try {
            IService<RamModule> ramModuleService = RamModuleService.getInstance();
            MainMenu menu = new MainMenu(ramModuleService);
            menu.process();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
