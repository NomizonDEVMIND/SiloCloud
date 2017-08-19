package de.siloks.silocloud;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.StandardCharsets;

/**
 * Erstellt von Lars am 17.08.2017.
 * Alle Rechte vorbehalten. Der Entwickler kann jederzeit
 * die Rechte an diesem Code entziehen!
 */
public class Client {

    public static final boolean EPOLL = Epoll.isAvailable();

    public Client(String message) throws Exception {
        EventLoopGroup eventLoopGroup = EPOLL ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            Channel channel = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(EPOLL ? EpollSocketChannel.class : NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new StringDecoder(StandardCharsets.UTF_8)).addLast(new StringEncoder(StandardCharsets.UTF_8));
                        }
                    }).connect("127.0.0.1", 801).sync().channel();
            channel.writeAndFlush(message);
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void send(String message){
        try {
            new Client(message);
        } catch (Exception e) {}
    }

}