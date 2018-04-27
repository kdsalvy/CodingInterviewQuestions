package usecase.design.parkinglot;

public class ParkingLevel {

    private ParkingSpace parkingSpaces;
    private int level;

    public ParkingLevel(int level, int parkingSpots){
        this.level = level;
        this.parkingSpaces = new ParkingSpace(parkingSpots);
    }

    public ParkingSpace getParkingSpaces() {
        return parkingSpaces;
    }

    public int getLevel(){
        return this.level;
    }
}
