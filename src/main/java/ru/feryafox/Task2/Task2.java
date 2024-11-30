package ru.feryafox.Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String urlString = null;
        boolean valid = false;

        while (!valid) {
            System.out.print("Введите URL ресурса: ");
            urlString = scanner.nextLine();

            try {
                URL url = new URL(urlString);
                // Проверяем доступность ресурса
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000); // установка таймаута соединения
                connection.setReadTimeout(5000);    // установка таймаута чтения

                int status = connection.getResponseCode();
                if (status == HttpURLConnection.HTTP_OK) {
                    // Если соединение успешно, читаем содержимое
                    readContent(url);
                    valid = true; // Выход из цикла
                } else {
                    System.out.println("Не удалось получить доступ к ресурсу. Код ответа: " + status);
                }
            } catch (MalformedURLException e) {
                System.out.println("Неправильный формат URL. Пожалуйста, попробуйте снова.");
            } catch (IOException e) {
                System.out.println("Ошибка при доступе к ресурсу: " + e.getMessage());
            }
        }

        scanner.close();
    }


    public static void readContent(URL url) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            System.out.println("Содержимое ресурса:");
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении содержимого: " + e.getMessage());
        }
    }
}
