package socket.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(1234); // la creation d'un server
        Socket socket = serverSocket.accept();  //une instruction bloquante, attent un client

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();


        int i = inputStream.read(); // une instruction bloquante
        System.out.println("je recois " + i);

        int res = i * 5;
        outputStream.write(res);

        inputStream.close();
        outputStream.close();
        socket.close();


    }
}
