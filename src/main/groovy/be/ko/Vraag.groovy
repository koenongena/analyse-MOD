package be.ko

class Vraag {
    int id
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
