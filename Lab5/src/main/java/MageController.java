
import java.util.Optional;

public class MageController {
    private final MageRepository repository;

    public MageController(MageRepository repository) {
        this.repository = repository;
    }

    public String save(String name, String level) {
        try {
            repository.save(new Mage(name, Integer.parseInt(level)));
            return "done";
        }
        catch (IllegalArgumentException exception) {
            return "bad request";
        }
    }

    public String find(String name) {
        Optional<Mage> mage = repository.find(name);
        if(mage.isPresent()) {
            return mage.get().toString();
        }
        else {
            return "not found";
        }
    }

    public String delete(String name) {
        try {
            repository.delete(name);
            return "done";
        }
        catch (IllegalArgumentException exception) {
            return "not found";
        }
    }
}
