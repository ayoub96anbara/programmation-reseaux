package real.server.game;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Gamer2 {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1234);
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter= new PrintWriter(outputStream);


        System.out.println(bufferedReader.readLine());


     //   printWriter.println("sss");
//        outputStream.write(2);

        Scanner scanner = new Scanner(System.in);
        boolean _continue = true;
        while (_continue) {
            int entree = scanner.nextInt();
            System.out.println(entree);
            outputStream.write(entree);
            String response=bufferedReader.readLine();
            System.out.println(response);
            _continue = !response.equals("end");
        }
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
    }
}
