package elevator;

public class CabinIdleStatus implements CabinStatus {

	@Override
	public boolean isMoving() {
		return false;
	}

	@Override
	public boolean isIdle() {
		return true;
	}

}
