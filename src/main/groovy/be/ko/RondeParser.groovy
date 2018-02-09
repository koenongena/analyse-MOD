package be.ko

class RondeParser {
    Ronde parse(File file) {
        Ronde ronde = new Ronde();
        file.each { line, row ->
            def parts = it.split(";")
            def question = new Vraag(id: row + 1, artist: parts[0], title: parts[1], year: parts[2] as int)
            ronde.addQuestion(question)
        }
        return ronde;
    }
}
