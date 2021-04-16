package s180377;

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
        return Optional.ofNullable(mages.get(name));
    }

    public void delete(String name) throws IllegalArgumentException {
        if(!mages.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        mages.remove(name);
    }
}
