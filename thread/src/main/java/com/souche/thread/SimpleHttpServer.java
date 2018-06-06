package com.souche.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 默认线程池5000次请求分10个线程并发
 * 线程池数量             1            5             10         20
 * 响应时间(ms)          0.161       0.148          0.164      0.166
 * 每秒查询的数量       6225.14      6778.68       6115.83     6036.30
 * 测试完成时间(s)       2.410        2.213         2.453      2.485
 *
 * 线程池工具5000次请求分10个线程并发
 * 线程池数量             1            5             10          20
 * 响应时间(ms)          0.595       0.176          0.161       0.169
 * 每秒查询的数量        1680.32      5689.28       6208.51     5926.21
 * 测试完成时间(s)       8.927        2.637         2.416       2.531
 *
 * @author guishangquan
 * @date 2018/6/5
 */
public class SimpleHttpServer {

    private static final int MAX_PORT = 65536;
    private static final int MIN_PORT = 1;

//    private static ThreadPoolServiceUtil tp = new ThreadPoolServiceUtil();
    private static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<>();
    private static int port = 8080;
    private static String basePath = "/Users/guishangquan/shell";
    private static ServerSocket serverSocket;

    public static void setPort(int port) {
        if (port > MIN_PORT && port < MAX_PORT) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (null != basePath && new File(basePath).exists() && new File(basePath).isDirectory()) {
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void start() throws IOException {
//        tp.init();
        serverSocket = new ServerSocket(port);

        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            threadPool.execute(new HttpRequestHandler(socket));
//            tp.addTask(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    public static void main(String[] args) {
        try {
            SimpleHttpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class HttpRequestHandler implements Runnable {
        Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            FileInputStream input = null;
            ByteArrayOutputStream output = null;
            try {
                out = new PrintWriter(socket.getOutputStream());
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                String path = header.split(" ")[1];

                String filePath = basePath + path;
                if (path.endsWith(".jpg")) {
                    input = new FileInputStream(filePath);
                    output = new ByteArrayOutputStream();
                    int t;
                    while ((t = input.read()) != -1) {
                        output.write(t);
                    }
                    byte[] array = output.toByteArray();
                    String httpHeaderStr = "HTTP/1.1 200 OK\nServer: SimpleHttpServer\nContent-Type: image/jpeg\nContent-Length: " + array.length + "\n\n";
                    socket.getOutputStream().write(httpHeaderStr.getBytes());
                    socket.getOutputStream().write(array);

                } else if (path.endsWith(".html")) {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: SimpleHttpServer");
                    out.println("Content-Type: text/html; charset=utf-8");
                    out.println("");
                    String t;
                    while ((t = br.readLine()) != null) {
                        out.println(t);
                    }
                } else {
                    filePath = basePath + "/404.txt";
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out.println("HTTP/1.1 404 Not Found");
                    out.println("Server: SimpleHttpServer");
                    out.println("Content-Type: text/plain");
                    out.println("");
                    String t;
                    while ((t = br.readLine()) != null) {
                        out.println(t);
                    }
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            } finally {
                close(br, output, input, reader, out, socket);
            }
        }

        private void close(Closeable... closeables) {
            if (null != closeables) {
                for (Closeable c : closeables) {
                    try {
                        if (null != c) {
                            c.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }
    }
}
