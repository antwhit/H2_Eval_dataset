public class GetOpt {

    private String[] fArgs;

    private String fOptions;

    private String fPlace;

    private int fIndex;

    private int fOffset;

    private String fOptarg;

    public GetOpt(String[] args, String options) {
        fIndex = 0;
        fOffset = 0;
        fOptarg = null;
        fPlace = null;
        fArgs = args;
        fOptions = options;
    }

    public int Next() {
        fOptarg = null;
        if (fPlace == null || fOffset >= fPlace.length()) {
            fOffset = 0;
        }
        if (fOffset == 0) {
            fOffset = 1;
            while (true) {
                int oldi = fIndex;
                if (fIndex >= fArgs.length) return -1;
                fPlace = fArgs[fIndex++];
                if (fPlace == null) continue;
                if (fPlace.charAt(0) != '-') continue;
                if (fPlace.length() == 1) {
                    fOffset = 0;
                }
                if (fPlace.compareTo("--") == 0) {
                    fArgs[oldi] = null;
                    return -1;
                }
                fArgs[oldi] = null;
                break;
            }
        }
        int c = fPlace.charAt(fOffset++);
        int loc = fOptions.indexOf(c);
        if (c == ':' || loc == -1) {
            System.err.printf("Illegal option -- %1$c", c);
            return '?';
        }
        if ((loc + 1) < fOptions.length() && fOptions.charAt(loc + 1) == ':') {
            if (fOffset < fPlace.length()) {
                fOptarg = fPlace.substring(fOffset);
                fOffset = 0;
            } else if (fArgs.length > fIndex) {
                fOptarg = fArgs[fIndex];
                fArgs[fIndex] = null;
                fIndex++;
            } else {
                System.err.printf("Option requires an argument -- %1$c", c);
                return '?';
            }
        }
        return c;
    }

    public String Argument() {
        return fOptarg;
    }

    public String[] CommandLine() {
        int i;
        int count = 0;
        for (i = 0; i < fArgs.length; i++) {
            if (fArgs[i] != null) count++;
        }
        if (count == i) return fArgs;
        String[] tmp = new String[count];
        count = 0;
        for (i = 0; i < fArgs.length; i++) {
            if (fArgs[i] != null) tmp[count++] = fArgs[i];
        }
        return tmp;
    }
}
