class DemoRoboSimples2 {

    public static void main(String[] args) {
        RoboSimples2 jaburu = new RoboSimples2("Jaburu", 20, 10, 'S');
        RoboSimples2 chico = new RoboSimples2("Chico");
        RoboSimples2 semNome = new RoboSimples2();
        jaburu.move(10);
        chico.mudaDirecao('E');
        chico.move(5);
        semNome.move();
        System.out.println(jaburu);
        System.out.println("---------------------------------------------");
        System.out.println(chico);
        System.out.println("---------------------------------------------");
        System.out.println(semNome);
        System.out.println("---------------------------------------------");
    }
}
