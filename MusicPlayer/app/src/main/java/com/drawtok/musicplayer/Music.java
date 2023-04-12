package com.drawtok.musicplayer;

public class Music {
    private String nameSong;
    private int pathToSong;

    public Music(String nameSong, int pathToSong) {
        this.nameSong = nameSong;
        this.pathToSong = pathToSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public int getPathToSong() {
        return pathToSong;
    }

    public void setPathToSong(int pathToSong) {
        this.pathToSong = pathToSong;
    }
}
