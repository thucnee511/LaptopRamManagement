package business.daos;

import business.entities.RamModule;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import utils.IFileAccess;

public class RamModuleDao implements IFileAccess<RamModule>, IDao<RamModule> {

    private final String filepath;
    private final List<RamModule> list = new ArrayList<>();
    private static RamModuleDao instance = null;

    private RamModuleDao(String filepath) throws Exception {
        this.filepath = new File("").getAbsolutePath() + "\\src\\business\\entities\\" + filepath;
        load();
    }

    public static RamModuleDao getInstance(String filepath) throws Exception {
        if (instance == null) {
            instance = new RamModuleDao(filepath);
        }
        return instance;
    }

    @Override
    public boolean load() throws Exception {
        File file = new File(filepath);
        list.clear();
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;
            while ((line = input.readLine()) != null) {
                RamModule entity = RamModule.fromString(line);
                list.add(entity);
            }
            input.close();
        } catch (IOException e) {
        }
        return true;
    }

    @Override
    public boolean save() throws Exception {
        File file = new File(filepath);
        try {
            file.createNewFile();
            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            for (RamModule line : list) {
                output.write(line.toString());
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public List<RamModule> get() throws Exception {
        return list;
    }

    @Override
    public RamModule post(RamModule item) throws Exception {
        RamModule _item = list.stream()
                .filter(r -> r.getCode().equalsIgnoreCase(item.getCode()))
                .collect(Collectors.toList()).get(0);
        if (_item == null) {
            list.add(item);
        } else {
            throw new Exception("Item already exists.");
        }
        return _item;
    }

    @Override
    public RamModule update(RamModule item) throws Exception {
        RamModule _item = list.stream()
                .filter(r -> r.getCode().equalsIgnoreCase(item.getCode()))
                .collect(Collectors.toList()).get(0);
        if (_item != null) {
            _item.setBrand(item.getBrand());
            _item.setBus(item.getBus());
            _item.setProduction_month_year(item.getProduction_month_year());
            _item.setQuantity(item.getQuantity());
            _item.setType(item.getType());
            _item.setActive(item.isActive());
        } else {
            throw new Exception("Item not found.");
        }
        return _item;
    }

    @Override
    public RamModule delete(RamModule item) throws Exception {
        RamModule _item = list.stream()
                .filter(r -> r.getCode().equalsIgnoreCase(item.getCode()))
                .collect(Collectors.toList()).get(0);
        if (_item != null) {
            _item.setActive(false);
        } else {
            throw new Exception("Item not found.");
        }
        return _item;
    }
}
