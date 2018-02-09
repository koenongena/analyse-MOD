import be.ko.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ploeg> ploegen = new PloegParser().parse(new File("src/main/resources/ploegen.csv"));

        new RondePrinter("RONDE 1 : ALFABET", "ronde1", ploegen).print(System.out);

//        scoreAnalytics.printScores(System.out);

    }


}
