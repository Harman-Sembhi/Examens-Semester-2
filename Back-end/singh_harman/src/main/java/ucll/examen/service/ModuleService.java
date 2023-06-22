package ucll.examen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import ucll.examen.exception.ServiceException;
import ucll.examen.model.Module;
import ucll.examen.repo.ModuleRepository;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    public List<Module> getAll() {
        return moduleRepository.findAll();
    }

    public Module addModule(Module module) {
        return moduleRepository.save(module);
    }

    public Module removeModuleById(long moduleId) throws ServiceException {
        Module module = moduleRepository.findById(moduleId);
        if (module == null)
            throw new ServiceException("id", "Module not found with this id");
        moduleRepository.delete(module);
        return module;
    }

}
