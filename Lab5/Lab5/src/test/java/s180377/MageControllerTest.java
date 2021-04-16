package s180377;

import org.junit.jupiter.api.Test;
import org.mockito.Matchers;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class MageControllerTest {
    @Test
    public void save_uniqueKey_returnDone() {
        MageRepository mockRepo = mock(MageRepository.class);
        doNothing().when(mockRepo).save(Matchers.any());
        MageController controller = new MageController(mockRepo);

        String response = controller.save("TEST_NAME", "0");

        assertThat(response).isEqualTo("done");
    }

    @Test
    public void save_duplicateKey_returnBadRequest() {
        MageRepository mockRepo = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mockRepo).save(Matchers.any());
        MageController controller = new MageController(mockRepo);

        String response = controller.save("TEST_NAME", "0");

        assertThat(response).isEqualTo("bad request");
    }

    @Test
    public void find_objectFound_returnMageToString() {
        Mage mage = new Mage("TEST_NAME", 0);
        MageRepository mockRepo = mock(MageRepository.class);
        when(mockRepo.find("TEST_NAME")).thenReturn(Optional.of(mage));
        MageController controller = new MageController(mockRepo);

        String response = controller.find("TEST_NAME");

        assertThat(response).isEqualTo("Mage{name='TEST_NAME', level=0}");
    }

    @Test
    public void find_objectNotFound_returnNotFound() {
        MageRepository mockRepo = mock(MageRepository.class);
        when(mockRepo.find("ABSENT_NAME")).thenReturn(Optional.empty());
        MageController controller = new MageController(mockRepo);

        String response = controller.find("ABSENT_NAME");

        assertThat(response).isEqualTo("not found");
    }

    @Test
    public void delete_objectDeleted_returnDone() {
        MageRepository mockRepo = mock(MageRepository.class);
        doNothing().when(mockRepo).save(Matchers.any());
        MageController controller = new MageController(mockRepo);

        String response = controller.delete("TEST_NAME");

        assertThat(response).isEqualTo("done");
    }

    @Test
    public void delete_objectAbsent_returnNotFound() {
        MageRepository mockRepo = mock(MageRepository.class);
        doThrow(IllegalArgumentException.class).when(mockRepo).delete(Matchers.any());
        MageController controller = new MageController(mockRepo);

        String response = controller.delete("TEST_NAME");

        assertThat(response).isEqualTo("not found");
    }
}
