package be.ko

class RondeAnalytics {

    private List<Vraag> vragen
    private List<PloegAntwoorden> antwoorden
    private DecenniumScorebord decenniaScoreboard

    RondeAnalytics(List<Vraag> vragen, List<PloegAntwoorden> antwoorden) {
        this.antwoorden = antwoorden
        this.vragen = vragen
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
    }



    void printAntwoordenAantallen(PrintStream out) {
        for (Vraag question : vragen) {
            int correctArtists = countArtists(antwoorden, question)
            int correctTitles = countTitles(antwoorden, question)

            out.println "${question.id}. ${question.artist} ${CountFormatter.formatCount(correctArtists)} - ${question.title} ${CountFormatter.formatCount(correctTitles)}"
        }
    }

    void printSolos(PrintStream out) {
        out.println("Minst beantwoord:")
        Map<String, Integer> output = new HashMap<>()
        List<String> qms = new ArrayList<>()

        for (Vraag vraag : vragen) {
            def ploegenMetCorrecteArtiest = filterPloegenMetCorrecteArtiest(vraag)
            if (ploegenMetCorrecteArtiest.size() == 0) {
                qms << vraag.artist
            } else if (ploegenMetCorrecteArtiest.size() <= 3) {
                String text = "${ploegenMetCorrecteArtiest.size()}x: ${vraag.artist} (${ploegenMetCorrecteArtiest.join(" - ")})"
                output.put(text, ploegenMetCorrecteArtiest.size())
            }

            def ploegenMetCorrecteTitel = filterPloegenMetCorrecteTitel(vraag)
            if (ploegenMetCorrecteTitel.size() == 0) {
              qms << vraag.title
            } else if (ploegenMetCorrecteTitel.size() <= 3) {
                String text = "${ploegenMetCorrecteTitel.size()}x: ${vraag.title} (${ploegenMetCorrecteTitel.join(" - ")})"
                output.put(text, ploegenMetCorrecteTitel.size())
            }
        }

        if (qms.size() > 0) {
            out.println("0x: " + qms.join(", "))

        }
        output.sort { a, b -> a.value <=> b.value }.each {
            out.println(it.key)
        }

    }

    private List<Ploeg> filterPloegenMetCorrecteTitel(Vraag vraag) {
        List<Ploeg> ploegen = new ArrayList<>()
        for (PloegAntwoorden ploeg : antwoorden) {
            if (ploeg.getAnswer(vraag.id).titelCorrect) {
                ploegen.add(ploeg.getPloeg())
            }
        }
        return ploegen
    }


    private List<Ploeg> filterPloegenMetCorrecteArtiest(Vraag vraag) {
        List<Ploeg> ploegen = new ArrayList<>()
        for (PloegAntwoorden ploeg : antwoorden) {
            if (ploeg.getAnswer(vraag.id).artiestCorrect) {
                ploegen.add(ploeg.getPloeg())
            }
        }
        return ploegen
    }

    private static int countTitles(List<PloegAntwoorden> ploegAntwoordens, Vraag vraag) {
        ploegAntwoordens.count { it.getAnswer(vraag.id).titelCorrect }
    }

    private static int countArtists(List<PloegAntwoorden> ploegAntwoordens, Vraag vraag) {
        ploegAntwoordens.count { it.getAnswer(vraag.id).artiestCorrect }
    }

    }
