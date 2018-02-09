package be.ko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ronde {

    private List<Vraag> vragen = new ArrayList<>();
    private Map<Ploeg, List<Answer>> antwoorden = new HashMap<>();

    public void addQuestion(Vraag vraag) {
        vragen.add(vraag);
    }
}
