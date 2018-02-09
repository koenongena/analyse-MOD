package be.ko;

import java.io.File;
import java.io.PrintStream;
import java.util.List;

public class RondePrinter {
    private String titel;
    private String uitleg = "";
    private String fileBaseName;
    private List<Ploeg> ploegen;

    public RondePrinter(String titel, String fileBaseName, List<Ploeg> ploegen) {

        this.titel = titel;
        this.fileBaseName = fileBaseName;
        this.ploegen = ploegen;
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

        List<Vraag> vragen = new VragenParser().parse(new File("src/main/resources/rondes/" + fileBaseName + ".csv"));
        List<PloegAntwoorden> antwoorden = new AntwoordenParser(ploegen).parse(fileBaseName + ".txt");

        RondeAnalytics rondeAnalytics = new RondeAnalytics(vragen, antwoorden);
        rondeAnalytics.printAntwoordenAantallen(out);

        out.println();
        rondeAnalytics.printSolos(out);
        out.println();

        ScoreAnalytics scoreAnalytics = new ScoreAnalytics(vragen, antwoorden);
        scoreAnalytics.printTopscore(out);
        scoreAnalytics.printGemiddeldeScore(out);
    }
}
