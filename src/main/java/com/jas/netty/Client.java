package com.jas.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by Administrator on 2017/12/19.
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workerGroup)
         .channel(NioSocketChannel.class)
         .handler(new ChannelInitializer<SocketChannel>() {
             protected void initChannel(SocketChannel socketChannel) throws Exception {
                 //拆包
                 //ByteBuf buf = Unpooled.copiedBuffer("@$".getBytes());
                 //socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                 socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                 // 可以使接受到的数据变成字符串
                 socketChannel.pipeline().addLast(new StringDecoder());
                 // 处理具体的业务逻辑
                 socketChannel.pipeline().addLast(new ClientHandler());
             }
         })
        .option(ChannelOption.SO_BACKLOG, 128)
        .option(ChannelOption.SO_KEEPALIVE, true);
        try {
            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 12345).sync();
            // Wait until the connection is closed.
            Channel channel = f.channel();

            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerGroup.shutdownGracefully();
    }
}
