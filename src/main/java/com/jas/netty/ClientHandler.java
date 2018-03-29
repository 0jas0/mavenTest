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
        String requestStr = "客户端发送了一个请求";
        for (int i = 0; i < 100; i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer((requestStr+System.getProperty("line.separator")).getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //处理业务逻辑
            /*if(msg instanceof ByteBuf){
                ByteBuf byteBuf = (ByteBuf)msg;
                byte[] data = new byte[byteBuf.readableBytes()];
                byteBuf.readBytes(data);
                System.out.println("客户端接受到信息:" + new String(data,"UTF-8"));
            }*/
            if (msg instanceof String){
                String message = (String) msg;
                System.out.println("客户端接受的消息："+message);
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
