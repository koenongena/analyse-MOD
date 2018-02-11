package be.ko

class DecenniumScorebord {
    private List<Ploeg> ploegen
    private Map<Ploeg, Map<Decade, Integer>> scores = new HashMap<>()
    private Map<Decade, Integer> maxScores = new HashMap<>()

    DecenniumScorebord(List<Ploeg> ploegen) {
        this.ploegen = ploegen
        ploegen.each {
            scores.put(it, createDecadeMap())
        }
        Decade.values().each {
            maxScores.put(it, 0)
        }
    }

    Integer getScore(Decade decade, Ploeg ploeg) {
        return scores.get(ploeg).get(decade)
    }

    DecenniumScorebord plus(DecenniumScorebord decenniumScorebord) {
        def newScoreboard = new DecenniumScorebord(ploegen)
        for (Ploeg ploeg : ploegen) {
            for (Decade decade : Decade.values()) {
                int newScore = this.getScore(decade, ploeg) + decenniumScorebord.getScore(decade, ploeg)
                newScoreboard.scores.get(ploeg).put(decade, newScore)
            }
        }

        for (Decade decade : Decade.values()) {
            newScoreboard.maxScores.put(decade, this.maxScores.get(decade) + decenniumScorebord.maxScores.get(decade))
        }

        return newScoreboard
    }

    static Map<Decade, Integer> createDecadeMap() {
        def map = new HashMap<Decade, Integer>()
        Decade.values().each { map.put(it, 0) }
        map
    }

    void addPunt(Decade decade, Ploeg ploeg) {
        int currentScore = scores.get(ploeg).get(decade)
        scores.get(ploeg).put(decade, currentScore + 1)
    }

    void setMaxScores(Map<Decade, Integer> maxScores) {
        this.maxScores = maxScores
    }

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append(";60's (op ${maxScores.get(Decade.SIXTIES)});")
        stringBuilder.append("70's (op ${maxScores.get(Decade.SEVENTIES)});")
        stringBuilder.append("80's (op ${maxScores.get(Decade.EIGHTIES)});")
        stringBuilder.append("90's (op ${maxScores.get(Decade.NINETIES)});")
        stringBuilder.append("00's (op ${maxScores.get(Decade.NILLIES)});")
        stringBuilder.append("10's (op ${maxScores.get(Decade.TENS)})\n")
        for (Ploeg ploeg : ploegen) {
            def ploegScores = scores.get(ploeg)
            stringBuilder.append "${ploeg}"
            stringBuilder.append ";${ploegScores.get(Decade.SIXTIES)}"
            stringBuilder.append ";${ploegScores.get(Decade.SEVENTIES)}"
            stringBuilder.append ";${ploegScores.get(Decade.EIGHTIES)}"
            stringBuilder.append ";${ploegScores.get(Decade.NINETIES)}"
            stringBuilder.append ";${ploegScores.get(Decade.NILLIES)}"
            stringBuilder.append ";${ploegScores.get(Decade.TENS)}"
            stringBuilder.append "\n"
        }
        return stringBuilder.toString()
    }

    void printSixtiesKlassement(PrintStream out) {

        out.println("[b]Klassement 60s (op ${maxScores.get(Decade.SIXTIES)})[/b]")
        out.println("")

        def decade = Decade.SIXTIES
        printDecadeKlassement(decade, out)
    }
    void printSeventiesKlassement(PrintStream out) {

        out.println("[b]Klassement 70s (op ${maxScores.get(Decade.SEVENTIES)})[/b]")
        out.println("")

        printDecadeKlassement(Decade.SEVENTIES, out)
    }
    void printEightiesKlassement(PrintStream out) {

        out.println("[b]Klassement 80s (op ${maxScores.get(Decade.EIGHTIES)})[/b]")
        out.println("")

        printDecadeKlassement(Decade.EIGHTIES, out)
    }
    void printNinetiesKlassement(PrintStream out) {

        out.println("[b]Klassement 90s (op ${maxScores.get(Decade.NINETIES)})[/b]")
        out.println("")

        printDecadeKlassement(Decade.NINETIES, out)
    }
    void printNiliesKlassement(PrintStream out) {

        out.println("[b]Klassement 00s (op ${maxScores.get(Decade.NILLIES)})[/b]")
        out.println("")

        printDecadeKlassement(Decade.NILLIES, out)
    }
    void printTensKlassement(PrintStream out) {

        out.println("[b]Klassement 10s (op ${maxScores.get(Decade.TENS)})[/b]")
        out.println("")

        printDecadeKlassement(Decade.TENS, out)
    }

    private void printDecadeKlassement(Decade decade, out) {
        Map<Ploeg, Integer> scores = getScores(decade)
        scores.sort { a, b -> b.value <=> a.value }.each { ploeg, score ->
            out.println("${ploeg} - ${score}")
        }
        out.println ""
    }

    private Map<Ploeg, Integer> getScores(Decade decade) {
        Map<Ploeg, Integer> scores = new HashMap<>()
        for (Ploeg ploeg1 : ploegen) {
            scores.put(ploeg1, getScore(decade, ploeg1))
        }
        return scores
    }
}
