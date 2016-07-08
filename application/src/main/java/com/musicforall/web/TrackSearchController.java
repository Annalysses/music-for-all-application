package com.musicforall.web;

import com.musicforall.model.Track;
import com.musicforall.model.User;
import com.musicforall.services.track.TrackService;
import com.musicforall.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * This RESTful Web service provides the track search facility.
 */
@Controller
@ResponseBody
@RequestMapping("/api/search")
public class TrackSearchController {

    @Autowired
    private TrackService trackService;

    private static final Logger log = Logger.getLogger("TrackSearchController");

    @PostConstruct
    private void addSampleRecords() {
        log.info("Post construct");
        trackService.save(new Track("Track 1", "/track1.mp3"));
        trackService.save(new Track("Track 2", "/track2.mp3"));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Track> search(@RequestParam String query) {

        Collection<Track> tracks = trackService.getAllByName(query);
        return tracks;
    }
}
