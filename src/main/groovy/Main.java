import be.ko.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ploeg> ploegen = new PloegParser().parse(new File("src/main/resources/ploegen.csv"));

        List<Vraag> ronde1Vragen = new VragenParser().parse(new File("src/main/resources/rondes/" + "ronde1" + ".csv"));
        List<PloegAntwoorden> ronde1Antwoorden = new AntwoordenParser(ploegen).parse("ronde1" + ".txt");

        new RondePrinter("RONDE 1 : ALFABET", ronde1Vragen, ronde1Antwoorden).print(System.out);

        RondePrinter ronde2 = new RondePrinter("RONDE 2 : WHAT'S IN A GIRLS NAME", new VragenParser().parse(new File("src/main/resources/rondes/" + "ronde2" + ".csv")), new AntwoordenParser(ploegen).parse("ronde2" + ".txt"));
        ronde2.print(System.out);

        RondePrinter ronde3 = new RondePrinter("RONDE 3 : SOMETIMES GOOD GUYS DON’T WEAR WHITE", new VragenParser().parse(new File("src/main/resources/rondes/" + "ronde3" + ".csv")), new AntwoordenParser(ploegen).parse("ronde3" + ".txt"));
        ronde3.print(System.out);

        RondePrinter ronde4 = new RondePrinter("RONDE 4 : WHEREVER I LAY MY HAT...", new VragenParser().parse(new File("src/main/resources/rondes/" + "ronde4" + ".csv")), new AntwoordenParser(ploegen).parse("ronde4" + ".txt"));
        ronde4.print(System.out);

        RondePrinter ronde5 = new RondePrinter("RONDE 5: I CLOSE MY EYES AND COUNT BACK FROM 12", new VragenParser().parse(new File("src/main/resources/rondes/" + "ronde5" + ".csv")), new AntwoordenParser(ploegen).parse("ronde5" + ".txt"));
        ronde5.print(System.out);

        RondePrinter ronde7 = new RondePrinter("RONDE 7 : THE WIND CRIES MARY", new VragenParser().parse(new File("src/main/resources/rondes/ronde7.csv")), new AntwoordenParser(ploegen).parse("ronde7" + ".txt"));
        ronde7.print(System.out);

        RondePrinter ronde8 = new RondePrinter("RONDE 8: OVER 6 DAGEN", new VragenParser().parse(new File("src/main/resources/rondes/ronde8.csv")), new AntwoordenParser(ploegen).parse("ronde8" + ".txt"));
        ronde8.print(System.out);

        RondePrinter ronde9 = new RondePrinter("RONDE 9 : AND I WANT, AND I NEED, AND I LOVE...", new VragenParser().parse(new File("src/main/resources/rondes/ronde9.csv")), new AntwoordenParser(ploegen).parse("ronde9" + ".txt"));
        ronde9.print(System.out);

        RondePrinter ronde10 = new RondePrinter("RONDE 10: FAVORIETENRONDE", new VragenParser().parse(new File("src/main/resources/rondes/ronde10.csv")), new AntwoordenParser(ploegen).parse("ronde10" + ".txt"));
        ronde10.print(System.out);




//        scoreAnalytics.printScores(System.out);

        DecenniumScorebord decenniumScorebord = new DecenniumScorebord(ploegen);

    }


}
