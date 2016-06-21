package com.musicforall.services;

import com.musicforall.model.Song;
import com.musicforall.model.Songlist;

import java.util.Set;

/**
 * Created by Pukho on 15.06.2016.
 */
public interface SonglistService {

    void save(Integer userId, String songlistName);
    Set<Song> getAllSongsInSonglist(Integer songlistId);
    void addSong(Song song, Songlist songlist);
    void delete(Integer songlistId);
}
