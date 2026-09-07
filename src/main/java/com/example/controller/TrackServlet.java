package com.example.controller;

import com.example.model.Artist;
import com.example.model.Track;
import com.example.service.ArtistService;
import com.example.service.TrackService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collection;

@WebServlet("/tracks")
public class TrackServlet extends HttpServlet {
    private TrackService trackService;
    private ArtistService artistService;

    @Override
    public void init() throws ServletException {

        ApplicationContext context =
                (ApplicationContext) getServletContext()
                        .getAttribute("springContext");

        trackService = context.getBean(TrackService.class);
        artistService = context.getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        listTracks(response);
    }

    private void listTracks(HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Tracks</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Registered Tracks</h1>");

        out.println("<ul>");

        for (Track track : trackService.getAllTracks()) {

            out.println("<li>");

            out.println("<strong>"
                    + track.getTitle()
                    + "</strong>");

            out.println(" | Genre: "
                    + track.getGenre());

            out.println(" | Duration: "
                    + track.getDuration());

            out.println(" | Album: "
                    + track.getAlbumTitle());

            out.println(" | Artists: ");

            for (Artist artist : track.getArtists()) {

                out.println(artist.getName() + " ");
            }

            out.println("</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTrack(request, response);
        }
        else if ("delete".equals(action)) {
            deleteTrack(request, response);
        }
    }

    private void createTrack(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {

        Integer id = Integer.parseInt(
                request.getParameter("id")
        );

        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String duration = request.getParameter("duration");
        String albumTitle = request.getParameter("albumTitle");

        String[] artistIdValues =
                request.getParameterValues("artistIds");

        Collection<Integer> artistIds = new ArrayList<>();

        if (artistIdValues != null) {

            for (String value : artistIdValues) {

                artistIds.add(
                        Integer.parseInt(value)
                );
            }
        }

        Track track = new Track(
                id,
                title,
                genre,
                duration,
                albumTitle
        );

        trackService.createTrack(
                track,
                artistIds
        );

        response.sendRedirect(
                request.getContextPath() + "/tracks"
        );
    }

    private void deleteTrack(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {

        Integer id = Integer.parseInt(
                request.getParameter("id")
        );

        trackService.deleteTrack(id);

        response.sendRedirect(
                request.getContextPath() + "/tracks"
        );
    }
}
