import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<Mage,Integer> statystyka;
        String sort = args[0];
        if(sort.equals("brak")){
            statystyka=new HashMap();
        }
        else if (sort.equals("normal"))
            statystyka = new TreeMap<>();
        else{
            MageComparator mCompare = new MageComparator();
            statystyka = new TreeMap<>(mCompare);
        }
        Mage root = new Mage("TheOldOne",210,1569,sort);
        Mage a = new Mage("Marek",7,35,sort);
        root.addApprentice(a);
        Mage b = new Mage("Kamil",4,12,sort);
        a.addApprentice(b);
        Mage c = new Mage("Andrzej",3,7,sort);
        a.addApprentice(c);
        Mage d = new Mage("Geralt",76,850,sort);
        root.addApprentice(d);
        Mage e = new Mage("Stefan",25,58,sort);
        d.addApprentice(e);
        Mage f = new Mage("Andrzej",37,345,sort);
        d.addApprentice(f);
        Mage g = new Mage("Mustafa",47,753,sort);
        d.addApprentice(g);
        Mage h = new Mage("Biały",23,32,sort);
        g.addApprentice(h);
        Mage i = new Mage("Szary",13,23,sort);
        g.addApprentice(i);
        Mage j = new Mage("Merlin",2,13,sort);
        i.addApprentice(j);



        root.print(0);
        root.countAss(statystyka);
        System.out.println("\n\nSTATYSTYKA\n");
        for(Mage m : statystyka.keySet()){
            System.out.println(m+" - "+ statystyka.get(m));
        }
    }
}
