package com.jas.netty2;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created by Administrator on 2017/12/21.
 */
public class ServertHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Send send = (Send) msg;
        System.out.println("client发送："+send);

        Receive receive = new Receive();
        receive.setId(send.getId());
        receive.setMessage(send.getMessage());
        receive.setName(send.getName());
        ctx.writeAndFlush(receive);
    }

}
