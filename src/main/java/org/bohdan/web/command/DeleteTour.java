package org.bohdan.web.command;

import org.apache.log4j.Logger;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class DeleteTour extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DeleteTour.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean check = new TourDao().delete(id);
            logger.info("log: delete Tour = " + check);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (Exception ex) {
            getServletContext().getRequestDispatcher(Path.ERROR_PAGE).forward(req, resp);
        }
    }
}
