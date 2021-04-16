import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void save_correct() {
        MageRepository repository = mock(MageRepository.class);
        doNothing().when(repository).save(Matchers.any());
        MageController controller = new MageController(repository);

        String response = controller.save("test", "0");
        Assertions.assertEquals(response,"done");
    }
    @Test
    public void save_dupeKey() {
        MageRepository repository = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(repository).save(Matchers.any());
        MageController controller = new MageController(repository);

        String response = controller.save("test", "0");

        Assertions.assertEquals(response,"bad request");
    }

    @Test
    public void find_correct() {
        Mage mage = new Mage("test", 0);
        MageRepository repository = mock(MageRepository.class);
        when(repository.find("test")).thenReturn(Optional.of(mage));
        MageController controller = new MageController(repository);

        String response = controller.find("test");

        Assertions.assertEquals(response,"Mage{name='test', level=0}");
    }

    @Test
    public void find_notFound() {
        MageRepository repository = mock(MageRepository.class);
        MageController controller = new MageController(repository);
        when(repository.find("test_brak")).thenReturn(Optional.empty());

        String response = controller.find("test_brak");

        Assertions.assertEquals(response,"not found");
    }

    @Test
    public void delete_correct() {
        MageRepository repository = mock(MageRepository.class);
        MageController controller = new MageController(repository);
        doNothing().when(repository).save(Matchers.any());

        String response = controller.delete("test");

        Assertions.assertEquals(response,"done");
    }

    @Test
    public void delete_notFound() {
        MageRepository repository = mock(MageRepository.class);
        MageController controller = new MageController(repository);
        doThrow(IllegalArgumentException.class).when(repository).delete(Matchers.any());

        String response = controller.delete("test");

        Assertions.assertEquals(response,"not found");
    }


}
