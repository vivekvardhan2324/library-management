package com.library.servlet;

import com.library.dao.BookDAO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteBookServlet extends HttpServlet {
    private final BookDAO dao = new BookDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            if (dao.deleteBook(id)) {
                resp.sendRedirect(ctx + "/books?msg=deleted");
            } else {
                BookForm.showMessage(resp.getWriter(), ctx, "Delete Book", "Book not found.", true);
            }
        } catch (NumberFormatException e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Delete Book", "Invalid book id.", true);
        } catch (Exception e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Delete Book", "Database error: " + e.getMessage(), true);
        }
    }
}
