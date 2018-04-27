package usecase.design.parkinglot;

public class ExitGate {

    private ParkingLevel[] levels;

    public int handleExit(String vehicleNumber){
        int totalHours = 0;
        for(int i = 0; i < levels.length; i++){
            if(levels[i].getParkingSpaces().isCarPresent(vehicleNumber)) {
                ParkingSpace parkingSpaceDetail = levels[i].getParkingSpaces().findAndRemoveCarFromParkingSpace(vehicleNumber);
                totalHours = (int)Math.ceil((parkingSpaceDetail.getEndDateTime().getTime() -
                        parkingSpaceDetail.getStartDateTime().getTime()) / 1000 * 60 * 60);
                break;
            }
        }
        return totalHours;
    }
}
