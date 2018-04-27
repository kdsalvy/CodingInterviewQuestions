package usecase.design.parkinglot;

import usecase.design.parkinglot.Vehicle.Vehicle;
import usecase.design.parkinglot.Vehicle.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private ParkingLevel[] levels;
    private EntryGate[] gates;
    private Map<VehicleType, Integer> tariffMap;

    public ParkingLot(int levelCount, int spotsOnEachLevel){
        for(int i = 0; i < levelCount; i++){
            levels[i] = new ParkingLevel(i, spotsOnEachLevel);
        }
        tariffMap = new HashMap<>();
    }

    public void entry(EntryGate gate, Vehicle vehicle){
        gate.handleVehicleEntry(vehicle);
    }

    public int exit(ExitGate gate, String licenseNumber){
        gate.handleExit(licenseNumber);
    }

}
