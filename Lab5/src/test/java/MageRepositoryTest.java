import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class MageRepositoryTest {

    @Test
    public void find_correct() {
        MageRepository repository = new MageRepository();
        Mage mage = new Mage("test", 0);
        repository.save(mage);

        Optional<Mage> wynik = repository.find("test");
        Assertions.assertNotEquals(wynik,Optional.empty());
    }

    @Test
    public void find_notFound() {
        MageRepository repository = new MageRepository();

        Optional<Mage> wynik = repository.find("no_name");

        Assertions.assertTrue(wynik.isEmpty());
    }


    @Test
    public void delete_Correct() {
        Assertions.assertDoesNotThrow(() -> {
            MageRepository repository = new MageRepository();
            repository.save(new Mage("test", 0));
            repository.delete("test");
        });
    }

    @Test
    public void delete_notFound() {
        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            MageRepository repository = new MageRepository();
            repository.delete("no_name");
        });
    }

    @Test
    public void save_correct() {
        MageRepository repository = new MageRepository();
        Assertions.assertDoesNotThrow(()->repository.save(new Mage("test", 0)));
    }

    @Test
    public void save_dupe() {

        Assertions.assertThrows(IllegalArgumentException.class,() -> {
            MageRepository repo = new MageRepository();
            repo.save(new Mage("test", 0));
            repo.save(new Mage("test", 26));});
    }

}
