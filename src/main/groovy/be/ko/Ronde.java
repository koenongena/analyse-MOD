package be.ko;

import java.io.PrintStream;
import java.util.List;

public class Ronde {
    private final List<Vraag> vragen;
    private final List<PloegAntwoorden> antwoorden;
    private String titel;
    private String uitleg = "";

    public Ronde(String titel, List<Vraag> vragen, List<PloegAntwoorden> antwoorden) {

        this.titel = titel;

        this.vragen = vragen;
        this.antwoorden = antwoorden;
    }

    public DecenniaAnalystics getDecenniaAnalytics() {
        return new DecenniaAnalystics(vragen, antwoorden);
    }

    public void setUitleg(String uitleg) {
        this.uitleg = uitleg;
    }

    public void print(PrintStream out) {
        out.println("[b]" + titel + "[/b]");
        if (!uitleg.isEmpty()) {
            out.println(uitleg);
        }
        out.println();



        RondeAnalytics rondeAnalytics = new RondeAnalytics(vragen, antwoorden);
        rondeAnalytics.printAntwoordenAantallen(out);

        out.println();
        rondeAnalytics.printSolos(out);
        out.println();

        ScoreAnalytics scoreAnalytics = new ScoreAnalytics(vragen, antwoorden);
        scoreAnalytics.printTopscore(out);
        scoreAnalytics.printGemiddeldeScore(out);

        out.println();
        out.println("**************************");
        out.println();
    }

    public DecenniumScorebord getDecenniaScorebord() {
        return new DecenniaAnalystics(vragen, antwoorden).getScorebord();
    }
}
