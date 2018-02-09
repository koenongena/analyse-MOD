def rondeNaam = args[0]

assert !rondeNaam.isEmpty()

def ronde1 = """AH RAHMAN;JAI HO;2008;
BRAN VAN 3000;ASTOUNDED;2001;
CLAUDIA SYLVA;J’AI PLEURÉ;1965;
DILLINGER;RAGNAMPIZA;1976;
EXECUTIVE SLACKS;SOLEMN DILLEMA;1986;
FRANCIS BEBEY;THE COFEE COLA SONG;1982;
GOLDIE LOOKIN CHAIN;GUNS DON’T KILL PEOPLE, RAPPERS DO;2004;
HAWKWIND;MOTORHEAD;1975;
ILLUSION;DANSEN IN DE REGEN;1994;
THE JUDDS;WHY NOT ME;1984;
KÖLSCH;IN BOTTLES;2017;
LIVERPOOL 5;ANYWAY THAT YOU WANT ME;1967;
MARK RONSON (feat. Mystikal);FEEL RIGHT;2015;
O’ JAYS;BACKSTABBERS;1972;
PRINCE BUSTER;DANCE CLEOPATRA DANCE;1967;
QUANNUM (MC’S);I CHANGED MY MIND;1999;
ROLLING STONES;JUST YOUR FOOL;2016;
SLIPKNOT;SPIT IT OUT;1999;
TV ON THE RADIO;WOLF LIKE ME;2006;
UNDERGROUND SUNSHINE;BIRTHDAY;1969;
VAN MORRISON;MOONDANCE;1970;
WALLACE VANBORN;ATOM JUGGLER;2010;
XTC;WONDERLAND;1983;
ZOHRA;I HATE TO LOVE YOU;1997;"""
//def ronde1 = """AH RAHMAN;JAI HO;2008;
//BRAN VAN 3000;ASTOUNDED;2001;"""

def ploegen = ["THE 4SHOTA", "THE CRYPTKICKERS", "GARNIZOEN!", "GREEN ONIONS", "KIPZ", "LUCKY LOCOS", "PARIS HILTON LOOKALIKES", "ROYAL FLUSH", "WAG TEAM"]
//def ploegen = ["PARIS HILTON LOOKALIKES", "WAG"]

def questions = []
def answers = []
ronde1.eachLine { line, lineNr ->
    def parts = line.split(";")
    def question = new Question(vraag: lineNr + 1, artist: parts[0], title: parts[1], year: parts[2] as int)
    questions << question
    answers << new Answer(question: question)
}

DecadeScoreboard decadeScoreboard = new DecadeScoreboard(ploegen)
for (String ploeg : ploegen) {
    println ploeg
    println '*' * ploeg.length()

    for (Answer answer : answers) {
        def question = answer.question
        def artistCorrect = System.console().readLine(question.artist + " - " + question.title + "? " as String)
        if (artistCorrect[0] == '1') {
            answer.addArtistAnsweredBy(ploeg)
            decadeScoreboard.add(question.decade, ploeg)
        }
        if (artistCorrect[1] == '1') {
            answer.addTitleAnsweredBy(ploeg)
            decadeScoreboard.add(question.decade, ploeg)
        }
    }
    println ''
}

String formatCount(int count) {
    if (count == 0) {
        return "[color=#FF4000](${count})[/color]"
    } else if (count < 4) {
        return "[color=#FF8000](${count})[/color]"
    } else {
        return "(${count})"
    }
}

println rondeNaam
println '*' * rondeNaam.length()

for (Answer answer : answers) {
    Question question = answer.question
    println "${question.vraag}. ${question.artist} ${formatCount(answer.countArtistsAnswered())} - ${question.title} ${formatCount(answer.countTitlesAnswered())})"
}


println ""
println "TOPSCORE"
def scores = determineScores(answers)
def maxScore = answers.size() * 2
def topscore = findTopScore(scores)
def topscorendePloegende = scores.findAll { it.value == topscore }.collect { it.key }
println topscorendePloegende.join(" / ") + ": " + topscore + "/" + maxScore + "(${topscore * 100 / maxScore}%)"

