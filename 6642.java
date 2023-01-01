import kea.busfw.*;

/**
 * AccountType  represent a account type object in finance
 * 
 * @author <A HREF="mailto: zbingfeng@163.net">bingfeng zhang</A>
</A>
 */
public class AccountType extends BusEntityImpl implements BusEntity {

    public String id;

    public String name;

    /**
     * Get a primary key of Business entity object
     * @return String  a business primary key, 
     * the return value may null,if the BusEntity Object is created
     * and  it's not saved.
     * @author <A HREF="mailto: zbingfeng@163.net">bingfeng zhang</A>
     */
    public String getId() {
        return this.id;
    }

    /**
     * set a primary key of Business entity object
     * @author <A HREF="mailto: zbingfeng@163.net">bingfeng zhang</A>
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get account type name
     * @author <A HREF="mailto: zbingfeng@163.net">bingfeng zhang</A>
     */
    public String getName() {
        return this.name;
    }

    /**
     * set account type name 
     * @author <A HREF="mailto: zbingfeng@163.net">bingfeng zhang</A>
     */
    public void setName(String name) {
        this.name = name;
    }
}
