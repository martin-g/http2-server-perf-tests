package com.example.netty.http2;

import io.netty.channel.socket.ServerSocketChannel;
import io.netty.incubator.channel.uring.IOUringEventLoopGroup;
import io.netty.incubator.channel.uring.IOUringServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.ssl.SslContext;

public final class Http2Server {

    private static final int PORT = 8080;
    private static final Logger logger = LoggerFactory.getLogger(Http2Server.class);

    public static void main(String[] args) throws Exception {
        final SslContext sslCtx = Http2Util.createSSLContext(true);

        EventLoopGroup group;
        Class<? extends ServerSocketChannel> channelClass;
        if (args.length == 1 && "io_uring".equals(args[0])) {
            logger.info("Using io_uring !");
            group = new IOUringEventLoopGroup();
            channelClass = IOUringServerSocketChannel.class;
        } else {
            logger.info("Using NIO (epoll) !");
            group = new NioEventLoopGroup();
            channelClass = NioServerSocketChannel.class;
        }

        try {

            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(group)
                .channel(channelClass)
//                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        if (sslCtx != null) {
                            ch.pipeline()
                                .addLast(sslCtx.newHandler(ch.alloc()), Http2Util.getServerAPNHandler());
                        }
                    }

                });

            Channel ch = b.bind(PORT)
                .sync()
                .channel();

            logger.info("HTTP/2 Server is listening on https://127.0.0.1:" + PORT + '/');

            ch.closeFuture()
                .sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
