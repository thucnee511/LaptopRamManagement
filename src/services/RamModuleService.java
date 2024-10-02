package services;

import java.util.List;

import business.daos.IDao;
import business.daos.RamModuleDao;
import business.entities.RamModule;

public class RamModuleService implements IService<RamModule> {

    private static RamModuleService instance = null;
    private IDao<RamModule> ramModuleDao;

    private RamModuleService() throws Exception {
        ramModuleDao = RamModuleDao.getInstance("RamModules.dat");
    }

    public static RamModuleService getInstance() throws Exception {
        if (instance == null) {
            instance = new RamModuleService();
        }
        return instance;
    }

    @Override
    public List<RamModule> getAll() throws Exception {
        return ramModuleDao.get();
    }

    @Override
    public RamModule get(String code) throws Exception {
        List<RamModule> list = ramModuleDao.get();
        return list.stream().filter(item -> item.getCode().equals(code)).findFirst().orElse(null);
    }

    @Override
    public RamModule add(RamModule item) throws Exception {
        return ramModuleDao.post(item);
    }

    @Override
    public RamModule update(RamModule item) throws Exception {
        return ramModuleDao.update(item);
    }

    @Override
    public RamModule delete(RamModule item) throws Exception {
        return ramModuleDao.delete(item);
    }

    @Override
    public boolean save() throws Exception {
        return ramModuleDao.save();
    }
}
