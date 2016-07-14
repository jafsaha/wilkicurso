package elevator;

public class Cabin {
	CabinStatus status;
	Door door;
	Motor cabinMotor;
	
	public Cabin(){
		this.door = new Door();
		this.cabinMotor = new Motor();
	}

	public void closeDoor() {
		cabinMotor.start(MotorMode.counterClockWise);
		door.startClose();
		door.status().closeDoor(this);
	}
	
	public void closeDoorWhen(DoorClosingStatus status){
		
	}

	public void stop() {
		status = new CabinIdleStatus();
	}
	
}
