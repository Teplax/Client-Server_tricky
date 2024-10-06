package org.example;

import com.sun.source.tree.WhileLoopTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {
        //Запускаем сервер на сокете с портом 8080
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Сервер запущен");
            while (true){
                try(Socket clientSocket = serverSocket.accept();//Принимаем запрос на подключение от клиента
                    //Создаём поток для ответа клиенту с данными о его сокете
                    PrintWriter printWriter= new PrintWriter(clientSocket.getOutputStream(), true);
                    //Создаём поток для чтения сообщения от клиента:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ){
                    String [] serverRequests={"Write your name:","Are you child? (yes/no)"};
                    String [] infoFromClient = new String[3];
                    int i=0;
                    while (i<2){
                        printWriter.println(serverRequests[i]);
                        infoFromClient[i] = bufferedReader.readLine();
                        System.out.println("Client's message: "+ infoFromClient[i++]);
                    }
                    if(infoFromClient[1].equalsIgnoreCase("yes")){
                        printWriter.printf("Welcome to the kids area, %s! Let's play!\n",infoFromClient[0]);
                    }
                    else printWriter.printf("Welcome to the adult zone, %s! Have a good rest, or a good working day!\n",infoFromClient[0]);
                }
            }

        }
        catch(IOException e){
            throw new RuntimeException();
        }
    }
}
