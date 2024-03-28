package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private static int exceptionCount = 0;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            List<ProdutoCS> produtoCSs = new ArrayList<>();

            while (true) {
                Socket socket = null;

                try {
                    System.out.println("Servidor ativo.");

                    socket = serverSocket.accept();

                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                    int idProduto = objectInputStream.readInt();

                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
                    EntityManager entityManager = entityManagerFactory.createEntityManager();

                    ProdutoCS produto = entityManager.find(ProdutoCS.class, idProduto);

                    if (produto != null) {
                        objectOutputStream.writeObject(produto);
                    } else {
                        objectOutputStream.writeObject(null);
                    }

                    objectOutputStream.flush();

                    socket.close();

                    objectOutputStream.close();
                    objectInputStream.close();

                    exceptionCount = 0;

                } catch (IOException ex) {
                    System.out.println("Erro (IOException) ao conectar ao cliente: " + ex.getMessage());
                    exceptionCount++;
                    if (exceptionCount >= 5) {
                        break;
                    }

                } catch (Exception ex) {
                    System.out.println("Erro (Exception) ao conectar ao cliente: " + ex.getMessage());
                    exceptionCount++;
                    if (exceptionCount >= 5) {
                        break;
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}