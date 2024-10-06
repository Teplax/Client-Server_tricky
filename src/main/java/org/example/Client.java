package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //Запускаем клиентский сокет и подключаем его к порту сервера
        try(Socket clientSocket = new Socket("netology.homework", Server.PORT);
            //Создаём поток для отправки сообщения на сервер
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            //Создаём поток для приёма отета сервера
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            Scanner scanner= new Scanner(System.in);
            String answer = "";
            while (true){
                System.out.println(reader.readLine());
                answer=scanner.nextLine();
                writer.println(answer);
            }
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }
}
