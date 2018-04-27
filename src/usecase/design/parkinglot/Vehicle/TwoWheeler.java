package usecase.design.parkinglot.Vehicle;

public class TwoWheeler extends AbstractVehicle {

    public TwoWheeler(String licensePlateNumber) {
        super(licensePlateNumber, VehicleType.TWO_WHEELER);
    }
}
