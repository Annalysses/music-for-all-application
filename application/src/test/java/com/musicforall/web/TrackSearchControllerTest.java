package com.musicforall.web;

import com.musicforall.config.SpringRootConfiguration;
import com.musicforall.config.WebAppConfig;
import com.musicforall.model.Track;
import com.musicforall.services.track.TrackService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringRootConfiguration.class, WebAppConfig.class})
@WebAppConfiguration
public class TrackSearchControllerTest {

    private MockMvc mockMvc;

    private Track track;

    @Autowired
    private TrackService trackService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();

        track = new Track("Track 1", "/track1.mp3");
        trackService.save(track);
    }

    @Test
    public void testSearch() throws Exception {
        mockMvc.perform(get(String.format(
                "/api/search/?query=%s&category=title", track.getName())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }
}