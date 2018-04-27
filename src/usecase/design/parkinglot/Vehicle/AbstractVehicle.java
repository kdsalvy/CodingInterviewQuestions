package usecase.design.parkinglot.Vehicle;

public abstract class AbstractVehicle implements Vehicle{

    private String licensePlateNumber;
    private VehicleType type;

    public AbstractVehicle(String licensePlateNumber, VehicleType type){
        this.licensePlateNumber = licensePlateNumber;
        this.type = type;
    }

    @Override
    public String getVehicleNumber(){
        return licensePlateNumber;
    }

    @Override
    public VehicleType getVehicleType(){
        return type;
    }

}
