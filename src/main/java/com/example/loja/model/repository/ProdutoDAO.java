package com.example.loja.model.repository;

import com.example.loja.model.entity.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

    Connection con;

    public ProdutoDAO(){
        con = MinhaConexao.conexao();
    }

    public List<Produto> buscarProdutos() {
        try {
            String sql = "select * from tb_produto";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Produto> produtos = new ArrayList();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getLong("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
                produtos.add(p);
            }
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Produto buscarProduto(Long id) {
        try {
            String sql = "select * from tb_produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Produto p = new Produto();
            while (rs.next()) {
                p.setId(rs.getLong("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setValor(rs.getDouble("valor"));
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean remove(Long id) {
        try {
            String sql = "delete from tb_produto where id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean save(Produto produto) {
        try {
            String sql = "insert into tb_produto (descricao, valor) values (?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setLong(2, produto.getValor().longValue());

            if(ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean update(Produto produto) {
        try {
            String sql = "update tb_produto set descricao=? , valor=? where id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getDescricao());
            ps.setDouble(2, produto.getValor());
            ps.setLong(3, produto.getId());

            if (ps.executeUpdate()==1)
                return true;

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}