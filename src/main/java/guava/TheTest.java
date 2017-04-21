package guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * <dl>
 * <dt>TheTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/9</dd>
 * </dl>
 *
 * @author cuihc
 */
public class TheTest {

    @Test
    public void testPrecondition() {
        Preconditions.checkArgument(true);
        int i = -1;
        Preconditions.checkArgument(i>=0, "Argument was %s but expected nonnegative", i);
    }
}
