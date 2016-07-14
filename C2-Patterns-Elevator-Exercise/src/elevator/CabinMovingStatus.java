package elevator;

public class CabinMovingStatus implements CabinStatus {

	@Override
	public boolean isMoving() {
		return true;
	}

	@Override
	public boolean isIdle() {
		return false;
	}
	
}
