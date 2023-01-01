import org.junit.Test;
import junit.framework.Assert;

public class ConeTest {

    int radius = 2;

    int NegativeRadius = -2;

    int ZeroRadius = 0;

    int height = 4;

    int NegativeHeight = -4;

    int ZeroHeight = 0;

    double volume = Math.PI / 3 * (double) Math.pow(radius, 2) * height;

    double delta = 0.00002;

    @Test
    public void CanCreateCone() {
        Cone cone = new Cone();
        Assert.assertNotNull(cone);
    }

    @Test
    public void CanInitializeCone() {
        Cone cone = new Cone(radius, height);
        Assert.assertNotNull(cone);
    }

    @Test
    public void CanGetConeRadius() {
        Cone cone = new Cone(radius, height);
        Assert.assertEquals(radius, cone.GetRadius());
    }

    @Test
    public void CanGetConeHeight() {
        Cone cone = new Cone(radius, height);
        Assert.assertEquals(height, cone.GetHeight());
    }

    @Test
    public void CanSetConeRadius() {
        Cone cone = new Cone();
        cone.SetRadius(radius);
        Assert.assertEquals(radius, cone.GetRadius());
    }

    @Test
    public void CanSetConeHeight() {
        Cone cone = new Cone();
        cone.SetHeight(height);
        Assert.assertEquals(height, cone.GetHeight());
    }

    @Test
    public void CanCalculateVolume() {
        Cone cone = new Cone(radius, height);
        Assert.assertEquals(volume, cone.CalculateVolume(), delta);
    }

    @Test(expected = ParameterValueException.class)
    public void CanCheckExceptionWithNegativeRadius() throws ParameterValueException {
        Cone cone = new Cone(NegativeRadius, height);
        cone.ValidateRadius();
    }

    @Test(expected = ParameterValueException.class)
    public void CanCheckExceptionWithNegativeHeight() throws ParameterValueException {
        Cone cone = new Cone(radius, NegativeHeight);
        cone.ValidateHeight();
    }

    @Test
    public void CanCalculateVolumeWithNegativeRadius() {
        Cone cone = new Cone(NegativeRadius, height);
        Assert.assertEquals(volume, cone.CalculateVolume(), delta);
    }

    @Test
    public void CanCalculateVolumeWithZeroRadius() {
        Cone cone = new Cone(ZeroRadius, height);
        Assert.assertEquals(volume, cone.CalculateVolume(), delta);
    }

    @Test
    public void CanCalculateVolumeWithNegativeHeight() {
        Cone cone = new Cone(radius, NegativeHeight);
        Assert.assertEquals(volume, cone.CalculateVolume(), delta);
    }

    @Test
    public void CanCalculateVolumeWithZeroHeight() {
        Cone cone = new Cone(radius, ZeroHeight);
        Assert.assertEquals(volume, cone.CalculateVolume(), delta);
    }
}
