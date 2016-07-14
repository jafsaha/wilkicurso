/*
 * Developed by 10Pines SRL
 * License: 
 * This work is licensed under the 
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ 
 * or send a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 *  
 */
package elevator;

public class ElevatorController {
	private boolean working=false;
	private int floor=0;
	Cabin cabin;
	
	private Motor elevatorMotor;
	private boolean up;
	
	public ElevatorController(){
		cabin = new Cabin();
		elevatorMotor = new Motor();
	}

	//Elevator state
	public boolean isIdle() {
		return !isWorking();
	}

	public boolean isWorking() {		
		return working;
	}

	//Door state
	public boolean isCabinDoorOpened() {
		return cabin.door.status().isOpen();
	}

	public boolean isCabinDoorOpening() {
		return cabin.door.status().isOpenning();
//		return   cabinMotor.isActive() && MotorMode.clockWise.equals(cabinMotor.moveState());
	}

	public boolean isCabinDoorClosed() {
		return cabin.door.status().isClosed();
	}

	public boolean isCabinDoorClosing() {
		return cabin.door.status().isClosing();
//		return cabinMotor.isActive() && MotorMode.counterClockWise.equals(cabinMotor.moveState());
	}

	//Cabin state
	public int cabinFloorNumber() {
		return floor;
	}

	public boolean isCabinStopped() {
		return !isCabinMoving();
	}
	
	public boolean isCabinMoving() {
		return cabin.status.isMoving();
//		return cabinMoving;
	}

	public boolean isCabinWaitingForPeople() {
		throw new UnsupportedOperationException();
	}


	//Events
	public void goUpPushedFromFloor(int aFloorNumber) {
		cabin.closeDoor();
		working = true;
		up = true;
	}

	public void cabinOnFloor(int aFloorNumber) {
		cabin.stop();
		
//		cabinStopped = true;
//		cabinMoving = false;
		floor = aFloorNumber;
		
		cabinDoorOpened = false;
		cabinDoorClosed = false;	
		cabinMotor.start(MotorMode.clockWise);
		elevatorMotor.stop();
		
	}

	public void cabinDoorClosed() {
		cabinDoorClosed = true;
		cabinMotor.stop();
		if(up){
			elevatorMotor.start(MotorMode.clockWise);
		}else{
			elevatorMotor.start(MotorMode.counterClockWise);
		}
		cabinMoving = true;
		
		
	}

	public void openCabinDoor() {
		if(working){
			if(!elevatorMotor.isActive()){
				if(isCabinDoorClosing()){
					cabinMotor.stop();
					cabinMotor.start(MotorMode.clockWise);
				}
			}
		}else{
			elevatorMotor.stop();
			cabinMotor.stop();
			cabinDoorOpened = true;
			
		}
	}

	public void cabinDoorOpened() {
		elevatorMotor.stop();
		cabinDoorOpened = true;
		working = false;
		cabinStopped = true;
		cabinDoorClosed = false;
		cabinMotor.stop();
		
	}

	public void waitForPeopleTimedOut() {
		throw new UnsupportedOperationException();
	}

	public void closeCabinDoor() {
		throw new UnsupportedOperationException();
	}

}
