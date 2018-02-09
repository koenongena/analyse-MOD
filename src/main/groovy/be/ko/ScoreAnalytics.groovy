package be.ko

class ScoreAnalytics {
    private List<PloegAntwoorden> ploegen
    private List<Vraag> vragen
    private Map<Ploeg, Integer> scores

    ScoreAnalytics(List<Vraag> vragen, List<PloegAntwoorden> answers) {
        this.vragen = vragen
        this.ploegen = answers

        this.scores = determineScores()
    }

    void printScores(PrintStream out) {
        out.println("SCORES")
        out.println("")
        Map<Ploeg, Integer> scores = determineScores()
        scores.sort {a, b -> a.value <=> b.value}.each {ploeg, score ->
            out.println("${ploeg} : ${score}")
        }

    }

    void printTopscore(PrintStream out) {

        def maxScore = vragen.size() * 2
        def topscore = findTopScore()
        def topscorendePloegende = scores.findAll { it.value == topscore }.collect { it.key }

        out.print("Max score: ")
        out.print(topscore + "/" + maxScore)
        out.println " (${topscorendePloegende.join(", ")})"
    }

    private double toPercentage(int x, int y) {
        return (x * 100 / y).toDouble().round(2)
    }

    private int findTopScore() {
        int max = 0
        scores.each { k, v ->
            max = (v > max ? v : max)
        }
        return max
    }


    private Map<Ploeg, Integer> determineScores() {
        def scores = [:]
        for (PloegAntwoorden ploeg : ploegen) {
            int sum = 0
            for (Vraag vraag : vragen) {
                def a = ploeg.getAnswer(vraag.id)
                if (a.titelCorrect) sum++
                if (a.artiestCorrect) sum++
            }
            scores.put(ploeg.getPloeg(), sum)
        }
        return scores
    }

    void printGemiddeldeScore(PrintStream out) {
        int sum = 0
        scores.each { k, score ->
            sum += score
        }

        def gemiddleScore = (sum / ploegen.size()).toInteger()
        def maxScore = vragen.size() * 2

        out.print  "Gemiddelde score: "
        out.println "${gemiddleScore}/${maxScore}"
    }
}
