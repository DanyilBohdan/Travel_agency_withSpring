package org.bohdan.web.controllers;

import org.bohdan.model.general.ListBean;
import org.bohdan.model.general.OrderTours;
import org.bohdan.model.general.TourView;
import org.bohdan.web.Path;
import org.bohdan.web.services.OrderService;
import org.bohdan.web.services.TourService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

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

        TourView first = TourView.createTour("testName1","testType1","testCountry1",
                "testDesc1",980, 4, 5, date, 4, 5);
        first.setId(1);

        TourView second = TourView.createTour("testName2","testType2","testCountry2",
                "testDesc2",750, 2, 3, date, 1, 2);
        second.setId(2);

        ListBean countryFirst = ListBean.create(1,"testCountry1");
        ListBean countrySecond = ListBean.create(2,"testCountry2");
        ListBean typeFirst = ListBean.create(1,"testType1");
        ListBean typeSecond = ListBean.create(2,"testType2");

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

}
