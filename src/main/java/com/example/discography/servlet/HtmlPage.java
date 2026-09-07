package com.example.discography.servlet;

import java.io.PrintWriter;

final class HtmlPage {

    private HtmlPage() {
    }

    static void start(PrintWriter out, String title, String contextPath) {
        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'><head><meta charset='UTF-8'><title>" + escape(title) + "</title></head><body>");
        out.println("<nav><a href='" + contextPath + "/artists'>Artists</a> | ");
        out.println("<a href='" + contextPath + "/artists/create'>Create Artist</a> | ");
        out.println("<a href='" + contextPath + "/artists/search'>Search Artist</a> | ");
        out.println("<a href='" + contextPath + "/artists/delete'>Delete Artist</a> | ");
        out.println("<a href='" + contextPath + "/tracks'>Tracks</a> | ");
        out.println("<a href='" + contextPath + "/tracks/create'>Create Track</a> | ");
        out.println("<a href='" + contextPath + "/tracks/delete'>Delete Track</a></nav><hr>");
        out.println("<h1>" + escape(title) + "</h1>");
    }

    static void end(PrintWriter out) {
        out.println("</body></html>");
    }

    static String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}
