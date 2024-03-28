package org.example;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try {
            // Create a socket
            Socket socket = new Socket("localhost", 5000);

            // Create an object output stream and input stream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            System.out.println("Conexão estabelecida com o Servidor.");
            System.out.println("Digite o id do produto que deseja obter informações: ");

            int idProduto = scanner.nextInt();

            // Send the ID of the product to the server
            objectOutputStream.writeInt(idProduto);
            objectOutputStream.flush();

            // Read the product object from the server
            ProdutoCS produto = (ProdutoCS) objectInputStream.readObject();

            // Display the product information
            if (produto != null) {
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Preço: " + produto.getPreco());
            } else {
                System.out.println("Produto não encontrado com o ID informado.");
            }

            // Close the streams and the socket
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Erro ao conectar ao servidor: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro ao converter o objeto lido do servidor: " + ex.getMessage());
        }
    }
}