//Gemiddelde score
int sum = 0
scores.each { k, score ->
    sum += score
}
def gemiddleScore = sum / ploegen.size()
println ""
println "GEMIDDELDE SCORE:"
println "Gemiddelde score ${gemiddleScore}/${maxScore} (${gemiddleScore * 100 / maxScore}%)"

//MOEILIJKERE UITVOERDERS/TITELS
println ""
println "MOEILIJKERE UITVOERDERS/TITELS"
filterBasedOnAnswers(answers, 0).each { vraag, pl ->
    System.out.println vraag + ": QMS"
}

filterBasedOnAnswers(answers, 1).each { vraag, pl ->
    System.out.println vraag + ": " + pl.join(" / ")
}

filterBasedOnAnswers(answers, 2).each { vraag, pl ->
    System.out.println vraag + ": " + pl.join(" / ")
}

filterBasedOnAnswers(answers, 3).each { vraag, pl ->
    System.out.println vraag + ": " + pl.join(" / ")
}

new File(rondeNaam + ".csv" as String).withWriter { writer -> writer.append(decadeScoreboard.toString()) }

//* Lijst QMS op *//

def determineScores(answers) {
    def s = [:]
    for (Answer answer : answers) {
        for (String ploeg : answer.titleAnsweredBy) {
            if (!s.containsKey(ploeg)) {
                s[ploeg] = 0
            }
            s[ploeg] = s[ploeg] + 1
        }
        for (String ploeg : answer.artistAnsweredBy) {
            if (!s.containsKey(ploeg)) {
                s[ploeg] = 0
            }
            s[ploeg] = s[ploeg] + 1
        }
    }

    return s
}

def filterBasedOnAnswers(answers, amount) {
    def a = [:]
    answers.each { Answer answer ->
        if (answer.countArtistsAnswered() == amount) {
            a.put(answer.question.artist, answer.artistAnsweredBy)
        }
        if (answer.countTitlesAnswered() == amount) {
            a.put(answer.question.title, answer.titleAnsweredBy)
        }
    }
    return a
}

def qms = filterBasedOnAnswers(answers)
for (String a : qms) {
    println "QMS : " + a
}


class Question {
    int vraag
    String artist, title
    int year

    Decade getDecade() {
        switch (year) {
            case 1900..1969: return Decade.SIXTIES
            case 1970..1979: return Decade.SEVENTIES
            case 1980..1989: return Decade.EIGHTIES
            case 1990..1999: return Decade.NINETIES
            case 2000..2009: return Decade.NILLIES
            case 2010..2019: return Decade.TENS
        }

        throw new RuntimeException("AAAAAHHHH, wrong era")
    }
}

enum Decade {
    SIXTIES, SEVENTIES, EIGHTIES, NINETIES, NILLIES, TENS;
}

class Answer {
    Question question
    def titleAnsweredBy = []
    def artistAnsweredBy = []

    void addTitleAnsweredBy(String ploeg) {
        titleAnsweredBy << ploeg
    }

    void addArtistAnsweredBy(String ploeg) {
        artistAnsweredBy << ploeg
    }

    int countArtistsAnswered() {
        return artistAnsweredBy.size()
    }

    int countTitlesAnswered() {
        return titleAnsweredBy.size()
    }
}

int findTopScore(Map scores) {
    int max = 0
    scores.each { k, v ->
        max = (v > max ? v : max)
    }
    return max
}

class DecadeScoreboard {
    private List<String> ploegen
    private Map<String, Map<Decade, Integer>> scores = new HashMap<>()

    DecadeScoreboard(List<String> ploegen) {
        this.ploegen = ploegen
        ploegen.each {
            scores.put(it, createDecadeMap())
        }
    }

    static Map<Decade, Integer> createDecadeMap() {
        def map = new HashMap<Decade, Integer>()
        Decade.values().each { map.put(it, 0) }
        map
    }

    void add(Decade decade, String ploeg) {
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