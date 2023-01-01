class DemoDataComFabrica {

    public static void main(String args[]) {
        DataComFabrica NatalDe1966 = DataComFabrica.Natal((short) 1966);
        DataComFabrica NatalDe1970 = DataComFabrica.Natal((short) 1970);
        DataComFabrica NatalDe2000 = DataComFabrica.Natal((short) 2000);
        System.out.println(NatalDe1966);
        System.out.println(NatalDe1970);
        System.out.println(NatalDe2000);
    }
}
