import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;

public final class MusicPlaylistManager {

    private Map<String, Sequence<String>> playlists;

    public MusicPlaylistManager() {
        this.playlists = new Map1L<>();
    }

    public void createPlaylist(String name) {
        if (!this.playlists.hasKey(name)) {
            this.playlists.add(name, new Sequence1L<>());
            System.out.println("Created playlist: " + name);
        } else {
            System.out.println("Playlist already exists" + name);
        }
    }

    public void deletePlaylist(String name) {
        if (this.playlists.hasKey(name)) {
            this.playlists.remove(name);
            System.out.println("Deleted playlist: " + name);
        } else {
            System.out.println("Playlist not found: " + name);
        }
    }

    public void addSong(String playlistName, String song) {
        if (this.playlists.hasKey(playlistName)) {
            Sequence<String> songs = this.playlists.value(playlistName);
            songs.add(songs.length(), song);
            System.out.println(
                    "Added song '" + song + "' to playlist: " + playlistName);
        } else {
            System.out.println("Playlist not found: " + playlistName);
        }
    }

    public void removeSong(String playlistName, String song) {
        if (this.playlists.hasKey(playlistName)) {
            Sequence<String> songs = this.playlists.value(playlistName);
            int index = -1;
            for (int i = 0; i < songs.length(); i++) {
                if (songs.entry(i).equals(song) && index == -1) {
                    index = i;
                }
            }
            if (index != -1) {
                songs.remove(index);
                System.out.println("Removed song '" + song + "' from playlist: "
                        + playlistName);
            } else {
                System.out.println("Song: '" + song + "' is not in playlist: "
                        + playlistName);
            }
        } else {
            System.out.println("Playlist not found: " + playlistName);
        }
    }

    public void displayPlaylist(String playlistName) {
        if (this.playlists.hasKey(playlistName)) {
            Sequence<String> songs = this.playlists.value(playlistName);
            if (songs.length() == 0) {
                System.out.println(playlistName + " is Empty");
            } else {
                System.out.println("Playlist: " + playlistName);
                for (int i = 0; i < songs.length(); i++) {
                    System.out.println(" - " + songs.entry(i));
                }
            }
        } else {
            System.out.println("Playlist not found: " + playlistName);
        }

    }

    public int playlistSize(String playlistName) {
        int result = -1;
        if (this.playlists.hasKey(playlistName)) {
            Sequence<String> songs = this.playlists.value(playlistName);
            result = songs.length();
        } else {
            System.out.println("Playlist not found: " + playlistName);
        }
        return result;
    }

    public static void main(String[] args) {
        MusicPlaylistManager manager = new MusicPlaylistManager();

        manager.createPlaylist("Playlist 1");
        manager.createPlaylist("Playlist 2");

        manager.addSong("Playlist 1", "Iris (The Goo Goo Dolls)");
        manager.addSong("Playlist 1",
                "There Is a Light That Never Goes Out (The Smiths)");
        manager.addSong("Playlist 2",
                "Parisienne Walkways (Brian May, Kerry Ellis)");

        manager.displayPlaylist("Playlist 1");
        manager.displayPlaylist("Playlist 2");

        System.out.println(
                "Playlist size is: " + manager.playlistSize("Playlist 1"));

        manager.deletePlaylist("Playlist 1");
        manager.deletePlaylist("Playlist 1");

        manager.removeSong("Playlist 2",
                "Parisienne Walkways (Brian May, Kerry Ellis)");
        manager.removeSong("Playlist 2",
                "Parisienne Walkways (Brian May, Kerry Ellis)");

        manager.displayPlaylist("Playlist 1");
        manager.displayPlaylist("Playlist 2");
    }

}
