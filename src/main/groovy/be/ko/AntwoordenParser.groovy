package be.ko

class AntwoordenParser {
    private List<Ploeg> ploegen

    AntwoordenParser(List<Ploeg> ploegen) {
        this.ploegen = ploegen
    }

    List<PloegAntwoorden> parse(String fileName) {
        List<PloegAntwoorden> ploegAntwoorden = new ArrayList<>()
        for (Ploeg ploeg : ploegen) {
            ploegAntwoorden.add(parseFile(ploeg, fileName))
        }
        return ploegAntwoorden
    }

    private static PloegAntwoorden parseFile(Ploeg ploeg, String fileName) {
        File file = new File(ploeg.getDirectory(), fileName)
        def antwoorden = new PloegAntwoorden(ploeg)
        file.eachLine { line, row ->
            def artistAnswer = line.charAt(0)
            def titleAnswer = line.charAt(1)
            antwoorden.addAntwoord(new Answer(row, artistAnswer == ('1' as char), titleAnswer == ('1' as char)))
        }
        return antwoorden
    }
}
