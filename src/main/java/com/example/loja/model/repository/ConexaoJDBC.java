package com.example.loja.model.repository;

import java.sql.Connection;

public interface ConexaoJDBC {

    public Connection criarConexao();

}
