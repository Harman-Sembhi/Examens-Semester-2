package ucll.examen.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ucll.examen.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    public Module findById(long id);
}
