package org.bohdan.web.services;

import org.bohdan.db.DAO.CountryDao;
import org.bohdan.db.DAO.TourDao;
import org.bohdan.db.DAO.TypeTourDao;
import org.bohdan.model.general.TourView;
import org.bohdan.web.services.impl.TourServiceImpl;
import org.junit.Test;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

public class TourServiceImplTest {

    private final TypeTourDao typeTourDao = mock(TypeTourDao.class);

    private final CountryDao countryDao = mock(CountryDao.class);

    private final TourDao tourDao = mock(TourDao.class);

    private final TourService tourService = new TourServiceImpl(typeTourDao, countryDao, tourDao);

    @Test
    public void viewToursTest() throws Exception {
        List<TourView> tours = generateTour(3);
        tourDao.setFilter(0);
        doReturn(tours).when(tourDao).findAllByLocale("EN", 0, 6);

        List<TourView> tourViews = tourService.viewTours("EN");

        verify(tourDao).findAllByLocale("EN", 0, 6);
        assertThat(tourViews, hasSize(3));

        tours.forEach(
                tourView -> assertThat(tourViews, hasItem(
                        allOf(
                                hasProperty("id", is(tourView.getId())),
                                hasProperty("name", is(tourView.getName())),
                                hasProperty("type", is(tourView.getType())),
                                hasProperty("country", is(tourView.getCountry())),
                                hasProperty("description", is(tourView.getDescription())),
                                hasProperty("price", is(tourView.getPrice())),
                                hasProperty("countPeople", is(tourView.getCountPeople())),
                                hasProperty("markHotel", is(tourView.getMarkHotel())),
                                hasProperty("startDate", is(tourView.getStartDate())),
                                hasProperty("days", is(tourView.getDays())),
                                hasProperty("discount", is(tourView.getDiscount()))
                        )))
        );
    }

    @Test
    public void viewTourTest() throws Exception {
        TourView tour = generateTour(1).get(0);
        doReturn(tour).when(tourDao).findByIdLocale("EN", tour.getId());

        TourView tourView = tourService.viewTour("EN", tour.getId());

        verify(tourDao).findByIdLocale("EN", tour.getId());

        assertThat(tourView,
                allOf(
                        hasProperty("id", is(tourView.getId())),
                        hasProperty("name", is(tourView.getName())),
                        hasProperty("type", is(tourView.getType())),
                        hasProperty("country", is(tourView.getCountry())),
                        hasProperty("description", is(tourView.getDescription())),
                        hasProperty("price", is(tourView.getPrice())),
                        hasProperty("countPeople", is(tourView.getCountPeople())),
                        hasProperty("markHotel", is(tourView.getMarkHotel())),
                        hasProperty("startDate", is(tourView.getStartDate())),
                        hasProperty("days", is(tourView.getDays())),
                        hasProperty("discount", is(tourView.getDiscount()))
                ));
    }

    private List<TourView> generateTour(int count) throws ParseException {
        List<TourView> tours = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse("01/01/2021");
        for (int i = 0; i < count; i++) {
            TourView tourView = TourView.createTour("testName" + i, "testType" + i, "testCountry" + i,
                    "testDesc" + i, 200 + i * i - i, 4, 5, date, 4, 5);
            tourView.setId(i);
            tours.add(tourView);
        }
        return tours;
    }
}
