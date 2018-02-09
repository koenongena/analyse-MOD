package be.ko

import groovy.transform.CompileStatic;

@CompileStatic
class PloegParser {

    List<Ploeg> parse(File file) {
        def ploegen = []
        file.eachLine {
            def parts = it.split(";")
            ploegen << new Ploeg(naam: parts[0], directory: new File("src/main/resources/" + parts[1]))
        }
        return ploegen
    }
}
