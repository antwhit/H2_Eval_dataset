public class Util {

    private static final String letraCliente = "C";

    public static String generarCodigo(int id, String letraRol) {
        String res = new Integer(id).toString();
        int n = res.length();
        if (n == 3) res = letraRol + "0" + res; else if (n == 2) res = letraRol + "00" + res; else if (n == 1) res = letraRol + "000" + res; else res = letraRol + res;
        return res;
    }

    public static String generarCodigo(int id) {
        String res = new Integer(id).toString();
        int n = res.length();
        if (n == 3) res = letraCliente + "0" + res; else if (n == 2) res = letraCliente + "00" + res; else if (n == 1) res = letraCliente + "000" + res; else res = letraCliente + res;
        return res;
    }

    public static int getNumDeCodigo(String codUsuario) {
        int n = 0;
        n = Integer.parseInt(codUsuario.substring(1));
        return n;
    }

    public static boolean compruebaCampoNif(char[] pCamp) {
        int x;
        boolean correcto = true;
        if (pCamp.length != 9) {
            correcto = false;
        } else {
            for (x = 0; x < pCamp.length - 1; x++) {
                if (Character.isDigit(pCamp[x])) {
                    correcto = true;
                } else {
                    correcto = false;
                    break;
                }
            }
            if (correcto = true) {
                if (Character.isLetter(pCamp[8])) {
                    correcto = true;
                } else {
                    correcto = false;
                }
            }
        }
        return correcto;
    }

    public static boolean compruebaCampo(char[] pCamp) {
        int x;
        boolean correcto = true;
        for (x = 0; x < pCamp.length; x++) {
            if (Character.isDigit(pCamp[x]) || Character.isLetter(pCamp[x])) {
                correcto = true;
            } else {
                correcto = false;
                break;
            }
        }
        return correcto;
    }

    public static boolean IsNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }

    public static boolean compruebaCampoTamano(char[] pCamp, int tamanoMax) {
        boolean correcto = true;
        if (pCamp.length > tamanoMax || pCamp.length == 0) correcto = false;
        return correcto;
    }
}
