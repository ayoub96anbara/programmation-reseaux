package real.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMT extends Thread {
    private boolean isActive = true;
    private int nombreClient;

    public static void main(String[] args) {
        new ServeurMT().start();  // run server
        System.out.println("running server");
        System.out.println("l'execution de l'application est continue....");
    }

    @Override
    public void run() {


        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            while (isActive) {
                Socket socket = serverSocket.accept(); // bloquante, il attent qu'un client puisse se connecte
                nombreClient++;
                new Conversation(socket, nombreClient).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}

class Conversation extends Thread {
    private Socket socket;
    private int numClient;

    public Conversation(Socket socket, int numClient) {
        this.socket = socket;
        this.numClient = numClient;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();

            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader buffered = new BufferedReader(reader);


            PrintWriter printWriter = new PrintWriter(output, true);
            System.out.println("connexion client numero: " + numClient);

            String IP = socket.getInetAddress().getHostAddress();
            printWriter.println("welcome you are client num: " + numClient + " IP: " + IP);

            while (true) {
                String req = buffered.readLine();
                System.out.println("le client " + IP + " a envoye une requete " + req);
                printWriter.println("length of request: " + req.length());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
