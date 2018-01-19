package spi.impl;

import spi.HelloInterface;

/**
 * <dl>
 * <dt>ImageHello</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2017/11/30</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ImageHello implements HelloInterface{
    public void sayHello() {
        System.out.println("Image hello.");
    }
}
