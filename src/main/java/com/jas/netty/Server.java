package com.jas.netty;

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
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by Administrator on 2017/12/19.
 */
public class Server {
    public static void main(String[] args) {
        // NioEventLoopGroup是个线程组，它包含了一组NIO线程，专门用于网络事件的处理，实际上它们就是Reactor线程组。
        // 这里创建两个线程的原因是一个用于服务端接受客户端的连接，另一个是用于SocketChannel的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup)
         .channel(NioServerSocketChannel.class)
         .childHandler(new ChannelInitializer<SocketChannel>() {
             protected void initChannel(SocketChannel socketChannel) throws Exception {
                 //拆包
                 ByteBuf buf = Unpooled.copiedBuffer("@$".getBytes());
                 socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                 //可以使接受到的数据变成字符串
                 socketChannel.pipeline().addLast(new StringDecoder());
                 socketChannel.pipeline().addLast(new ServerHandler());
             }
         })
        .option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true);
        try {
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(12345).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
