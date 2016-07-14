package elevator;

public class DoorOpeningStatus implements DoorStatus {

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOpenning() {
		return true;
	}

	@Override
	public boolean isClosing() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void closeDoor(Cabin cabin) {
		cabin.closeDoorWhen(this);
	}


}
