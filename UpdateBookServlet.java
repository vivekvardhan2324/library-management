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

@WebServlet("/update")
public class UpdateBookServlet extends HttpServlet {
    private final BookDAO dao = new BookDAO();

    /** Shows the edit form pre-filled with the book's current details. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String ctx = req.getContextPath();
        try {
            Book b = dao.getBook(Integer.parseInt(req.getParameter("id")));
            if (b == null) {
                BookForm.showMessage(out, ctx, "Update Book", "Book not found.", true);
                return;
            }
            Html.header(out, ctx, "Update Book");
            out.println("<form class=\"card\" method=\"post\" action=\"" + ctx + "/update\">");
            out.println("<input type=\"hidden\" name=\"id\" value=\"" + b.getId() + "\">");
            out.println("<label>Book Name<input type=\"text\" name=\"bname\" maxlength=\"20\" required value=\""
                    + Html.esc(b.getBname()) + "\"></label>");
            out.println("<label>Author<input type=\"text\" name=\"author\" maxlength=\"20\" required value=\""
                    + Html.esc(b.getAuthor()) + "\"></label>");
            out.println("<label>Type<input type=\"text\" name=\"type\" maxlength=\"20\" required value=\""
                    + Html.esc(b.getType()) + "\"></label>");
            out.println("<label>Price<input type=\"number\" name=\"price\" step=\"0.01\" min=\"0\" required value=\""
                    + b.getPrice() + "\"></label>");
            out.println("<button class=\"btn\" type=\"submit\">Update Book</button> "
                    + "<a class=\"btn secondary\" href=\"" + ctx + "/books\">Cancel</a>");
            out.println("</form>");
            Html.footer(out);
        } catch (NumberFormatException e) {
            BookForm.showMessage(out, ctx, "Update Book", "Invalid book id.", true);
        } catch (Exception e) {
            BookForm.showMessage(out, ctx, "Update Book", "Database error: " + e.getMessage(), true);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String ctx = req.getContextPath();
        try {
            Book b = BookForm.parse(req);
            String error = BookForm.validate(b);
            if (error != null) {
                BookForm.showMessage(resp.getWriter(), ctx, "Update Book", error, true);
                return;
            }
            if (dao.updateBook(b)) {
                resp.sendRedirect(ctx + "/books?msg=updated");
            } else {
                BookForm.showMessage(resp.getWriter(), ctx, "Update Book", "Book not found.", true);
            }
        } catch (NumberFormatException e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Update Book", "Invalid id or price.", true);
        } catch (Exception e) {
            BookForm.showMessage(resp.getWriter(), ctx, "Update Book", "Database error: " + e.getMessage(), true);
        }
    }
}
