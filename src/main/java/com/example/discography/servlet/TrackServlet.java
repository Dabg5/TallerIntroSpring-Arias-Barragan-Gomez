package com.example.discography.servlet;

import com.example.discography.model.Artist;
import com.example.discography.model.Track;
import com.example.discography.service.ArtistService;
import com.example.discography.service.TrackService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet({"/tracks", "/tracks/create", "/tracks/delete"})
public class TrackServlet extends HttpServlet {

    private TrackService trackService;
    private ArtistService artistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        trackService = WebApplicationContextUtils.getWebApplicationContext(getServletContext())
                .getBean(TrackService.class);
        artistService = WebApplicationContextUtils.getWebApplicationContext(getServletContext())
                .getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getServletPath();
        if (path.equals("/tracks/create")) {
            showCreateForm(request, response);
        } else if (path.equals("/tracks/delete")) {
            showDeleteForm(request, response);
        } else {
            showTracks(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        if (request.getServletPath().equals("/tracks/create")) {
            createTrack(request, response);
        } else if (request.getServletPath().equals("/tracks/delete")) {
            deleteTrack(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }

    private void showTracks(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Tracks", request.getContextPath());
        out.println("<ul>");
        for (Track track : trackService.findAll()) {
            out.println("<li><strong>" + track.getId() + " - " + HtmlPage.escape(track.getTitle()) + "</strong><br>");
            out.println("Genre: " + HtmlPage.escape(track.getGenre()) + " | Duration: " + HtmlPage.escape(track.getDuration())
                    + " | Album: " + HtmlPage.escape(track.getAlbumTitle()) + "<br>");
            out.println("Artists: " + HtmlPage.escape(artistNames(track)) + "</li>");
        }
        out.println("</ul>");
        HtmlPage.end(out);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Create Track", request.getContextPath());
        out.println("<form method='post' action='create'>");
        out.println("<label>Title <input type='text' name='title' required></label><br>");
        out.println("<label>Genre <input type='text' name='genre' required></label><br>");
        out.println("<label>Duration <input type='text' name='duration' placeholder='3:30' required></label><br>");
        out.println("<label>Album title <input type='text' name='albumTitle' required></label><br>");
        out.println("<fieldset><legend>Artists</legend>");
        for (Artist artist : artistService.findAll()) {
            out.println("<label><input type='checkbox' name='artistId' value='" + artist.getId() + "'> "
                    + HtmlPage.escape(artist.getName()) + "</label><br>");
        }
        out.println("</fieldset><button type='submit'>Create Track</button></form>");
        HtmlPage.end(out);
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = prepareResponse(response);
        HtmlPage.start(out, "Delete Track", request.getContextPath());
        out.println("<form method='post' action='delete'>");
        out.println("<label>Track ID <input type='number' name='id' min='1' required></label>");
        out.println("<button type='submit'>Delete Track</button></form>");
        HtmlPage.end(out);
    }

    private void createTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            trackService.create(request.getParameter("title"), request.getParameter("genre"),
                    request.getParameter("duration"), request.getParameter("albumTitle"), selectedArtistIds(request));
            response.sendRedirect(request.getContextPath() + "/tracks");
        } catch (IllegalArgumentException exception) {
            showError(request, response, exception.getMessage(), "/tracks/create");
        }
    }

    private void deleteTrack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            if (!trackService.deleteById(id)) {
                showError(request, response, "Track not found.", "/tracks/delete");
                return;
            }
            response.sendRedirect(request.getContextPath() + "/tracks");
        } catch (IllegalArgumentException exception) {
            showError(request, response, "Track ID must be a number.", "/tracks/delete");
        }
    }

    private Set<Integer> selectedArtistIds(HttpServletRequest request) {
        Set<Integer> artistIds = new LinkedHashSet<>();
        String[] values = request.getParameterValues("artistId");
        if (values == null) {
            return artistIds;
        }
        for (String value : values) {
            artistIds.add(Integer.parseInt(value));
        }
        return artistIds;
    }

    private String artistNames(Track track) {
        StringBuilder names = new StringBuilder();
        for (Artist artist : artistService.findAll()) {
            if (track.getArtistIds().contains(artist.getId())) {
                if (names.length() > 0) {
                    names.append(", ");
                }
                names.append(artist.getName());
            }
        }
        return names.toString();
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
