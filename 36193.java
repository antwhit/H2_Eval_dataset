public class VectorObject extends Object {

    private int type;

    private double x;

    private double y;

    private double z;

    private char c;

    public static final int DIGIT = 0;

    public static final int DOT = 1;

    public static final int COMMA = 2;

    public static final int PLUS = 3;

    public static final int MINUS = 4;

    public static final int MULT = 5;

    public static final int L_BRACKET = 6;

    public static final int R_BRACKET = 7;

    public static final int L_SQ_BRACKET = 8;

    public static final int R_SQ_BRACKET = 9;

    public static final int SCALAR = 10;

    public static final int VECTOR = 11;

    public VectorObject(char c) throws Exception {
        super();
        this.c = c;
        switch(c) {
            case '+':
                type = PLUS;
                break;
            case '-':
                type = MINUS;
                break;
            case '*':
                type = MULT;
                break;
            case '.':
                type = DOT;
                break;
            case ',':
                type = COMMA;
                break;
            case '(':
                type = L_BRACKET;
                break;
            case ')':
                type = R_BRACKET;
                break;
            case '[':
                type = L_SQ_BRACKET;
                break;
            case ']':
                type = R_SQ_BRACKET;
                break;
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
                type = DIGIT;
                break;
            default:
                throw new Exception();
        }
    }

    public VectorObject(String x) {
        super();
        type = SCALAR;
        this.x = Double.parseDouble(x);
    }

    public VectorObject(String x, String y, String z) {
        super();
        type = VECTOR;
        this.x = Double.parseDouble(x);
        this.y = Double.parseDouble(y);
        this.z = Double.parseDouble(z);
    }

    public int getType() {
        return type;
    }

    public boolean isPrefix() {
        return (type == PLUS) | (type == MINUS);
    }

    public boolean isOperator() {
        return (type == PLUS) | (type == MINUS) | (type == MULT);
    }

    public boolean isDigit() {
        return (type == DIGIT);
    }

    public boolean isDot() {
        return (type == DOT);
    }

    public boolean isLBr() {
        return (type == L_BRACKET);
    }

    public boolean isRBr() {
        return (type == R_BRACKET);
    }

    public boolean isVector() {
        return (type == VECTOR);
    }

    public char getC() {
        return c;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
