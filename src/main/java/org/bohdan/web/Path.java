package org.bohdan.web;

public final class Path {


    public static final String PAGE_MAIN = "/main.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String ACCOUNT_USER = "/WEB-INF/jsp/common/accountUser.jsp";
    public static final String VIEW_TOUR_ID = "/WEB-INF/jsp/common/viewTourCommon.jsp";

    //admin
    public static final String LIST_TOURS_ADMIN = "/WEB-INF/jsp/admin/listToursAdmin.jsp";
    public static final String LIST_USERS_ADMIN = "/WEB-INF/jsp/admin/listUsersAdmin.jsp";
    public static final String CREATE_TOUR = "/WEB-INF/jsp/admin/createTour.jsp";
    public static final String EDIT_TOUR = "/WEB-INF/jsp/admin/editTour.jsp";
    public static final String ACCOUNT_ADMIN = "/WEB-INF/jsp/admin/accountAdmin.jsp";
    public static final String COMMAND_ACCOUNT_ADMIN = "/controller?command=accountAdmin";
    public static final String COMMAND_TOURS_ADMIN = "/controller?command=listTours";
    public static final String COMMAND_LIST_USER = "/controller?command=listUsers";
    public static final String COMMAND_EDIT_ADMIN = "/controller?command=editTour";
    public static final String COMMAND_CREATE_ADMIN = "/controller?command=createTour";


    public static final String ERROR_PAGE = "/WEB-INF/jsp/error/error_page.jsp";
    public static final String COMMAND_VIEW_TOURS = "/controller?command=viewTours";
    public static final String COMMAND_ACCOUNT = "/controller?command=accountUser";

    public static final String VIEW_TOURS = "viewTours";
    public static final String VIEW_TOUR = "viewTour";
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String AC_USER = "accountUser";
    public static final String AC_ADMIN = "accountAdmin";
    public static final String LIST_TOURS = "listTours";
    public static final String LIST_USERS = "listUsers";

    public static boolean LOGIN_CHECK = false;
}
