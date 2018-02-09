package be.ko;

import java.util.ArrayList;
import java.util.List;

public class PloegAntwoorden {
    private Ploeg ploeg;
    private List<Answer> antwoorden = new ArrayList<>();

    public PloegAntwoorden(Ploeg ploeg) {
        this.ploeg = ploeg;
    }

    public void addAntwoord(Answer answer) {
        antwoorden.add(answer);
    }

    public Ploeg getPloeg() {
        return ploeg;
    }

    public Answer getAnswer(int id) {
        for (Answer answer : antwoorden) {
            if (answer.equalsId(id)) {
                return answer;
            }
        }

        throw new IllegalArgumentException("Wrong ID " + id);
    }
}
