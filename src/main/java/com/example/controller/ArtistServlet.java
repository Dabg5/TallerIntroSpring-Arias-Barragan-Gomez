package com.example.controller;

import com.example.model.Artist;
import com.example.service.ArtistService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/artists")
public class ArtistServlet extends HttpServlet {
    private ArtistService artistService;

    @Override
    public void init() throws ServletException {

        ApplicationContext context =
                (ApplicationContext) getServletContext()
                        .getAttribute("springContext");

        artistService = context.getBean(ArtistService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            listArtists(response);
        }
        else if (action.equals("search")) {
            searchArtist(request, response);
        }
    }

    private void listArtists(HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Artists</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Registered Artists</h1>");

        out.println("<ul>");

        for (Artist artist : artistService.getAllArtists()) {

            out.println("<li>");
            out.println(
                    artist.getId()
                            + " - "
                            + artist.getName()
                            + " - "
                            + artist.getNationality()
            );
            out.println("</li>");
        }

        out.println("</ul>");

        out.println("</body>");
        out.println("</html>");
    }

    private void searchArtist(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        Artist artist = artistService.findArtistByName(name);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Search Artist</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Artist Search</h1>");

        if (artist == null) {

            out.println("<p>Artist not found.</p>");

        } else {

            out.println("<h2>" + artist.getName() + "</h2>");

            out.println("<p>ID: " + artist.getId() + "</p>");
            out.println("<p>Nationality: "
                    + artist.getNationality()
                    + "</p>");

            out.println("<h3>Tracks</h3>");

            out.println("<ul>");

            for (var track : artist.getTracks()) {

                out.println("<li>");
                out.println(
                        track.getTitle()
                                + " - "
                                + track.getGenre()
                                + " - "
                                + track.getDuration()
                                + " - "
                                + track.getAlbumTitle()
                );
                out.println("</li>");
            }

            out.println("</ul>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if (action.equals("create")) {
            createArtist(request, response);
        }
        else if (action.equals("delete")) {
            deleteArtist(request, response);
        }
    }

    private void createArtist(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {

        Integer id = Integer.parseInt(
                request.getParameter("id")
        );

        String name = request.getParameter("name");

        String nationality = request.getParameter("nationality");

        Artist artist = new Artist(
                id,
                name,
                nationality
        );

        artistService.createArtist(artist);

        response.sendRedirect(
                request.getContextPath() + "/artists"
        );
    }

    private void deleteArtist(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException {

        Integer id = Integer.parseInt(
                request.getParameter("id")
        );

        artistService.deleteArtist(id);

        response.sendRedirect(
                request.getContextPath() + "/artists"
        );
    }
}
