package be.ko

import static be.ko.CountFormatter.formatCount

class Theorieronde {
    void print(PrintStream out) {
        out.println("""[b]THEORIERONDE[/b]

1. ALESSIA CARA ${formatCount(3)}
2. CORROSION OF CONFORMITY ${formatCount(2)}
3. HE'S SIMPLE HE'S DUMB HE'S THE PILOT (ARTIEST: GRANDADDY) ${formatCount(6)}
4. THE WEEKND ${formatCount(3)}
5. FISCHER Z ${formatCount(3)}
6. EVA DE ROO ${formatCount(9)}
7. BILLY JOEL  ${formatCount(8)}
8. READING (FESTIVAL) ${formatCount(5)}
9. US ${formatCount(6)}
10. AND YOU WILL KNOW US BY THE TRAIL OF DEAD... ${formatCount(2)}
11. ROBERT TRUJILLO ${formatCount(8)}
12. IN THE MORNING ${formatCount(5)}

Minst beantwoord:
2x: CORROSION OF CONFORMITY (Garnizoen!, WAG Team)
2x: AND YOU WILL KNOW US BY THE TRAIL OF DEAD... (Paris Hilton Lookalikes, WAG Team)
3x: ALESSIA CARA (Royal Flush, Green Onions, Lucky Locos)
3x: THE WEEKND (Garnizoen!, Royal Flush, Paris Hilton Lookalikes)
3x: FISCHER Z (WAG Team, The 4 sLEEpless Horsemen Of The Apocalypse, Lucky Locos)

**************************
""")
    }
}
