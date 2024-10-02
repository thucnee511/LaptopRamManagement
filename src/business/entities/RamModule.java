package business.entities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RamModule extends IEntity<RamModule> {

    private String code;
    private String type;
    private String bus;
    private String brand;
    private int quantity;
    private Date production_month_year;
    private boolean active;

    public RamModule() {
    }

    public RamModule(String code, String type, String bus, String brand, int quantity, Date production_month_year, boolean active) {
        this.code = code;
        this.type = type;
        this.bus = bus;
        this.brand = brand;
        this.quantity = quantity;
        this.production_month_year = production_month_year;
        this.active = active;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getProduction_month_year() {
        return production_month_year;
    }

    public void setProduction_month_year(Date production_month_year) {
        this.production_month_year = production_month_year;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("yyyy/MM");
        String _date = formatter.format(production_month_year);
        return "RAM{" + "code=" + code + ", type=" + type + ", bus=" + bus + ", brand=" + brand + ", quantity=" + quantity + ", production_month_year=" + _date + ", active=" + active + '}';
    }

    public static RamModule fromString(String str) throws ParseException {
        String[] parts = str.subSequence(4, str.length() - 1).toString().split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid string format");
        }
        RamModule ramModule = new RamModule();
        DateFormat formatter = new SimpleDateFormat("yyyy/MM");
        ramModule.setCode(parts[0].split("=")[1]);
        ramModule.setType(parts[1].split("=")[1]);
        ramModule.setBus(parts[2].split("=")[1]);
        ramModule.setBrand(parts[3].split("=")[1]);
        ramModule.setQuantity(Integer.parseInt(parts[4].split("=")[1]));
        ramModule.setProduction_month_year(formatter.parse(parts[5].split("=")[1]));
        ramModule.setActive(Boolean.parseBoolean(parts[6].split("=")[1]));
        return ramModule;
    }
}
