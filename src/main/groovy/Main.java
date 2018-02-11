import be.ko.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ploeg> ploegen = new PloegParser().parse(new File("src/main/resources/ploegen.csv"));

        Ronde ronde1 = new Ronde("RONDE 1 : ALFABET", getVragen("ronde1"), getAntwoorden(ploegen, "ronde1"));
        ronde1.print(System.out);

        Ronde ronde2 = new Ronde("RONDE 2 : WHAT'S IN A GIRLS NAME", getVragen("ronde2"), getAntwoorden(ploegen, "ronde2"));
        ronde2.print(System.out);

        Ronde ronde3 = new Ronde("RONDE 3 : SOMETIMES GOOD GUYS DON’T WEAR WHITE", getVragen("ronde3"), getAntwoorden(ploegen, "ronde3"));
        ronde3.print(System.out);

        Ronde ronde4 = new Ronde("RONDE 4 : WHEREVER I LAY MY HAT...", getVragen("ronde4"), getAntwoorden(ploegen, "ronde4"));
        ronde4.print(System.out);

        Ronde ronde5 = new Ronde("RONDE 5: I CLOSE MY EYES AND COUNT BACK FROM 12", getVragen("ronde5"), getAntwoorden(ploegen, "ronde5"));
        ronde5.print(System.out);

        new Theorieronde().print(System.out);

        Ronde ronde6 = new Ronde("RONDE 6 : SHAKE YOUR BODY DOWN TO THE GROUND", getVragen("ronde6"), getAntwoorden(ploegen, "ronde6"));
        ronde6.print(System.out);

        Ronde ronde7 = new Ronde("RONDE 7 : THE WIND CRIES MARY", getVragen("ronde7"), getAntwoorden(ploegen, "ronde7"));
        ronde7.print(System.out);

        Ronde ronde8 = new Ronde("RONDE 8: OVER 6 DAGEN", getVragen("ronde8"), getAntwoorden(ploegen, "ronde8"));
        ronde8.print(System.out);

        Ronde ronde9 = new Ronde("RONDE 9 : AND I WANT, AND I NEED, AND I LOVE...", getVragen("ronde6"), getAntwoorden(ploegen, "ronde9"));
        ronde9.print(System.out);

        Ronde ronde10 = new Ronde("RONDE 10: FAVORIETENRONDE", getVragen("ronde10"), getAntwoorden(ploegen, "ronde10"));
        ronde10.print(System.out);

//        scoreAnalytics.printScores(System.out);

//        DecenniumScorebord decenniumScorebord = new DecenniumScorebord(ploegen);
//        decenniumScorebord += decenniumScorebord + ronde1.getDecenniaAnalytics():
    }

    private static List<PloegAntwoorden> getAntwoorden(List<Ploeg> ploegen, String ronde1) {
        return new AntwoordenParser(ploegen).parse(ronde1 + ".txt");
    }

    private static List<Vraag> getVragen(String ronde1) {
        return new VragenParser().parse(new File("src/main/resources/rondes/" + ronde1 + ".csv"));
    }


}
