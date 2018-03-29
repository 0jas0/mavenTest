package com.jas.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by Administrator on 2017/12/19.
 */
public class ClientHandler extends ChannelHandlerAdapter{
    /**
     * 向服务器发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端发送来的消息1@$".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端发送来的消息2@$".getBytes()));
        ctx.writeAndFlush(Unpooled.copiedBuffer("客户端发送来的消息3@$".getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //处理业务逻辑
            if(msg instanceof ByteBuf){
                ByteBuf byteBuf = (ByteBuf)msg;
                byte[] data = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(data);
                System.out.println("客户端接受到信息:" + new String(data,"UTF-8"));
            }

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
