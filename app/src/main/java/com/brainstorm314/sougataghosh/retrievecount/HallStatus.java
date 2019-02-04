package com.brainstorm314.sougataghosh.retrievecount;

public class HallStatus {
    String movieName, availableCount, reservedCount;
    HallStatus() {};

    public HallStatus(String movieName, String availableCount, String reservedCount) {
        this.movieName = movieName;
        this.availableCount = availableCount;
        this.reservedCount = reservedCount;
    }
}
