package org.bohdan.web.services;

import org.bohdan.model.User;
import org.bohdan.web.Path;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * View page Edit account command
 *
 * @author Bohdan Daniel
 */
public class GetEditAccount extends Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        request.setAttribute("user", user);

        return Path.PAGE_EDIT_ACCOUNT;
    }
}
