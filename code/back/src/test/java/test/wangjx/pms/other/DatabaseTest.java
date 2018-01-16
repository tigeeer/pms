package test.wangjx.pms.other;

import com.wangjx.pms.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: tigeeer
 * Date: 2017/10/23
 * Time: 16:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class DatabaseTest {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void test() {
        try {
            sqlSessionTemplate.getConnection();
            DatabaseMetaData meta = sqlSessionTemplate.getConnection().getMetaData();
            System.out.println(meta.getDatabaseProductName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
