import be.ko.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ploeg> ploegen = new PloegParser().parse(new File("src/main/resources/ploegen.csv"));

        new RondePrinter("RONDE 1 : ALFABET", "ronde1", ploegen).print(System.out);



        RondePrinter ronde2 = new RondePrinter("RONDE 2 : WHAT'S IN A GIRLS NAME", "ronde2", ploegen);
        ronde2.setUitleg("Titels met een vrouwennaam, alfabetisch geordend");
        ronde2.print(System.out);

        RondePrinter ronde3 = new RondePrinter("RONDE 3 : SOMETIMES GOOD GUYS DON’T WEAR WHITE", "ronde3", ploegen);
        ronde3.setUitleg("Titels met een mannennaam, alfabetisch geordend in omgekeerde volgorde");
        ronde3.print(System.out);

//        scoreAnalytics.printScores(System.out);

    }


}
