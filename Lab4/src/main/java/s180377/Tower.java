package s180377;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tower {
    @Id
    private String name;
    private int height;
    @OneToMany (mappedBy="tower",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Mage> mages;

    public Tower(){
        name = "";
        height=0;
    }

    public Tower(String name, int height){
        this.name=name;
        this.height=height;
        //mages = new ArrayList<Mage>();
    }

    public void addMage(Mage mage) {
        mages.add(mage);
        mage.setTower(this);
    }

    public void removeMage(Mage mage) {
        mages.remove(mage);
        mage.setTower(null);
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public List<Mage> getMages() {
        return mages;
    }
}