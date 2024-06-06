package com.example.userservice.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

class ServerThread extends Thread{

    Socket socket;
    ArrayList<MySocket> sockets;

    public ServerThread(Socket socket, ArrayList<MySocket> sockets) {//获取主函数传过来的值
        this.socket=socket;
        this.sockets=sockets;
    }

    @Override
    public void run() {
        while(true){//接收一个用户的多次发送信息
            try {
                InputStream inputStream=socket.getInputStream();//字节输入流
                DataInputStream din=new DataInputStream(inputStream);//数据输入流
                String text=din.readUTF();//获取输入的信息

                String user="";//设置当前用户名为空，以防下一次赋值
                for(int i=0;i<sockets.size();i++){//遍历sockets集合
                    if(socket==sockets.get(i).getSocket()){//判断当前发放信息的用户
                        user=sockets.get(i).getUser();//获取当前发放信息的用户名
                    }
                }

                for(int i=0;i<sockets.size();i++){
                    Socket sc=sockets.get(i).getSocket();//获取每个用户的socket
                    if(sc!=socket){//判断 不需要自己发信息给自己
                        OutputStream outputStream=sc.getOutputStream();//字节输出流
                        DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
                        dout.writeUTF(user+":"+text+"\n");//把当前客户端的名字和信息发送给除自己以外的其他客户端
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

class MySocket{//把每一台客户端的socket 和用户名联系起来
    private Socket socket;//客户端的socket
    private String user;//客户名

    public MySocket(Socket socket, String user) {
        this.socket=socket;
        this.user=user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

public class server {
    static ArrayList<MySocket> sockets=new ArrayList<MySocket>();//定义一个静态的集合里面的类型是class类 MySocket

    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(9000);//服务器的端口号为9000
        System.out.println("服务器准备就绪");

        while(true){//收集每一次客户端的连接
            Socket socket=ss.accept();
            System.out.println("响应请求");

            //每响应一个客户端就产生一个myscokcet
            InputStream inputStream=socket.getInputStream();//字节输入流
            DataInputStream din=new DataInputStream(inputStream);//数据输入流
            String user=din.readUTF();//获取客户端的名称
            user=user.substring(0,user.length()-4);//把客户端的名字获取，删除“加入聊天”
            MySocket ms=new MySocket(socket,user);//把用户的名字和socket放到MySocket里

            sockets.add(ms);//再添加到集合里，这样就可以保存每一个客户端的名字和socket

            new ServerThread(socket,sockets).start();//启动线程 把当前客户端的socket和加入到服务器的所有socket传到线程通过构造器，并开启线程run方法
        }
    }
}

