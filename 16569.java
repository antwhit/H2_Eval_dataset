import gosu.Gosu;
import org.jruby.*;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.anno.JRubyMethod;
import static org.jruby.RubyNumeric.*;

public abstract class GosuProxy {

    public static void createGosuMethods(Ruby runtime) {
        RubyModule mGosu = runtime.getModule("Gosu");
        mGosu.defineAnnotatedMethods(GosuProxy.class);
    }

    @JRubyMethod(name = "milliseconds", module = true)
    public static IRubyObject milliseconds(IRubyObject module) {
        return module.getRuntime().newFixnum(Gosu.milliseconds());
    }

    @JRubyMethod(name = "distance", required = 4, module = true)
    public static IRubyObject distance(IRubyObject module, IRubyObject[] args) {
        double result = Gosu.distance(num2dbl(args[0]), num2dbl(args[1]), num2dbl(args[2]), num2dbl(args[3]));
        return module.getRuntime().newFloat(result);
    }

    @JRubyMethod(name = "angle", required = 4, module = true)
    public static IRubyObject angle(IRubyObject module, IRubyObject[] args) {
        double result = Gosu.angle(num2dbl(args[0]), num2dbl(args[1]), num2dbl(args[2]), num2dbl(args[3]));
        return module.getRuntime().newFloat(result);
    }

    @JRubyMethod(name = "angle_diff", required = 2, module = true)
    public static IRubyObject angleDiff(IRubyObject module, IRubyObject from, IRubyObject to) {
        double result = Gosu.angleDiff(num2dbl(from), num2dbl(to));
        return module.getRuntime().newFloat(result);
    }

    @JRubyMethod(name = "offset_x", required = 2, module = true)
    public static IRubyObject offsetX(IRubyObject module, IRubyObject angle, IRubyObject radius) {
        double result = Gosu.offsetX(num2dbl(angle), num2dbl(radius));
        return module.getRuntime().newFloat(result);
    }

    @JRubyMethod(name = "offset_y", required = 2, module = true)
    public static IRubyObject offsetY(IRubyObject module, IRubyObject angle, IRubyObject radius) {
        double result = Gosu.offsetY(num2dbl(angle), num2dbl(radius));
        return module.getRuntime().newFloat(result);
    }

    @JRubyMethod(name = "default_font_name", module = true)
    public static IRubyObject defaultFontName(IRubyObject module) {
        return module.getRuntime().newString(Gosu.defaultFontName());
    }

    @JRubyMethod(name = "random", required = 2, module = true)
    public static IRubyObject random(IRubyObject module, IRubyObject min, IRubyObject max) {
        return module.getRuntime().newFloat(Gosu.random(num2dbl(min), num2dbl(max)));
    }
}
