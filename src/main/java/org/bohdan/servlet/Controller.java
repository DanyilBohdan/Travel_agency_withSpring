package org.bohdan.servlet;

import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DBManager;
import org.bohdan.db.entity.Tour;
import org.bohdan.db.entity.TypeTour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //ArrayList<Tour> tours = (ArrayList<Tour>) new TourDao().findAll();
        req.setAttribute("name", new TourDao().findEntityById(1).getName());
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
