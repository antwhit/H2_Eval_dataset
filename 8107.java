import java.math.BigDecimal;
import java.util.Date;

public class InputUnnecessarilyElse4 {

    public void save(Object data) throws SomeException {
        if (bean.getType().equals(new BigDecimal(45))) {
            if (getFoo().getTypeAsInt() == 42) {
                if (getSource() == null) {
                    throw new SomeException();
                } else if (getSource().isFoo()) {
                    SerializableHelper.save(user);
                } else {
                    throw new SomeException();
                }
            } else if (getFoo().getTypeAsInt() == 0) {
                if (getSource() == null) {
                    throw new SomeException();
                } else if (getSource().isFoo()) {
                    SerializableHelper.save(user);
                } else {
                    throw new SomeException();
                }
            } else {
                throw new SomeException();
            }
        } else {
            throw new SomeException();
        }
    }
}
