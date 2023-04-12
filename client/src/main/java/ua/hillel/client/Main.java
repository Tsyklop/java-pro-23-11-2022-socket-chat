package ua.hillel.client;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 8080));

        Scanner scanner = new Scanner(System.in);

        try {

            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {

                try (is) {

                    while (true) {

                        String response = is.readUTF();

                        if (response != null) {

                            if ("bye".equals(response)) {
                                break;
                            }

                            System.out.println("Server: " + response);
                        }

                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }).start();

            while (true) {

                System.out.println("Enter message:");

                String message = scanner.nextLine();

                if ("exit".equals(message)) {
                    break;
                }

                if (message.startsWith("file:")) {

                    // file:C:\Users\tsykl\Desktop\file.txt

                    File file = new File(message.replace("file:", ""));

                    if (!file.exists() || !file.isFile()) {
                        System.out.println("Выберите файл");
                        continue;
                    }

                    os.writeUTF("file_start");

                    os.writeUTF("file_name");
                    os.writeUTF(file.getName());

                    os.writeUTF("file_size");
                    os.writeLong(file.length());

                    os.writeUTF("file_content");

                    try (FileInputStream fis = FileUtils.openInputStream(file)) {
                        IOUtils.copy(fis, os);
                    } finally {
                        System.out.println("Uploaded");
                    }

                    os.writeUTF("file_finish");

                } else {
                    os.writeUTF(message);
                }

                os.flush();

            }

        } finally {
            socket.close();
        }

    }

    //Mockito.when(socket.getInputStream(Mockito.any())).thenReturn(() -> new DataInputStream(new FileInputStream(new File(""))));

//                type: "message" | "file" | "file_content" | "system"
//                data: "jfsdhfjdsk"
//                data: name: ..., size: ...,


    /*public static interface MessageHandler<T> {

        MessageType getType();

        void handle(T message);

    }

    public static class HelloMessageHandler implements MessageHandler<HelloMessage> {
        public MessageType getType() {
            return MessageType.HELLO;
        }

        public void handle(HelloMessage message) {

        }

    }*/

}