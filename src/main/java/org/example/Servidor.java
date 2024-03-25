package org.example;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            List<Produto> produtos = new ArrayList<>();
            produtos.add(new Produto(1, "Produto 1", 10.0, "2022-12-31", "G", "Descrição do produto 1"));
            produtos.add(new Produto(2, "Produto 2", 20.0, "2023-12-31", "M", "Descrição do produto 2"));

            while (true) {
                Socket socket = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                int idProduto = (int) objectInputStream.readObject();
                Produto produto = null;

                for (Produto p : produtos) {
                    if (p.getId() == idProduto) {
                        produto = p;
                        break;
                    }
                }

                objectOutputStream.writeObject(produto);
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
