package test.wangjx.pms.pojo;

import com.wangjx.pms.constant.UserRole;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/23
 * Time: 15:38
 */
public class UserRoleTest {

    @Test
    public void userRoleTest() {
        System.out.println(UserRole.ROOT.getId());
        System.out.println(UserRole.ROOT.getName());
    }
}
