package com.library.servlet;

import com.library.dao.BookDAO;
import com.library.model.Book;
import com.library.util.Html;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddBookServlet extends HttpServlet {
    private final BookDAO dao = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        try {
            Book b = BookForm.parse(req);
            String error = BookForm.validate(b);
            if (error != null) {
                BookForm.showMessage(resp.getWriter(), ctx, "Add Book", error, true);
                return;
            }
            dao.addBook(b);
            resp.sendRedirect(ctx + "/books?msg=added");
        } catch (NumberFormatException e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Add Book", "Price must be a number.", true);
        } catch (Exception e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Add Book", "Database error: " + e.getMessage(), true);
        }
    }
}
