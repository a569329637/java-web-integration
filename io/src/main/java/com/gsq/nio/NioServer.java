package com.gsq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author guishangquan
 * @date 2018/2/12
 */
public class NioServer {

    NioServer() {
        go();
    }

    private void go() {

        try {
            // 新建选择器
            Selector selector = Selector.open();

            // 新建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(8);

            // 新建通道
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ServerSocket ss = ssc.socket();
            InetSocketAddress isa = new InetSocketAddress(8080);
            ss.bind(isa);
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("开始监听");
            while (selector.select() != -1) {

                Iterator it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();

                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
                        System.out.println("监听到accept事件");

                        // 再注册一个 read 事件
                        ServerSocketChannel ssc1 = (ServerSocketChannel) key.channel();
                        SocketChannel sc1 = ssc1.accept();
                        sc1.configureBlocking(false);
                        sc1.register(selector, SelectionKey.OP_READ);

                        it.remove();
                    } else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
                        System.out.println("监听到read事件");

                        SocketChannel sc = (SocketChannel) key.channel();
                        while (true) {
                            byteBuffer.clear();
                            int ret = sc.read(byteBuffer);
                            if (ret <= 0) {
                                sc.close();
                                System.out.println("读取数据完毕,断开连接");
                                break;
                            }
                            System.out.println("##" + ret + " " + new String(byteBuffer.array(), 0, byteBuffer.position()));
                            byteBuffer.flip();
                        }

                        it.remove();
                    } else {
                        key.cancel();
                        System.out.println("没有注册该事件");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new NioServer();
    }
}
