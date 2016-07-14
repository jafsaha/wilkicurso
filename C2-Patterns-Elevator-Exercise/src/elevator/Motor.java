package elevator;

public class Motor {
	private boolean active = false;
	private MotorMode move = null;
	
	public void start(MotorMode motorMode){
		move = motorMode;
		active = true;
	}
	
	public void stop(){
		move = null;
		active = false;
	}
	
	public MotorMode moveState(){
		return move;
	}
	
	public boolean isActive(){
		return active;
	}
	
	
}
