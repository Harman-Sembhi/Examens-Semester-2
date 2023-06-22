package ucll.examen.Module.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ucll.examen.repo.ModuleRepository;
import ucll.examen.service.ModuleService;
import ucll.examen.exception.ServiceException;
import ucll.examen.model.Module;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class ModuleServiceTest {

    @Mock
    ModuleRepository moduleRepository;

    @InjectMocks
    ModuleService moduleService;

    private Module moduleJava;
    private Module modulePython;

    @BeforeEach
    public void setUp() {
        moduleJava = new Module("Java");
        modulePython = new Module("Python");
    }

    @Test
    public void givenModuleJava_whenValidModuleWithValidIdIsDeleted_thenModuleJavaIsDeletedAndReturned() throws ServiceException{
        //given
        when(moduleRepository.save(moduleJava)).thenReturn(moduleJava);
        //when
        Module added = moduleService.addModule(moduleJava);
        Module deleted = moduleService.removeModuleById(added.getId());

        //then
        verify(moduleRepository).save(moduleJava);
        assertEquals(moduleJava.getId(), deleted.getId());
        assertEquals(moduleJava.getName(), deleted.getName());
        assertEquals(moduleJava.getCourse(), deleted.getCourse());
    }

    @Test
    public void givenModulePython_whenValidModuleIsDeletedWithInvalidGivenId_thenViolationMessageIsThrown(){
        //given
        when(moduleRepository.save(modulePython)).thenReturn(modulePython);
        long invalidId = 11;
        //when
        Module added = moduleService.addModule(modulePython);
        ServiceException ex = Assertions.assertThrows(ServiceException.class, () -> {
            moduleService.removeModuleById(invalidId);
        });

        //then 
        assertEquals("id", ex.getField());
        assertEquals("Module not found with this id", ex.getMessage());
    }
}
