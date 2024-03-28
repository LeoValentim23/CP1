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

public class Servidor{

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            List<ProdutoCS> produtoCSs = new ArrayList<>();



            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Servidor ativo.");
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                int idProduto = objectInputStream.readInt();
                ProdutoCS produto = null;

                EntityManager entityManager = null;
                try {
                    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CLIENTE_ORACLE");
                    entityManager = entityManagerFactory.createEntityManager();

                    produto = entityManager.find(ProdutoCS.class, idProduto);
                    if (produto != null) {
                        objectOutputStream.writeObject(produto);
                    } else {
                        objectOutputStream.writeObject(null);
                    }
                } catch (Exception ex) {
                    objectOutputStream.writeObject(null);
                } finally {
                    entityManager.close();
                }

                objectOutputStream.flush();
                objectOutputStream.close();
                objectInputStream.close();
                socket.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao iniciar o servidor: " + e.getMessage());
        }
    }
}