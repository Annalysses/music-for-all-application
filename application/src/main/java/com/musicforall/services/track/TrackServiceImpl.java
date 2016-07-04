package com.musicforall.services.track;

import com.musicforall.common.dao.Dao;
import com.musicforall.model.Playlist;
import com.musicforall.model.Tag;
import com.musicforall.model.Track;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Pukho on 15.06.2016.
 */
@Service("trackService")
@Transactional
public class TrackServiceImpl implements TrackService {

    @Autowired
    private Dao dao;

    @Override
    public Track save(Track track) {
        return dao.save(track);
    }

    @Override
    public Collection<Track> saveAll(Collection<Track> tracks) {
        return dao.saveAll(tracks);
    }

    @Override
    public void delete(Integer trackId) {
        final Track track = dao.get(Track.class, trackId);
        dao.delete(track);
    }

    @Override
    public Track get(Integer id) {
        return dao.get(Track.class, id);
    }

    @Override
    public void addTags(Integer trackId, Set<Tag> tags) {
        final Track track = get(trackId);
        track.addTags(tags);
        save(track);
    }

    @Override
    public Collection<Track> getAllByName(String nameTrack) {
        final DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Track.class)
                .add(Restrictions.like("name", "%"+nameTrack+"%").ignoreCase());
        return dao.getAllBy(detachedCriteria);
    }

    @Override
    public Collection<Track> getTracksByTags(Collection<String> tagsName) {

        Disjunction disjuction = Restrictions.disjunction();
        for (String t:
             tagsName) {
            disjuction.add(Restrictions.eq("tgs.name", t).ignoreCase());
        }
        DetachedCriteria subcriteria = DetachedCriteria.forClass(Track.class)
                .createAlias("tags", "tgs")
                .add(disjuction)
                .setProjection(Projections.property("id"));

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Track.class)
                .add(Subqueries.propertyIn("id", subcriteria));

        return dao.getAllBy(detachedCriteria);
    }
}