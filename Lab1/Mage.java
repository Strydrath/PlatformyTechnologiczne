import java.util.*;

public class Mage implements Comparable<Mage> {
    private String name;
    private int level;
    private int power;
    private Set<Mage> apprentices;
    public Mage(String n, int l, int k, String sort){
        name = n;
        level = l;
        power = k;
        if(sort.equals("brak"))
            apprentices = new HashSet<Mage>();
        else if (sort.equals("normal"))
            apprentices = new TreeSet<Mage>();
        else if (sort.equals("alter")){
            MageComparator mageCompare = new MageComparator();
            apprentices = new TreeSet<Mage>(mageCompare);
        }
    }
    public int getPower() {
        return power;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void addApprentice(Mage m){
        apprentices.add(m);
    }


    public int countAss(Map<Mage,Integer> stat){
        int sum=0;
        for (Mage obj : apprentices) {
            sum+=obj.countAss(stat)+1;
        }
        stat.put(this,sum);
        return sum;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level &&
                power == mage.power &&
                name.equals(mage.name) &&
                Objects.equals(apprentices, mage.apprentices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power, apprentices);
    }

    @Override
    public int compareTo(Mage o) {
        int wynik = name.compareTo(o.name);
        if(wynik==0){
            wynik = level-o.level;
        }
        if(wynik==0){
            wynik = power-o.power;
        }

        return wynik;
    }

    public void print(int depth){
        String dive="";
        for(int i=0; i<depth;i++){
            dive+="-";
        }
        System.out.println(dive + toString());
        for (Mage obj : apprentices) {
            obj.print(depth + 1);
        }
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                "}";
    }
}
