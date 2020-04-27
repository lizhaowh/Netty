package com.mashibing.nettynio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyIO {


    public static void main(String[] args) {

        NioEventLoopGroup boss = new NioEventLoopGroup(2);
        NioEventLoopGroup worker = new NioEventLoopGroup(2);

        ServerBootstrap boot = new ServerBootstrap();

        try {
            boot.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            System.out.println("client...conn..."+ ch);
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new MyInbound());

                        }
                    });

            ChannelFuture future = boot.bind(9090).sync();

            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

class MyInbound extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
//        ctx.write(msg);
//    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        int size = buf.writerIndex();
        byte[] data = new byte[size];
        buf.getBytes(0,data);
        String dd = new String(data);

        String[] strs = dd.split("\n");

        for (String str : strs) {
            System.out.print("触发的命令："+str+"...");
        }


        ctx.write(msg);

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}