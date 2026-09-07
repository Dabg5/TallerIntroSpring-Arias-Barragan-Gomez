package com.example.discography.servlet;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.service.ArtistDetails;
import com.example.discography.service.ArtistService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ArtistServlet extends HttpServlet {

    private ArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        artistService = WebApplicationContextUtils.getWebApplicationContext(getServletContext())
                .getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/artists/create")) {
            showCreateForm(request, response);
        } else if (path.equals("/artists/search")) {
            showSearchForm(request, response);
        } else if (path.equals("/artists/delete")) {
            showDeleteForm(request, response);
        } else {
            showArtists(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        if (path.equals("/artists/create")) {
            createArtist(request, response);
        } else if (path.equals("/artists/search")) {
            searchArtist(request, response);
        } else if (path.equals("/artists/delete")) {
            deleteArtist(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void showArtists(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Artists", request.getContextPath());
        out.println("<ul>");
        for (Artist artist : artistService.findAll()) {
            out.println("<li>" + artist.getId() + " - " + HtmlPage.escape(artist.getName())
                    + " (" + HtmlPage.escape(artist.getNationality()) + ")</li>");
        }
        out.println("</ul>");
        HtmlPage.end(out);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Create Artist", request.getContextPath());
        out.println("<form method='post' action='create'>");
        out.println("<label>Name <input type='text' name='name' required></label><br>");
        out.println("<label>Nationality <input type='text' name='nationality' required></label><br>");
        out.println("<button type='submit'>Create Artist</button></form>");
        HtmlPage.end(out);
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Search Artist", request.getContextPath());
        out.println("<form method='post' action='search'>");
        out.println("<label>Name <input type='text' name='name' required></label>");
        out.println("<button type='submit'>Search</button></form>");
        HtmlPage.end(out);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Delete Artist", request.getContextPath());
        out.println("<form method='post' action='delete'>");
        out.println("<label>Artist ID <input type='number' name='id' min='1' required></label>");
        out.println("<button type='submit'>Delete Artist</button></form>");
        HtmlPage.end(out);
    }

    private void createArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            artistService.create(request.getParameter("name"), request.getParameter("nationality"));
            response.sendRedirect(request.getContextPath() + "/artists");
        } catch (IllegalArgumentException exception) {
            showError(request, response, exception.getMessage(), "/artists/create");
        }
    }

    private void searchArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            ArtistDetails details = artistService.findByNameWithTracks(request.getParameter("name"));
            PrintWriter out = prepareResponse(response);
            HtmlPage.start(out, "Artist Search Result", request.getContextPath());
            if (details == null) {
                out.println("<p>No artist was found.</p>");
            } else {
                Artist artist = details.artist();
                out.println("<p><strong>ID:</strong> " + artist.getId() + "<br>");
                out.println("<strong>Name:</strong> " + HtmlPage.escape(artist.getName()) + "<br>");
                out.println("<strong>Nationality:</strong> " + HtmlPage.escape(artist.getNationality()) + "</p>");
                out.println("<h2>Tracks</h2><ul>");
                for (Track track : details.tracks()) {
                    out.println("<li>" + HtmlPage.escape(track.getTitle()) + " - "
                            + HtmlPage.escape(track.getAlbumTitle()) + " (" + HtmlPage.escape(track.getGenre()) + ")</li>");
                }
                out.println("</ul>");
            }
            HtmlPage.end(out);
        } catch (IllegalArgumentException exception) {
            showError(request, response, exception.getMessage(), "/artists/search");
        }
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (!artistService.deleteById(id)) {
                showError(request, response, "Artist not found.", "/artists/delete");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/artists");
        } catch (IllegalArgumentException exception) {
            showError(request, response, "Artist ID must be a number.", "/artists/delete");
        }
    }

    private PrintWriter prepareResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        return response.getWriter();
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message, String backPath)
            throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Request Error", request.getContextPath());
        out.println("<p>" + HtmlPage.escape(message) + "</p>");
        out.println("<p><a href='" + request.getContextPath() + backPath + "'>Back</a></p>");
        HtmlPage.end(out);
    }
}
