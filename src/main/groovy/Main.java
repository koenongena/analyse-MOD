import be.ko.*;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ploeg> ploegen = new PloegParser().parse(new File("src/main/resources/ploegen.csv"));

        List<Vraag> vragen = new VragenParser().parse(new File("src/main/resources/rondes/ronde1.csv"));
        List<PloegAntwoorden> antwoorden = new AntwoordenParser(ploegen).parse("ronde1.txt");

        RondeAnalytics rondeAnalytics = new RondeAnalytics(vragen, antwoorden);
        rondeAnalytics.printAntwoordenAantallen(System.out);

        System.out.println();
        rondeAnalytics.printSolos(System.out);
        System.out.println();

        ScoreAnalytics scoreAnalytics = new ScoreAnalytics(vragen, antwoorden);
        scoreAnalytics.printTopscore(System.out);
        scoreAnalytics.printGemiddeldeScore(System.out);
//        scoreAnalytics.printScores(System.out);

    }
}
