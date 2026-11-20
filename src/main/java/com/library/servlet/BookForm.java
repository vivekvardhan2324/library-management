package com.library.servlet;

import com.library.model.Book;
import com.library.util.Html;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;

/** Shared helpers for reading/validating the book form and rendering messages. */
final class BookForm {
    private BookForm() {}

    static Book parse(HttpServletRequest req) {
        Book b = new Book();
        String id = req.getParameter("id");
        if (id != null && !id.trim().isEmpty()) b.setId(Integer.parseInt(id.trim()));
        b.setBname(trim(req.getParameter("bname")));
        b.setAuthor(trim(req.getParameter("author")));
        b.setType(trim(req.getParameter("type")));
        b.setPrice(Float.parseFloat(trim(req.getParameter("price"))));
        return b;
    }

    static String validate(Book b) {
        if (b.getBname().isEmpty() || b.getAuthor().isEmpty() || b.getType().isEmpty())
            return "All fields are required.";
        if (b.getBname().length() > 20 || b.getAuthor().length() > 20 || b.getType().length() > 20)
            return "Name, author and type must be at most 20 characters.";
        if (b.getPrice() < 0 || Float.isNaN(b.getPrice()) || Float.isInfinite(b.getPrice()))
            return "Price must be a valid non-negative number.";
        return null;
    }

    static void showMessage(PrintWriter out, String ctx, String title, String msg, boolean error) {
        Html.header(out, ctx, title);
        out.println("<p class=\"alert " + (error ? "error" : "success") + "\">" + Html.esc(msg) + "</p>");
        out.println("<a class=\"btn\" href=\"javascript:history.back()\">Go Back</a>");
        Html.footer(out);
    }

    private static String trim(String s) {
        return s == null ? "" : s.trim();
    }
}
