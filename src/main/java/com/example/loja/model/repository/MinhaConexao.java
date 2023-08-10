package com.example.loja.model.repository;

import com.example.loja.ConexaoMysql;

import java.sql.Connection;

public class MinhaConexao{

    public static Connection conexao(){
        ConexaoJDBC conexao = new ConexaoMysql();
        return conexao.criarConexao();
    }

}