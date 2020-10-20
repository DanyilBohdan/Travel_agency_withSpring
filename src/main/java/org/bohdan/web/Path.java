package org.bohdan.web;

public final class Path {


    public static final String PAGE_MAIN = "/main.jsp";
    public static final String PAGE_LOGIN = "/login.jsp";
    public static final String PAGE_REGISTER_USER = "/jsp/common/register.jsp";
    public static final String PAGE_REGISTER_USER_REDIRECT = "redirect:/controller?command=registerUser";
    public static final String PAGE_VIEW_TOUR = "/jsp/common/viewTourCommon.jsp";
    public static final String PAGE_EDIT_ACCOUNT = "/jsp/common/accountEdit.jsp";

    //admin
    public static final String LIST_TOURS_ADMIN = "/jsp/admin/listToursAdmin.jsp";
    public static final String LIST_USERS_ADMIN = "/jsp/admin/listUsersAdmin.jsp";
    public static final String LIST_ORDERS_ADMIN = "/jsp/admin/listOrdersAdmin.jsp";
    public static final String LIST_COUNTRY_ADMIN = "/jsp/admin/listCountry.jsp";
    public static final String LIST_TYPE_ADMIN = "/jsp/admin/listTypeTour.jsp";
    public static final String CREATE_TOUR = "/jsp/admin/createTour.jsp";
    public static final String EDIT_TOUR = "/jsp/admin/editTour.jsp";
    public static final String ACCOUNT_ADMIN = "/jsp/admin/accountAdmin.jsp";
    public static final String ACCOUNT_MANAGER = "/jsp/admin/accountManager.jsp";

    public static final String COMMAND_CREATE_TOUR= "redirect:/controller?command=getCreateTour";
    public static final String COMMAND_LIST_ORDERS = "redirect:/controller?command=listOrders";
    public static final String COMMAND_ACCOUNT_ADMIN = "redirect:/controller?command=accountAdmin";
    public static final String COMMAND_ACCOUNT_MANAGER = "redirect:/controller?command=accountManager";
    public static final String COMMAND_TOURS_ADMIN = "redirect:/controller?command=listTours";
    public static final String COMMAND_LIST_USER = "redirect:/controller?command=listUsers";
    public static final String COMMAND_LIST_TYPE = "redirect:/controller?command=listType";
    public static final String COMMAND_LIST_COUNTRY = "redirect:/controller?command=listCountry";

    public static final String COMMAND_ACCOUNT = "redirect:/controller?command=accountUser";
    public static final String ACCOUNT_USER = "/jsp/user/accountUser.jsp";
    public static final String REGISTER_TOUR = "/jsp/user/registerTour.jsp";

    public static final String ERROR_PAGE = "/jsp/error/error_page.jsp";
    public static final String REGISTER_CHECK_REDIRECT = "redirect:/controller?command=registerCheck";
    public static final String PAGE_REGISTER_CHECK = "/jsp/registerCheck.jsp";
}
