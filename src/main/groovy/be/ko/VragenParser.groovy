package be.ko;

class VragenParser {

    List<Vraag> parse(File file) {
        List<Vraag> vragen = new ArrayList<>()
        file.eachLine { String line, row ->
            def parts = line.split(";")
            def vraag = new Vraag(id: row, artist: parts[0], title: parts[1], year: parts[2] as int)
            vragen.add(vraag)
        }
        return vragen;
    }
}
