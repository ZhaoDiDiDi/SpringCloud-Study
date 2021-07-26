import com.it.springcloud.dao.DeptDao;
import com.it.springcloud.service.DeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Mytest {

    @Autowired
    DeptDao deptDao;

    @Test
    public void addDept() {
        deptDao.queryById((long) 1);
    }
}
