package be.ko

class CountFormatter {

    public static String formatCount(int count) {
        if (count == 0) {
            return "[color=#FF4000](${count})[/color]"
        } else if (count < 4) {
            return "[color=#FF8000](${count})[/color]"
        } else {
            return "(${count})"
        }
    }

}
