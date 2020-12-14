package org.bohdan.web.controllers;

import org.bohdan.model.general.TourView;
import org.bohdan.web.AbstractBaseSpringTest;
import org.bohdan.web.services.TourService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TourControllerIT extends AbstractBaseSpringTest {

    @Autowired
    private TourService tourService;

    @Test
    @Rollback
    public void userDeletionWorksThroughAllLayers() throws Exception {
        List<TourView> tours = tourService.viewToursForAdmin("EN");
        int idForList = tours.size() - 1;
        Integer id = tours.get(idForList).getId();

        mockMvc.perform(post("/tours/admin/tour/delete")
                .param("id", String.valueOf(id))
                .with(user("admin@gmail.com").roles("ADMIN")))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/tours/admin/view")
                .with(user("admin@gmail.com").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tours", hasSize(idForList)));
    }
}
