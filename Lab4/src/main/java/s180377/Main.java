package s180377;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4PU");

        Database<Mage> magesDB = new Database<>(emf, Mage.class);
        Database<Tower> towersDB = new Database<>(emf, Tower.class);
        UI program = new UI(magesDB,towersDB);
        program.runProgramm();

    }
}
