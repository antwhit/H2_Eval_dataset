public class main_exemplo_completo {

    public static void main(String args[]) {
        int a, b, c;
        a = 6;
        System.out.print("\na='" + a + "'\n");
        b = 3;
        System.out.print("b='" + b + "'\n");
        c = soma(a, b);
        System.out.print("soma(a,b)='" + c + "'\n");
        System.out.print("subtracao(a,b)='" + subtracao(a, b) + "'\n");
        c = multiplicao(a, b);
        System.out.print("multiplicao(a,b)='" + c + "'\n");
        c = divisao(a, b);
        System.out.print("divisao(a,b)='" + c + "'\n");
        c = resto(a, b);
        System.out.print("resto(a,b)='" + c + "'\n");
        c = negativacao(a);
        System.out.print("negativacao(a,b)='" + c + "'\n");
        c = incremento(a, b);
        System.out.print("incremento(a,b)='" + c + "'\n");
        c = and(a, b);
        System.out.print("and(a,b)='" + c + "'\n");
        c = or(a, b);
        System.out.print("or(a,b)='" + c + "'\n");
        c = xor(a, b);
        System.out.print("xor(a,b)='" + c + "'\n");
        c = not(a);
        System.out.print("not(a)='" + c + "'\n");
        c = shiftLeft(a, 1);
        System.out.print("shiftLeft(a,1)='" + c + "'\n");
        c = shiftRight(a, 1);
        System.out.print("shiftRight(a,1)='" + c + "'\n");
    }

    static int soma(int a, int b) {
        return a + b;
    }

    static int subtracao(int a, int b) {
        return a - b;
    }

    static int multiplicao(int a, int b) {
        return a * b;
    }

    static int divisao(int a, int b) {
        return a / b;
    }

    static int resto(int a, int b) {
        return a % b;
    }

    static int negativacao(int a) {
        return -a;
    }

    static int incremento(int a, int b) {
        return soma(a, b);
    }

    static int and(int a, int b) {
        return a & b;
    }

    static int or(int a, int b) {
        return a | b;
    }

    static int xor(int a, int b) {
        return a ^ b;
    }

    static int not(int a) {
        return ~a;
    }

    static int shiftLeft(int a, int b) {
        return a << b;
    }

    static int shiftRight(int a, int b) {
        return a >> b;
    }
}
