package real.server.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerMTChat extends Thread {
    private boolean isActive = true;
    private int nombreClient;
    List<Conversation> clients = new ArrayList<>();


    String message;


    public static void main(String[] args) {
        new ServerMTChat().start();  // run server
        System.out.println("running server");
        System.out.println("l'execution de l'application est continue....");
    }

    ServerSocket serverSocket;

    @Override
    public void run() {


        try {
            serverSocket = new ServerSocket(1234);

            while (isActive) {
                Socket socket = serverSocket.accept(); // bloquante, il attent qu'un client puisse se connecte

                nombreClient++;
                Conversation client = new Conversation(socket, nombreClient);
                clients.add(client);
                client.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    void broadcastMsg(String msg, Socket socketEmeteur) {
        System.out.println(clients.size());
        int numClient;
        int index=msg.indexOf("=>");
        if (index!=-1){
            String ss=msg.substring(index);
            String w= msg.replaceAll(ss,"");
             numClient=Integer.parseInt(w);
        }
        else {
            return;
        }

        clients.forEach(client -> {
            try {
                Socket socket = client.socket;
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream, true);

                if (socket != socketEmeteur && client.numClient==numClient) {
                    printWriter.println(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
                InputStream input = this.socket.getInputStream();
                InputStreamReader reader = new InputStreamReader(input);
                BufferedReader buffered = new BufferedReader(reader);


                OutputStream output = this.socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(output, true);

                System.out.println("connexion client numero: " + numClient);

                String IP = socket.getInetAddress().getHostAddress();
                //   printWriter.println("welcome you are client num: " + numClient + " IP: " + IP);
                //  System.out.println(buffered.readLine());
                //   System.out.println(input.read());
                while (true) {

                    message = buffered.readLine();
                    System.out.println(message);


                    broadcastMsg(message, socket);
                    //  System.out.println("le client " + IP + " a envoye une requete " + req);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
