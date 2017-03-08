package http;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.Test;

import java.io.IOException;

/**
 * <dl>
 * <dt>HttpClientTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/3/6</dd>
 * </dl>
 *
 * @author cuihc
 */
public class HttpClientTest {

    @Test
    public void testhttps() throws IOException {

        CloseableHttpResponse response = HttpUtils.doHttpsGet("https://ccod4.5d:8443/cas/serviceValidate?ticket=ST-20-6GebrmfhFKKpiKJ0EclH", null, null);
        System.out.print(response);

    }


}
