package test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * <dl>
 * <dt>TheTest</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/12/22</dd>
 * </dl>
 *
 * @author cuihc
 */
public class TheTest {

    @Test
    public void test() {
        String s = "你好";
        try {
            byte[] bytes = s.getBytes("GB2312");
            System.out.println(bytes.length);
            System.out.println(bytesToHexString(bytes));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            System.out.println();
        }
        return stringBuilder.toString();
    }

    /**
     * @Author cuihc
     * @Date 0:57
     * cmt
     */

    /**
     * @Author cuihc
     * @Date 2017/12/24
     *
    

    /**
     * @Author cuihc
     * @Date
     */
    
    /**
     * @Author cuihc
     * @Date 2017/12/24 
     */

    /**
     * @Author cuihc
     * @Date 2017/12/24
     */
     /**
      * @Author cuihc
      * @Date 2017/12/24
      */


}
