package be.ko

class DecenniaAnalystics {

    private List<Vraag> vragen
    private List<PloegAntwoorden> antwoorden
    private List<Ploeg> ploegen
    private DecenniumScorebord decenniaScoreboard

    DecenniaAnalystics(List<Vraag> vragen, List<PloegAntwoorden> antwoorden) {
        this.antwoorden = antwoorden
        this.vragen = vragen
        this.ploegen = antwoorden*.ploeg
        initDecenniaScoreboard()
    }

    private void initDecenniaScoreboard() {
        this.decenniaScoreboard = new DecenniumScorebord(antwoorden.collect { it.ploeg })

        for (Vraag vraag : vragen) {
            for (PloegAntwoorden ploegAntwoord : antwoorden) {
                def a = ploegAntwoord.getAnswer(vraag.id)
                if (a.titelCorrect) {
                    decenniaScoreboard.addPunt(vraag.decade, ploegAntwoord.ploeg)
                }
                if (a.artiestCorrect) {
                    decenniaScoreboard.addPunt(vraag.decade, ploegAntwoord.ploeg)
                }
            }
        }
        this.decenniaScoreboard.setMaxScores(getHighscores())
    }

    private Map<Ploeg, Integer> getSixtiesScores() {
        Map<Ploeg, Integer> scores = new HashMap<>()
        for (Ploeg ploeg : ploegen) {
            def score = decenniaScoreboard.getScore(Decade.SIXTIES, ploeg)
            scores.put(ploeg, score)
        }
       return scores
    }


    private Integer getHighscore(Decade decade) {
        int sum = 0
        for (Vraag vraag : vragen) {
            if (vraag.decade == decade) {
                sum += 2
            }
        }
        return sum
    }

    private Map<Decade, Integer> getHighscores() {
        Map<Decade, Integer> highscores = new HashMap<>()
        for (Decade decade : Decade.values()) {
            highscores.put(decade, getHighscore(decade))
        }
        return highscores
    }

    DecenniumScorebord getScorebord ( ) {
        return this.decenniaScoreboard
    }
}
