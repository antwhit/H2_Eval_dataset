public class StringConverter {

    public static double[] String2double(String line) {
        double mode = 1;
        boolean new_value = true;
        double after_dot = 10;
        double exponent = 0;
        double vorzeichen = 1;
        double exponent_vorzeichen = 1;
        double[] erg = new double[0];
        for (int i = 0; i < line.length(); i++) {
            if (new_value) {
                double[] erg_buffer = new double[erg.length];
                for (int j = 0; j < erg.length; j++) {
                    erg_buffer[j] = erg[j];
                }
                erg = new double[erg.length + 1];
                for (int j = 0; j < erg_buffer.length; j++) {
                    erg[j] = erg_buffer[j];
                }
                erg[erg.length - 1] = 0;
                new_value = false;
                mode = 1;
                after_dot = 10;
                exponent = 0;
                vorzeichen = 1;
                exponent_vorzeichen = 1;
            }
            char zeichen = line.charAt(i);
            switch(zeichen) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    if (mode == 1) {
                        erg[erg.length - 1] = erg[erg.length - 1] * 10 + (int) zeichen - 48;
                    }
                    if (mode == 2) {
                        erg[erg.length - 1] = erg[erg.length - 1] + ((int) zeichen - 48) / after_dot;
                        after_dot = after_dot * 10;
                    }
                    if (mode == 3) {
                        exponent = exponent * 10 + (int) zeichen - 48;
                    }
                    break;
                case '.':
                    mode = 2;
                    break;
                case 'E':
                case '+':
                    mode = 3;
                    exponent = 0;
                    break;
                case '-':
                    if (mode == 1) {
                        vorzeichen = -1;
                        break;
                    }
                    if (mode == 3) {
                        exponent_vorzeichen = -1;
                        break;
                    }
                case '\t':
                    erg[erg.length - 1] = vorzeichen * erg[erg.length - 1] * Math.pow(10, exponent_vorzeichen * exponent);
                    new_value = true;
                    break;
                default:
                    System.out.println("Mode " + mode + ". What's this?!?: <" + zeichen + "> ignoring...");
            }
        }
        erg[erg.length - 1] = vorzeichen * erg[erg.length - 1] * Math.pow(10, exponent_vorzeichen * exponent);
        return erg;
    }

    public static String[] String2String(String line) {
        String erg[] = { "" };
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '\t') {
                String[] erg_buffer = new String[erg.length];
                for (int j = 0; j < erg.length; j++) {
                    erg_buffer[j] = erg[j];
                }
                erg = new String[erg_buffer.length + 1];
                for (int j = 0; j < erg_buffer.length; j++) {
                    erg[j] = erg_buffer[j];
                }
                erg[erg.length - 1] = "";
            } else {
                erg[erg.length - 1] = erg[erg.length - 1] + line.charAt(i);
            }
        }
        return erg;
    }
}
