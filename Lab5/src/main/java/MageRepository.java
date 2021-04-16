
import java.util.Optional;
import java.util.TreeMap;

public class MageRepository {
    private final TreeMap<String,Mage> mages;

    public MageRepository() {
        mages = new TreeMap<>();
    }

    public void save(Mage mage) throws IllegalArgumentException{
        if(mages.containsKey(mage.getName())) {
            throw new IllegalArgumentException();
        }
        mages.put(mage.getName(), mage);
    }

    public Optional<Mage> find(String name) {
        Mage m = mages.get(name);
        if(m != null)
            return Optional.of(mages.get(name));
        else
            return Optional.empty();
    }

    public void delete(String name) throws IllegalArgumentException {
        if(!mages.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        mages.remove(name);
    }
}
