package elevator;

public class DoorOpenedStatus implements DoorStatus {

	@Override
	public boolean isOpen() {
		return true;
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
		// TODO Auto-generated method stub
		return false;
	}

}
