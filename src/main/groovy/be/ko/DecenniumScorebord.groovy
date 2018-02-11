package be.ko

class DecenniumScorebord {
    private List<Ploeg> ploegen
    private Map<Ploeg, Map<Decade, Integer>> scores = new HashMap<>()

    DecenniumScorebord(List<Ploeg> ploegen) {
        this.ploegen = ploegen
        ploegen.each {
            scores.put(it, createDecadeMap())
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
                newScoreboard.scores.get(ploeg).get(decade, newScore)
            }
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

    @Override
    String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append(";60's;70's;80's;90's;00's;10's\n")
        for (String ploeg : ploegen) {
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
}
