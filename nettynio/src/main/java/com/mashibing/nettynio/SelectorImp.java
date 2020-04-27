package com.mashibing.nettynio;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.AbstractSelector;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author: 马士兵教育
 * @create: 2020-04-26 14:52
 */
public class SelectorImp {

    public static void main(String[] args) {
        SelectorProvider provider = SelectorProvider.provider();
        try {
            AbstractSelector selector = provider.openSelector();
            ServerSocketChannel ssc = provider.openServerSocketChannel();
            ssc.bind(new InetSocketAddress(9999));
            ssc.configureBlocking(false);
            ssc.setOption(StandardSocketOptions.TCP_NODELAY,false);





        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
