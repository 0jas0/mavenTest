package com.jas.netty2;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by Administrator on 2017/12/21.
 */
public class ClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=1;i<=5;i++){
            Send send = new Send();
            send.setId(i);
            send.setMessage("message"+i);
            send.setName("name"+i);
            ctx.writeAndFlush(send);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        Receive receive = (Receive) msg;
        System.out.println("客户端接受到服务器反馈的信息："+receive);
    }
}