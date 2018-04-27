package usecase.design.parkinglot;

import usecase.design.parkinglot.Vehicle.Vehicle;

import java.util.Date;
import java.util.Map;
import java.util.Stack;

public class ParkingSpace {

    private Map<String, Integer> vehicleParkingSpaceMap;
    private Stack<Integer> availableParkingSpaces;
    private Date startDateTime;
    private Date endDateTime;

    public ParkingSpace(int totalSpace){
        for(int i = 1; i <= totalSpace; i++){
            availableParkingSpaces.push(i);
        }
    }

    public boolean isParkingSpaceAvailable(){
        return !this.availableParkingSpaces.isEmpty();
    }

    public boolean isCarPresent(String vehicleNumber){
        return this.vehicleParkingSpaceMap.containsKey(vehicleNumber);
    }

    public void putCarInParkingSpace(Vehicle vehicle){
        int nextAvailableSpace = availableParkingSpaces.pop();
        vehicleParkingSpaceMap.put(vehicle.getVehicleNumber(), nextAvailableSpace);
        startDateTime = new Date();
    }

    public ParkingSpace findAndRemoveCarFromParkingSpace(String vehicleNumber){
        int parkingSlot = -1;
        if(vehicleParkingSpaceMap.containsKey(vehicleNumber)){
            parkingSlot = vehicleParkingSpaceMap.remove(vehicleNumber);
        }
        availableParkingSpaces.push(parkingSlot);
        endDateTime = new Date();
        return this;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public Date getEndDateTime(){
        return endDateTime;
    }
}
