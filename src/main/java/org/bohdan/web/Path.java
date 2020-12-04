package org.bohdan.web;

/**
 * Path holder (jsp pages, redirect commands).
 *
 * @author Bohdan Daniel
 *
 */

public final class Path {

    public static final String PAGE_MAIN = "main";
    public static final String PAGE_LOGIN = "login";
    public static final String PAGE_REGISTER_USER = "common/register";
    public static final String PAGE_VIEW_TOUR = "common/viewTourCommon";
    public static final String PAGE_EDIT_ACCOUNT = "common/accountEdit";

    public static final String LIST_TOURS_ADMIN = "admin/listToursAdmin";
    public static final String LIST_USERS_ADMIN = "admin/listUsersAdmin";
    public static final String LIST_ORDERS_ADMIN = "admin/listOrdersAdmin";
    public static final String LIST_COUNTRY_ADMIN = "admin/listCountry";
    public static final String LIST_TYPE_ADMIN = "admin/listTypeTour";
    public static final String CREATE_TOUR = "admin/createTour";
    public static final String EDIT_TOUR = "admin/editTour";
    public static final String ACCOUNT_ADMIN = "admin/accountAdmin";
    public static final String ACCOUNT_MANAGER = "admin/accountManager";
    public static final String ACCOUNT_USER = "user/accountUser";

    public static final String COMMAND_LIST_ORDERS = "redirect:/controller?command=listOrders";
    public static final String COMMAND_ACCOUNT_ADMIN = "redirect:/user/admin/account";
    public static final String COMMAND_ACCOUNT_MANAGER = "redirect:/user/manager/account";
    public static final String COMMAND_ACCOUNT = "redirect:/user/account";
    public static final String COMMAND_TOURS_ADMIN = "redirect:/tours/admin/view";
    public static final String COMMAND_LIST_USER = "redirect:/controller?command=listUsers";
    public static final String COMMAND_LIST_TYPE = "redirect:/controller?command=listType";
    public static final String COMMAND_LIST_COUNTRY = "redirect:/controller?command=listCountry";

    public static final String REGISTER_TOUR = "user/registerTour";

    public static final String ERROR_PAGE = "error/error_page";
    public static final String ERROR_EXCEPTION = "error/error_exception";
    public static final String REGISTER_CHECK_REDIRECT = "redirect:/user/registerCheck";
    public static final String PAGE_REGISTER_CHECK = "registerCheck";
}
