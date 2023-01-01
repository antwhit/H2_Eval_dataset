public class VisitorSample {

    public static void main(String[] args) {
        Visitor<Double> calc = new Visitor<Double>() {

            @Override
            Double visit(Add e) {
                return e.l.accept(this) + e.r.accept(this);
            }

            @Override
            Double visit(Sub e) {
                return e.l.accept(this) - e.r.accept(this);
            }

            @Override
            Double visit(Val e) {
                return e.v;
            }
        };
        Visitor<String> dump = new Visitor<String>() {

            @Override
            String visit(Add e) {
                return e.l.accept(this) + " + " + e.r.accept(this);
            }

            @Override
            String visit(Sub e) {
                return e.l.accept(this) + " - " + e.r.accept(this);
            }

            @Override
            String visit(Val e) {
                return String.valueOf(e.v);
            }
        };
        Add node = new Add(new Val(2), new Sub(new Val(3), new Val(1)));
        System.out.println(node.accept(dump));
        System.out.println(node.accept(calc));
    }

    abstract static class Visitor<T> {

        abstract T visit(Add e);

        abstract T visit(Sub e);

        abstract T visit(Val e);
    }

    abstract static class Expr {

        abstract <T> T accept(Visitor<T> v);
    }

    static class Add extends Expr {

        Expr l, r;

        Add(Expr l, Expr r) {
            this.l = l;
            this.r = r;
        }

        @Override
        <T> T accept(Visitor<T> v) {
            return v.visit(this);
        }
    }

    static class Sub extends Expr {

        Expr l, r;

        Sub(Expr l, Expr r) {
            this.l = l;
            this.r = r;
        }

        @Override
        <T> T accept(Visitor<T> v) {
            return v.visit(this);
        }
    }

    static class Val extends Expr {

        double v;

        Val(double v) {
            this.v = v;
        }

        @Override
        <T> T accept(Visitor<T> v) {
            return v.visit(this);
        }
    }
}
