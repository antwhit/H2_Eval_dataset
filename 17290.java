import java.util.Date;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import edu.mobbuzz.daf.bean.Logs;
import edu.mobbuzz.daf.bean.Stream;
import edu.mobbuzz.daf.dao.ILogsDao;
import edu.mobbuzz.daf.dao.IStreamDao;
import edu.mobbuzz.util.DBUtility;

public class StreamDaoTest {

    private static BeanFactory factory;

    @BeforeClass
    public static void init() {
        factory = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void getMessageInsert() {
        IStreamDao messageDao = (IStreamDao) factory.getBean("streamDao");
        Stream _stream = new Stream();
        _stream.setId(DBUtility.generateId());
        _stream.setSender("aaa");
        _stream.setRecepient("aaa");
        _stream.setStream("aa");
        _stream.setDate(new Date());
        messageDao.insert(_stream);
    }
}
