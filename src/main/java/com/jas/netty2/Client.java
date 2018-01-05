package com.jas.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by Administrator on 2017/12/21.
 */
public class Client {

    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup worker = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(worker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
                        //sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,buf));
                        //sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                        sc.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                        sc.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture f=b.connect("127.0.0.1",8765).sync();
        for(int i=1;i<=5;i++){
            Send send = new Send();
            send.setId(i);
            send.setMessage("message"+i);
            send.setName("name"+i);
            f.channel().writeAndFlush(send);
        }
        f.channel().closeFuture().sync();
        worker.shutdownGracefully();
    }
}
