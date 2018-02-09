package be.ko;

public class Answer {
    private int vraagId;
    private boolean artiestCorrect = false;
    private boolean titelCorrect = false;

    public Answer(int vraag, boolean artiestCorrect, boolean titelCorrect) {
        this.vraagId = vraag;
        this.artiestCorrect = artiestCorrect;
        this.titelCorrect = titelCorrect;
    }

    public int getVraagId() {
        return vraagId;
    }

    public boolean isArtiestCorrect() {
        return artiestCorrect;
    }

    public boolean isTitelCorrect() {
        return titelCorrect;
    }

    public boolean equalsId(int id) {
        return this.vraagId == id;
    }
}
