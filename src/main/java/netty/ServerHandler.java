package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <dl>
 * <dt>ServerHandler</dt>
 * <dd>Description:</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/7/24</dd>
 * </dl>
 *
 * @author cuihc
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerHandler.channelRegistered()...");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerHandler.channelActive()...");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ServerHandler.handlerAdded()...");
        super.handlerAdded(ctx);
    }
}
