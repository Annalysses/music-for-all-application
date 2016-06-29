package com.musicforall.services.tag;

import com.musicforall.model.Tag;
import com.musicforall.model.Track;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by Pukho on 22.06.2016.
 */
public interface TagService {
    Tag save(String name);

    Collection<Tag> saveAll(Collection<Tag> tags);

    boolean isTagExist(String name);

    Tag get(String name);

    List<Tag> getAllTags();
}
