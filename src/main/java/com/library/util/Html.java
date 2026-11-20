package com.library.util;

import java.io.PrintWriter;

public class Html {

    public static String esc(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }

    public static void header(PrintWriter out, String ctx, String title) {
        out.println("<!DOCTYPE html><html><head><meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        out.println("<title>" + esc(title) + " - Library Management</title>");
        out.println("<link rel=\"stylesheet\" href=\"" + ctx + "/css/style.css\"></head><body>");
        out.println("<nav><span class=\"brand\">&#128218; Library Management</span>");
        out.println("<a href=\"" + ctx + "/index.html\">Home</a>");
        out.println("<a href=\"" + ctx + "/add-book.html\">Add Book</a>");
        out.println("<a href=\"" + ctx + "/books\">View Books</a></nav>");
        out.println("<main><h2>" + esc(title) + "</h2>");
    }

    public static void footer(PrintWriter out) {
        out.println("</main></body></html>");
    }
}
