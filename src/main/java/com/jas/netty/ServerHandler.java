package com.jas.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Administrator on 2017/12/19.
 */
public class ServerHandler extends ChannelHandlerAdapter{
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //处理业务逻辑
            /*if (msg instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) msg;
                byte[] data = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(data);
                System.out.println("服务器端接受到信息：" + new String(data, "UTF-8"));
            }*/
            ChannelFuture channelFuture = null;
            if (msg instanceof String){
                String message = (String)msg;
                System.out.println("服务器接受了一个消息："+message);
            }
            channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer(("服务器个你反馈的信息"+System.getProperty("line.separator")).getBytes()));
            //关闭连接的客户端，服务器不关闭
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
