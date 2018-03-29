package com.jas.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
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
                 socketChannel.pipeline().addLast(new StringDecoder());
                 socketChannel.pipeline().addLast(new ClientHandler());
             }
         })
        .option(ChannelOption.SO_BACKLOG, 128)
        .option(ChannelOption.SO_KEEPALIVE, true);
        try {
            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 12345).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerGroup.shutdownGracefully();
    }
}
