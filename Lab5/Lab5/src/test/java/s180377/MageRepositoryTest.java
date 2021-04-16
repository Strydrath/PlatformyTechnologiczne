package s180377;

import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MageRepositoryTest {
    @Test
    public void save_uniqueKey_noException() {
        assertThatCode(() -> {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("TEST_NAME", 0));
        }).doesNotThrowAnyException();
    }

    @Test
    public void save_duplicateKey_IllegalArgumentException() {
        assertThatCode(() -> {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("TEST_NAME", 0));
            repo.save(new Mage("TEST_NAME", 9));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void find_objectPresent_optionalWithFoundMage() {
        MageRepository repo = new MageRepository();
        Mage mage = new Mage("TEST_NAME", 0);
        repo.save(mage);

        Optional<Mage> found = repo.find("TEST_NAME");

        assertThat(found).hasValue(mage);
    }

    @Test
    public void find_noObjectsPresent_emptyOptional() {
        MageRepository repo = new MageRepository();

        Optional<Mage> found = repo.find("ABSENT_NAME");

        assertThat(found).isEmpty();
    }

    @Test
    public void find_objectAbsent_emptyOptional() {
        MageRepository repo = new MageRepository();
        repo.save(new Mage("TEST_NAME", 0));

        Optional<Mage> found = repo.find("ABSENT_NAME");

        assertThat(found).isEmpty();
    }

    @Test
    public void delete_objectPresent_noException() {
        assertThatCode(() -> {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("TEST_NAME", 0));
            repo.delete("TEST_NAME");
        }).doesNotThrowAnyException();
    }

    @Test
    public void delete_noObjectsPresent_IllegalArgumentException() {
        assertThatCode(() -> {
            MageRepository repo = new MageRepository();
            repo.delete("ABSENT_NAME");
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void delete_objectAbsent_IllegalArgumentException() {
        assertThatCode(() -> {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("TEST_NAME", 0));
            repo.delete("ABSENT_NAME");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
