package socket.client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);


        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();


        outputStream.write(3);
        int res = inputStream.read(); // une instruction bloquante
        System.out.println("je recois " + res);
        inputStream.close();
        outputStream.close();
        socket.close();

    }
}
