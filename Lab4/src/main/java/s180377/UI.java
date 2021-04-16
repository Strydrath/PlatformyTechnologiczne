package s180377;

import java.util.List;
import java.util.Scanner;

public class UI {
    private Database<Mage> magesDB;
    private Database<Tower> towersDB;

    public UI(Database<Mage> magesDB,Database<Tower> towersDB){
        this.magesDB = magesDB;
        this.towersDB = towersDB;
    }

    public void menu(){
        System.out.println("1 - dodaj wpis");
        System.out.println("2 - usuń wpis");
        System.out.println("3 - wyświetl dane");
        System.out.println("4 - wyświetl wieże większe niż");
        System.out.println("9 - wyjdź");
    }
    public void printAllMages(List<Mage> all){
        int i = 1;
        for (Mage mage:all) {
            System.out.println(i+" - Mag "+mage.getName()+" o lvl "+mage.getLevel()+" wieża: "+mage.getTower().getName());
            //+"wieża: "+mage.getTower().getName()
            i++;
        }
    }
    public void printAllTowers(List<Tower> all){
        int i = 1;
        for (Tower tower:all) {
            System.out.println(i+" - Wieża "+tower.getName()+" o wysokości "+tower.getHeight());
            i++;
        }
    }
    public void runProgramm(){
        int polecenie=0;
        Scanner sc = new Scanner(System.in);
        int opcja=0;
        while(polecenie!=9){
            menu();
            //if(sc.hasNextInt())
            polecenie = sc.nextInt();

            switch(polecenie){
                case 1:
                    System.out.println("1 - Mag");
                    System.out.println("2 - Wieża");
                    opcja = sc.nextInt();
                    sc.nextLine();
                    if(opcja == 1){
                        System.out.println("Podaj imie:");
                        String name = sc.nextLine();
                        System.out.println("Podaj lvl:");
                        int lvl = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Wybierz wieżę:");
                        List<Tower> towers= towersDB.getAll();
                        printAllTowers(towers);
                        System.out.println("-----");
                        int tow = sc.nextInt();
                        sc.nextLine();
                        int a=0;
                        Mage nowy = new Mage(name, lvl,towers.get(tow-1));
                        magesDB.add(nowy);
                        System.out.println("-----");
                    }
                    else if (opcja == 2){
                        System.out.println("Podaj nazwę:");
                        String name = sc.nextLine();
                        System.out.println("Podaj wysokość:");
                        int height = sc.nextInt();
                        sc.nextLine();
                        towersDB.add(new Tower(name,height));
                        System.out.println("-----");
                    }

                    break;
                case 2:
                    System.out.println("1 - Mag");
                    System.out.println("2 - Wieża");
                    opcja = sc.nextInt();
                    sc.nextLine();
                    if(opcja==1){
                        System.out.println("Wybierz maga do usunięcia:");
                        List<Mage> mages = magesDB.getAll();
                        printAllMages(mages);
                        int mag = sc.nextInt();
                        sc.nextLine();
                        Mage ten = mages.get(mag-1);
                        magesDB.delete(ten);
                        System.out.println("-----");
                    }
                    else if(opcja==2){
                        System.out.println("Wybierz wieżę do usunięcia:");
                        List<Tower> towers= towersDB.getAll();
                        printAllTowers(towers);
                        int tow = sc.nextInt();
                        sc.nextLine();
                        Tower ta = towers.get(tow-1);

                        towersDB.delete(towers.get(tow-1));
                        System.out.println("-----");
                    }

                    break;
                case 3:
                    printAllMages(magesDB.getAll());
                    printAllTowers(towersDB.getAll());
                    System.out.println("-----");
                    break;
                case 4:
                    System.out.println("Podaj Wysokość:");
                    int high = sc.nextInt();
                    sc.nextLine();
                    List<Tower> towers=towersDB.getAllHigher(high);
                    printAllTowers(towers);
                    System.out.println("-----");

                    break;
            }

        }
    }
}
