package com.example.loja;

import com.example.loja.model.repository.ConexaoJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoMysql implements ConexaoJDBC {

    public static void main(String[] args) {

        //testar conexão
        System.out.println(new ConexaoMysql().criarConexao());

    }

    /**
     * método que vai retornar uma conexão
     * @return
     */
    @Override
    public Connection criarConexao(){
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/pw2";
            String usuario = "root";
            String senha = "102030";
            return DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConexaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}