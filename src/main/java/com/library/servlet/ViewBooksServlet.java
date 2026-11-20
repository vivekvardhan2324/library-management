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
import java.util.List;

@WebServlet("/books")
public class ViewBooksServlet extends HttpServlet {
    private final BookDAO dao = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String ctx = req.getContextPath();
        Html.header(out, ctx, "All Books");

        String msg = req.getParameter("msg");
        if ("added".equals(msg)) out.println("<p class=\"alert success\">Book added successfully.</p>");
        else if ("updated".equals(msg)) out.println("<p class=\"alert success\">Book updated successfully.</p>");
        else if ("deleted".equals(msg)) out.println("<p class=\"alert success\">Book deleted successfully.</p>");

        try {
            List<Book> books = dao.getAllBooks();
            if (books.isEmpty()) {
                out.println("<p>No books found. <a href=\"" + ctx + "/add-book.html\">Add one</a>.</p>");
            } else {
                out.println("<table><thead><tr><th>ID</th><th>Book Name</th><th>Author</th><th>Type</th>"
                        + "<th>Price</th><th>Actions</th></tr></thead><tbody>");
                for (Book b : books) {
                    out.println("<tr><td>" + b.getId() + "</td><td>" + Html.esc(b.getBname()) + "</td><td>"
                            + Html.esc(b.getAuthor()) + "</td><td>" + Html.esc(b.getType()) + "</td><td>"
                            + String.format("%.2f", b.getPrice()) + "</td><td class=\"actions\">"
                            + "<a class=\"btn small\" href=\"" + ctx + "/update?id=" + b.getId() + "\">Edit</a>"
                            + "<form method=\"post\" action=\"" + ctx + "/delete\" "
                            + "onsubmit=\"return confirm('Delete this book?');\">"
                            + "<input type=\"hidden\" name=\"id\" value=\"" + b.getId() + "\">"
                            + "<button class=\"btn small danger\" type=\"submit\">Delete</button></form></td></tr>");
                }
                out.println("</tbody></table>");
            }
        } catch (Exception e) {
            out.println("<p class=\"alert error\">Database error: " + Html.esc(e.getMessage()) + "</p>");
        }
        Html.footer(out);
    }
}
