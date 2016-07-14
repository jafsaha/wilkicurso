package elevator;

public class DoorClosingStatus implements DoorStatus {

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosing() {
		return true;
	}
	
	@Override
	public void closeDoor(Cabin cabin) {
		cabin.closeDoorWhen(this);
	}


}
