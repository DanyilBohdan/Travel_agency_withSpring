package org.bohdan.web.controllers;

import org.bohdan.model.Country;
import org.bohdan.model.Tour;
import org.bohdan.model.TypeTour;
import org.bohdan.model.general.ListBean;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.TourService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class TourControllerTest {

    private final TourService tourService = mock(TourService.class);

    private final HttpSession session = mock(HttpSession.class);

    private final HttpServletRequest request = mock(HttpServletRequest.class);

    private final TourController tourController = new TourController(tourService, session);

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(tourController).build();

    @Test
    public void viewListToursTest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("01/01/2021");

        TourView first = TourView.createTour("testName1", "testType1", "testCountry1",
                "testDesc1", 980, 4, 5, date, 4, 5);
        first.setId(1);

        TourView second = TourView.createTour("testName2", "testType2", "testCountry2",
                "testDesc2", 750, 2, 3, date, 1, 2);
        second.setId(2);

        ListBean countryFirst = ListBean.create(1, "testCountry1");
        ListBean countrySecond = ListBean.create(2, "testCountry2");
        ListBean typeFirst = ListBean.create(1, "testType1");
        ListBean typeSecond = ListBean.create(2, "testType2");

        String lang = "EN";
        when(this.tourService.viewTours(lang)).thenReturn(Arrays.asList(first, second));
        when(this.tourService.getListTypeTours(lang)).thenReturn(Arrays.asList(typeFirst, typeSecond));
        when(this.tourService.getListCountry(lang)).thenReturn(Arrays.asList(countryFirst, countrySecond));

        mockMvc.perform(get("/tours/view"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.PAGE_MAIN))
                .andExpect(forwardedUrl(Path.PAGE_MAIN))
                .andExpect(model().attribute("typeTourOut", hasSize(2)))
                .andExpect(model().attribute("countryOut", hasSize(2)))
                .andExpect(model().attribute("tours", hasSize(2)))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("id", is(first.getId())),
                                hasProperty("name", is(first.getName())),
                                hasProperty("price", is(first.getPrice()))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("id", is(first.getId())),
                                hasProperty("name", is(first.getName())),
                                hasProperty("price", is(first.getPrice()))
                        )
                )));
    }

    @Test
    public void viewListTourByIdTest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("01/01/2021");

        TourView first = TourView.createTour("testName1", "testType1", "testCountry1",
                "testDesc1", 980, 4, 5, date, 4, 5);
        first.setId(1);

        String lang = "EN";
        when(session.getAttribute("defLocale")).thenReturn(lang);

        when(request.getParameter("id")).thenReturn("1");

        when(this.tourService.viewTour(lang, 1)).thenReturn(first);

        mockMvc.perform(get("/tours/view/tour")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.PAGE_VIEW_TOUR))
                .andExpect(forwardedUrl(Path.PAGE_VIEW_TOUR))
                .andExpect(model().attribute("tour", hasProperty("id", is(first.getId()))))
                .andExpect(model().attribute("tour", hasProperty("name", is(first.getName()))))
                .andExpect(model().attribute("tour", hasProperty("price", is(first.getPrice()))));
    }

    @Test
    public void viewListToursForAdminTest() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("01/01/2021");

        TourView first = TourView.createTour("testName1", "testType1", "testCountry1",
                "testDesc1", 980, 4, 5, date, 4, 5);
        first.setId(1);

        TourView second = TourView.createTour("testName2", "testType2", "testCountry2",
                "testDesc2", 750, 2, 3, date, 1, 2);
        second.setId(2);

        ListBean countryFirst = ListBean.create(1, "testCountry1");
        ListBean countrySecond = ListBean.create(2, "testCountry2");
        ListBean typeFirst = ListBean.create(1, "testType1");
        ListBean typeSecond = ListBean.create(2, "testType2");

        String lang = "EN";
        when(this.tourService.viewToursForAdmin(lang)).thenReturn(Arrays.asList(first, second));
        when(this.tourService.getListTypeTours(lang)).thenReturn(Arrays.asList(typeFirst, typeSecond));
        when(this.tourService.getListCountry(lang)).thenReturn(Arrays.asList(countryFirst, countrySecond));

        mockMvc.perform(get("/tours/admin/view"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.LIST_TOURS_ADMIN))
                .andExpect(forwardedUrl(Path.LIST_TOURS_ADMIN))
                .andExpect(model().attribute("typeTourOut", hasSize(2)))
                .andExpect(model().attribute("countryOut", hasSize(2)))
                .andExpect(model().attribute("tours", hasSize(2)))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("id", is(first.getId())),
                                hasProperty("name", is(first.getName())),
                                hasProperty("price", is(first.getPrice()))
                        )
                )))
                .andExpect(model().attribute("tours", hasItem(
                        allOf(
                                hasProperty("id", is(first.getId())),
                                hasProperty("name", is(first.getName())),
                                hasProperty("price", is(first.getPrice()))
                        )
                )));
    }

    @Test
    public void createViewTourTest() throws Exception {

        Country countryFirst = Country.create("testCountry1", "тестСтрана1");
        countryFirst.setId(1);
        Country countrySecond = Country.create("testCountry2", "тестСтрана2");
        countrySecond.setId(2);
        TypeTour typeFirst = TypeTour.create("testType1", "тестТип1");
        typeFirst.setId(1);
        TypeTour typeSecond = TypeTour.create("testType2", "тестТип2");
        typeSecond.setId(2);

        List<Country> countries = Arrays.asList(countryFirst, countrySecond);
        List<TypeTour> typeTours = Arrays.asList(typeFirst, typeSecond);

        when(this.tourService.getListTypeTours()).thenReturn(typeTours);
        when(this.tourService.getListCountry()).thenReturn(countries);

        mockMvc.perform(get("/tours/admin/tour/createView"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.CREATE_TOUR))
                .andExpect(forwardedUrl(Path.CREATE_TOUR))
                .andExpect(model().attribute("typeDef", is(typeTours.get(0))))
                .andExpect(model().attribute("countryDef", is(countries.get(0))));
    }

    @Test
    public void editViewTourTest() throws Exception {

        Country countryFirst = Country.create("testCountry1", "тестСтрана1");
        countryFirst.setId(1);
        Country countrySecond = Country.create("testCountry2", "тестСтрана2");
        countrySecond.setId(2);

        TypeTour typeFirst = TypeTour.create("testType1", "тестТип1");
        typeFirst.setId(1);
        TypeTour typeSecond = TypeTour.create("testType2", "тестТип2");
        typeSecond.setId(2);

        List<Country> countries = Arrays.asList(countryFirst, countrySecond);
        List<TypeTour> typeTours = Arrays.asList(typeFirst, typeSecond);

        Tour tour = new Tour();
        tour.setId(1);
        tour.setNameEn("testTour1");
        tour.setTypeTourId(1);
        tour.setCountryId(1);

        when(request.getParameter("id")).thenReturn("1");
        when(this.tourService.getTourById(1)).thenReturn(tour);
        when(this.tourService.getTypeTourById(1)).thenReturn(typeFirst);
        when(this.tourService.getCountryById(1)).thenReturn(countryFirst);
        when(this.tourService.getListTypeTours()).thenReturn(typeTours);
        when(this.tourService.getListCountry()).thenReturn(countries);

        mockMvc.perform(get("/tours/admin/tour/editView")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name(Path.EDIT_TOUR))
                .andExpect(forwardedUrl(Path.EDIT_TOUR))
                .andExpect(model().attribute("typeDef", is(typeTours.get(0))))
                .andExpect(model().attribute("countryDef", is(countries.get(0))))
                .andExpect(model().attribute("tour", hasProperty("id", is(1))))
                .andExpect(model().attribute("tour", hasProperty("nameEn", is("testTour1"))));
    }
}