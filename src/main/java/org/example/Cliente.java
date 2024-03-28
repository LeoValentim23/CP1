package org.example;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

    public class Cliente {
        public static void main(String[] args) {
            try {
                Socket socket = new Socket("localhost", 5000);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

                Scanner scanner = new Scanner(System.in);
                System.out.println("Conexão estabelecida com o Servidor.");
                System.out.println("Digite o id do produto que deseja obter informações: ");
                int idProduto = scanner.nextInt();

                objectOutputStream.writeObject(idProduto);
                objectOutputStream.flush();

                Produto produto = (Produto) objectInputStream.readObject();

                if (produto != null) {
                    System.out.println("Nome: " + produto.getNome());
                    System.out.println("Preço: " + produto.getPreco());
                    System.out.println("Validade: " + produto.getValidade());
                    System.out.println("Tamanho: " + produto.getTamanho());
                    System.out.println("Descrição: " + produto.getDescricao());
                } else {
                    System.out.println("Não foi possível obter as informações do produto.");
                }

                objectOutputStream.close();
                objectInputStream.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Erro ao conectar ao servidor: " + e.getMessage());
            }
        }
    }

