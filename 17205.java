class Util {

    static String capitilize(String s) {
        if (s == null) return s;
        if (s.length() == 0) return s;
        if (s.length() == 1) return s.toUpperCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    static String decapitilize(String s) {
        if (s == null) return s;
        if (s.length() == 0) return s;
        if (s.length() == 1) return s.toLowerCase();
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    static boolean isEmpty(String s) {
        return s == null || s.equals("");
    }
}
