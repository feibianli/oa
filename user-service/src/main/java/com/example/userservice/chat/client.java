package com.example.userservice.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.Socket;

class CilThr extends Thread{
    Socket socket;
    JTextArea ta;
    public CilThr(Socket socket,JTextArea ta){//获取客户端的socket和文本区域
        this.socket=socket;
        this.ta=ta;

    }
    @Override
    public void run() {
        while (true){//一直在接收中
            try {
                InputStream inputStream=socket.getInputStream();//字节输入流
                DataInputStream din=new DataInputStream(inputStream);//数据输入流
                String text=din.readUTF();//获取输入的数据
                ta.append(text);//添加到文本区域

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

public class client {
    //定义窗口
    JFrame jf;
    JLabel jLabel;
    JTextField tf1,tf2;
    JButton start,send;
    JTextArea ta;

    Socket socket=new Socket("localhost",9000);//连接服务器需要的端口号9000

    public client() throws IOException {
        jf=new JFrame("客户端");
        jLabel=new JLabel("用户名");
        tf1=new JTextField(15);
        tf2=new JTextField(35);
        start=new JButton("连接");
        send=new JButton("发送");
        ta=new JTextArea(40,40);
        init();//调用方法实现界面
    }

    public void init(){
        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        JPanel jp3=new JPanel();

        ta.setEditable(false);//让文本框不能输入，只是显示出来
        send.setEnabled(false);//设置发送按钮，不能按，因为还没有连接上服务器

        jp1.add(jLabel);
        jp1.add(tf1);
        jp1.add(start);



        jp2.add(ta);
        jp2.add(new JScrollPane(ta,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));//设置文本区域的滚动条

        jp3.add(tf2);
        jp3.add(send);

        jf.add(jp1, BorderLayout.NORTH);//设置布局
        jf.add(jp2,BorderLayout.CENTER);
        jf.add(jp3,BorderLayout.SOUTH);

        jf.setVisible(true);
        jf.setLocationRelativeTo(null);
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        handler();//添加按钮监听
        new CilThr(socket,ta).start();//接收其他客户端发来的信息

    }

    private void handler() {
        start.addActionListener(new AbstractAction() {//连接
            @Override
            public void actionPerformed(ActionEvent e) {
                //发起连接
                String user= tf1.getText();//获取用户名
                System.out.println(user+"发起连接请求");

                try {
                    OutputStream outputStream=socket.getOutputStream();//字节输出流
                    DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
                    dout.writeUTF(user+" 加入聊天");//.writeUTF里的内容输出到文本里
                    ta.append(user+" 加入聊天"+"\n");//添加到显示的文本区域
                    send.setEnabled(true);//再设置“发送”按钮可以被点击

                } catch (IOException ex) {
                    ex.printStackTrace();
                }


                //接收
            }
        });

        send.addActionListener(new AbstractAction() {//“发送”按钮
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    OutputStream outputStream=socket.getOutputStream();//字节输出流
                    DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流

                    String text=tf2.getText();//获取待发送文本框的内容

                    dout.writeUTF(text);//输出到文件

                    ta.append("我说： "+text+"\n");//添加到显示的文本区域
                    tf2.setText("");//设置下一次需要输入文本框的内容为空
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) throws IOException {
        new client(); //调用构造方法 出界面

    }
}
