package elevator;

public class DoorClosedStatus implements DoorStatus {

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public boolean isClosed() {
		return true;
	}

	@Override
	public boolean isOpenning() {
		// TODO Auto-generated method stub
		return false;
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
