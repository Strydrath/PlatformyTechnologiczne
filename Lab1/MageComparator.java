import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage o1, Mage o2) {
        int wynik = o1.getLevel()-o2.getLevel();
        if(wynik==0){
            wynik = o1.getName().compareTo(o2.getName());
        }
        if(wynik==0){
            wynik = o1.getPower()-o2.getPower();
        }
        return wynik;
    }
}
