package elevator;

public class Door {
	DoorStatus status = new DoorOpenedStatus();
	
	public void startOpen(){
		status = new DoorOpeningStatus();
	}
	
	public void endOpen(){
		status = new DoorOpenedStatus();
	}

	public void startClose(){
		status = new DoorClosingStatus();
	}
	
	public void endClose(){
		status = new DoorClosingStatus();
	}

	public DoorStatus status() {
		return status;
	}

	
}
