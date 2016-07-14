package elevator;

public interface DoorStatus {
	public boolean isOpen();
	public boolean isClosed();
	public boolean isOpenning();
	public boolean isClosing();
	public void closeDoor(Cabin cabin);
}
