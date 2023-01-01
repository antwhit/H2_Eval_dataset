import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXB;
import junit.framework.TestCase;
import utils.FormulaCalculator;
import container.Weapon;
import container.WeaponTrait;
import container.Weapons;
import enums.Operator;

public class TestEnums extends TestCase {

    public void testPlus() {
        System.out.println(Operator.PLUS.performOperation(1, 3));
    }

    public void testFormula() {
        String formula = "1+2*3*4/5+7";
        FormulaCalculator util = new FormulaCalculator();
        double result = util.calculateFormula(formula, 8);
        System.out.println(formula + " = " + result);
    }

    public void testFormulaWith2X() {
        String formula = "1+2*3*4/5+7+x0*x1";
        FormulaCalculator util = new FormulaCalculator();
        double result = util.calculateFormula(formula, 8, 3);
        System.out.println(formula + " = " + result);
    }

    public void testFormulaWithX() {
        String formula = "1+2*3*4/5+7+x0";
        FormulaCalculator util = new FormulaCalculator();
        double result = util.calculateFormula(formula, 8);
        System.out.println(formula + " = " + result);
    }

    public void testWeaponSystemOut() {
        Weapon wep = new Weapon();
        wep.setAccuracy(1);
        wep.setDamageMultiplicator(12);
        wep.setOptimalRange(12);
        wep.setLongRange(24);
        wep.setWeaponName("Einfache Waffe");
        wep.setWeaponCode("EW");
        wep.setWeaponsTraits(new ArrayList<WeaponTrait>());
        JAXB.marshal(wep, System.out);
    }

    public void testWeaponFile() {
        Weapon wep = new Weapon();
        wep.setAccuracy(1);
        wep.setDamageMultiplicator(12);
        wep.setOptimalRange(12);
        wep.setLongRange(24);
        wep.setWeaponName("Einfache Waffe");
        wep.setWeaponCode("EW");
        wep.setWeaponsTraits(new ArrayList<WeaponTrait>());
        JAXB.marshal(wep, new File("./XML_Files/Weapons.xml"));
        Weapon wepUnMarschalled = JAXB.unmarshal(new File("./XML_Files/Weapons.xml"), Weapon.class);
        System.out.println(wepUnMarschalled.getWeaponName());
    }

    public void testWeaponList() {
        Weapon wep1 = new Weapon();
        wep1.setAccuracy(1);
        wep1.setDamageMultiplicator(12);
        wep1.setOptimalRange(12);
        wep1.setLongRange(24);
        wep1.setWeaponName("Einfache Waffe");
        wep1.setWeaponCode("EW");
        wep1.setWeaponsTraits(new ArrayList<WeaponTrait>());
        Weapon wep2 = new Weapon();
        wep2.setAccuracy(1);
        wep2.setDamageMultiplicator(12);
        wep2.setOptimalRange(12);
        wep2.setLongRange(24);
        wep2.setWeaponName("Einfache Waffe2");
        wep2.setWeaponCode("EW2");
        wep2.setWeaponsTraits(new ArrayList<WeaponTrait>());
        Weapon wep3 = new Weapon();
        wep3.setAccuracy(1);
        wep3.setDamageMultiplicator(12);
        wep3.setOptimalRange(12);
        wep3.setLongRange(24);
        wep3.setWeaponName("Einfache Waffe3");
        wep3.setWeaponCode("EW3");
        wep3.setWeaponsTraits(new ArrayList<WeaponTrait>());
        Weapons weapons = new Weapons();
        weapons.addWeapon(wep2);
        weapons.addWeapon(wep3);
        weapons.addWeapon(wep1);
        JAXB.marshal(weapons, new File("./XML_Files/Weapons.xml"));
    }
}
