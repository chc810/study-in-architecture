package netty;

import io.netty.buffer.ByteBuf;
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
public class ServerChildHandler extends ChannelInboundHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(ServerChildHandler.class);

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("ServerChildHandler.channelRegistered()...");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("ServerChildHandler.channelActive()...");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            logger.info(ctx.name());
            ctx.write(msg);
            ByteBuf byteBuf = (ByteBuf)msg;
            while (byteBuf.isReadable()) {
                System.out.print((char) byteBuf.readByte());
            }
            logger.info("");
            ctx.flush();
        } finally {
//            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("ServerChildHandler.handlerAdded()...");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("ServerChildHandler.handlerRemoved()...");
        super.handlerRemoved(ctx);
    }
}
