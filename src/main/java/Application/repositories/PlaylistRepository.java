package Application.repositories;

import Application.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    @Query(value="select track_list_track_id from playlists_track_list where playlists_playlistid=:playlist_id",nativeQuery = true)
    List<Integer> getTrackIDsByPlaylistId(Integer playlist_id);

    @Query(value="select playlist_name from playlists where playlistid=:playlist_id",nativeQuery = true)
    String getPlaylistName(Integer playlist_id);

    @Query(value="select user_userid from playlists where playlistid=:playlist_id",nativeQuery = true)
    Integer getUserId(Integer playlist_id);

    @Query(value="delete from playlists_track_list where (playlists_playlistid=:playlist_id AND track_list_track_id=:track_id)",nativeQuery = true)
    Integer removeTrackFromPlaylist(Integer playlist_id,Integer track_id);

    List<Playlist> getPlaylistsByUserId(Integer user_id);
}