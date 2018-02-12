package com.gsq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author guishangquan
 * @date 2018/2/12
 */
public class NioClient {

    NioClient() {
        go();
    }

    private void go() {
        try {
            // 新建选择器
            Selector selector = Selector.open();

            // 新建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 新建通道
            SocketChannel sc = SocketChannel.open();
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress(8080));
            sc.register(selector, SelectionKey.OP_CONNECT);

            selector.select();
            Iterator it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey key = (SelectionKey) it.next();

                if (key.isConnectable()) {
                    if (sc.isConnectionPending()) {
                        if (sc.finishConnect()) {
                            // 只有当连接成功后才能注册OP_READ事件
                            key.interestOps(SelectionKey.OP_READ);
                            byteBuffer.put("123456789abcdefghijklmnopq".getBytes());
                            byteBuffer.flip();
                            sc.write(byteBuffer);
                            System.out.println("##" + new String(byteBuffer.array()));
                            System.out.println("写入完毕");

                            it.remove();
                        } else {
                            key.cancel();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NioClient();
    }
}
