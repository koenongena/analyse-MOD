//def ronde1 = """AH RAHMAN;JAI HO;2008;
//BRAN VAN 3000;ASTOUNDED;2001;
//CLAUDIA SYLVA;J’AI PLEURÉ;1965;
//DILLINGER;RAGNAMPIZA;1976;
//EXECUTIVE SLACKS;SOLEMN DILLEMA;1986;
//FRANCIS BEBEY;THE COFEE COLA SONG;1982;
//GOLDIE LOOKIN CHAIN;GUNS DON’T KILL PEOPLE, RAPPERS DO;2004;
//HAWKWIND;MOTORHEAD;1975;
//ILLUSION;DANSEN IN DE REGEN;1994;
//THE JUDDS;WHY NOT ME;1984;
//KÖLSCH;IN BOTTLES;2017;
//LIVERPOOL 5;ANYWAY THAT YOU WANT ME;1967;
//MARK RONSON (feat. Mystikal);FEEL RIGHT;2015;
//O’ JAYS;BACKSTABBERS;1972;
//PRINCE BUSTER;DANCE CLEOPATRA DANCE;1967;
//QUANNUM (MC’S);I CHANGED MY MIND;1999;
//ROLLING STONES;JUST YOUR FOOL;2016;
//SLIPKNOT;SPIT IT OUT;1999;
//TV ON THE RADIO;WOLF LIKE ME;2006;
//UNDERGROUND SUNSHINE;BIRTHDAY;1969;
//VAN MORRISON;MOONDANCE;1970;
//WALLACE VANBORN;ATOM JUGGLER;2010;
//XTC;WONDERLAND;1983;
//ZOHRA;I HATE TO LOVE YOU;1997;"""
def ronde1 = """AH RAHMAN;JAI HO;2008;
BRAN VAN 3000;ASTOUNDED;2001;"""

//def ploegen = ["PARIS HILTON LOOKALIKES", "WAG TEAM", "LUCKY LOCOS", "THE CRYPTKICKERS", "GARNIZOEN!", "THE 4SHOTA", "ROYAL FLUSH", "GREEN ONIONS", "KIPZ"]
def ploegen = ["PARIS HILTON LOOKALIKES"]

def questions = []
def answers = []
ronde1.eachLine { line, lineNr ->
    def parts = line.split(";")
    def question = new Question(vraag: lineNr, artist: parts[0], title: parts[1], year: parts[2] as int)
    questions << question
    answers << new Answer(question: question)
}


for (String ploeg : ploegen) {
    println ploeg
    println '*' * ploeg.length()
    println '*' * ploeg.length()

    for (Answer answer  : answers ) {
        def question = answer.question
        def artistCorrect = System.console().readLine(question.artist + "? " as String)
        if (artistCorrect == '1'){
            answer.addArtistAnsweredBy(ploeg)
        }
        def titleCorrect = System.console().readLine(question.title + "? " as String)
        if (titleCorrect == '1'){
            answer.addTitleAnsweredBy(ploeg)
        }
    }
}

String formatCount(int count){
    if (count == 0){
        return "[color=#FF4000](${count})[/color]"
    } else if (count < 4){
        return "[color=#FF8000](${count})[/color]"
    } else{
        return "${count}"
    }
}

for (Answer answer : answers) {
    Question question = answer.question
    println "${question.artist} ${formatCount(answer.countArtistsAnswered())} - ${question.title} ${formatCount(answer.countTitlesAnswered())})"
}

//* Lijst QMS op *//


def filterQMS = {
    def qms = []
    answers.each { Answer answer ->
        if (answer.countArtistsAnswered() == 0) {
            qms << answer.question.artist
        }
        if (answer.countTitlesAnswered() == 0) {
            qms << answer.question.title
        }
    }
    return qms
}

/*
* TOPSCORE:
Martha Wainwrong ain't right met 46/48 (95,83%) :handgestures-thumbupright:

GEMIDDELDE SCORE:
39/48 (81,94%)

MOEILIJKERE UITVOERDERS/TITELS:
*Cocoon: The Cryptkickers / Royal Flush
*Until our dying day: The Cryptkickers / Martha Wainwrong ain't right
*Wenn es passiert: Martha Wainwrong ain't right / Wag Team / Garnizoen!

* */

def qms = filterQMS(answers)
for (String a : qms) {
    println "QMS : " + a
}


class Question {
    int vraag
    String artist, title
    int year

    Decade getDecade(){
        switch(year){
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

enum Decade{
    SIXTIES, SEVENTIES, EIGHTIES, NINETIES, NILLIES, TENS;
}

class Answer {
    Question question
    def titleAnsweredBy = []
    def artistAnsweredBy = []

    void addTitleAnsweredBy(String ploeg){
        titleAnsweredBy << ploeg
    }

    void addArtistAnsweredBy(String ploeg){
        artistAnsweredBy << ploeg
    }

    int countArtistsAnswered(){
        return artistAnsweredBy.size()
    }

    int countTitlesAnswered(){
        return titleAnsweredBy.size()
    }
}
