package com.library.dao;

import com.library.model.Book;
import com.library.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean addBook(Book b) throws SQLException {
        String sql = "INSERT INTO books (bname, author, `type`, price) VALUES (?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getBname());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getType());
            ps.setFloat(4, b.getPrice());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, bname, author, `type`, price FROM books ORDER BY id")) {
            while (rs.next()) {
                list.add(map(rs));
            }
        }
        return list;
    }

    public Book getBook(int id) throws SQLException {
        String sql = "SELECT id, bname, author, `type`, price FROM books WHERE id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public boolean updateBook(Book b) throws SQLException {
        String sql = "UPDATE books SET bname=?, author=?, `type`=?, price=? WHERE id=?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getBname());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getType());
            ps.setFloat(4, b.getPrice());
            ps.setInt(5, b.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int id) throws SQLException {
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM books WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    private Book map(ResultSet rs) throws SQLException {
        return new Book(rs.getInt("id"), rs.getString("bname"), rs.getString("author"),
                rs.getString("type"), rs.getFloat("price"));
    }
}
