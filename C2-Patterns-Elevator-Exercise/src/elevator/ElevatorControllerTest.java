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
import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ElevatorControllerTest {
	
	@Test
	public void test01ElevatorStartsIdleWithDoorOpenOnFloorZero(){
		ElevatorController elevatorController = new ElevatorController();
		
		assertTrue(elevatorController.isIdle());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpened());
		assertEquals(0,elevatorController.cabinFloorNumber());
	}
	
	@Test
	public void test02CabinDoorStartsClosingWhenElevatorGetsCalled(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		
		assertFalse(elevatorController.isIdle());
		assertTrue(elevatorController.isWorking());
		
		assertTrue(elevatorController.isCabinStopped());
		assertFalse(elevatorController.isCabinMoving());
		
		assertFalse(elevatorController.isCabinDoorOpened());
		assertFalse(elevatorController.isCabinDoorOpening());
		assertTrue(elevatorController.isCabinDoorClosing());
		assertFalse(elevatorController.isCabinDoorClosed());
	}
	
	@Test
	public void test03CabinStartsMovingWhenDoorGetsClosed(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		
		assertFalse(elevatorController.isIdle());
		assertTrue(elevatorController.isWorking());
	
		assertFalse(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinMoving());
		
		assertFalse(elevatorController.isCabinDoorOpened());
		assertFalse(elevatorController.isCabinDoorOpening());
		assertFalse(elevatorController.isCabinDoorClosing());
		assertTrue(elevatorController.isCabinDoorClosed());
	}
	
	@Test
	public void test04CabinStopsAndStartsOpeningDoorWhenGetsToDestination(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);

		assertFalse(elevatorController.isIdle());
		assertTrue(elevatorController.isWorking());
		
		assertTrue(elevatorController.isCabinStopped());
		assertFalse(elevatorController.isCabinMoving());
				
		assertFalse(elevatorController.isCabinDoorOpened());
		assertTrue(elevatorController.isCabinDoorOpening());
		assertFalse(elevatorController.isCabinDoorClosing());
		assertFalse(elevatorController.isCabinDoorClosed());

		assertEquals(1,elevatorController.cabinFloorNumber());
	}
		
	@Test
	public void test05ElevatorGetsIdleWhenDoorGetOpened(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		elevatorController.cabinDoorOpened();
		
		assertTrue(elevatorController.isIdle());
		assertFalse(elevatorController.isWorking());
		
		assertTrue(elevatorController.isCabinStopped());
		assertFalse(elevatorController.isCabinMoving());

		assertTrue(elevatorController.isCabinDoorOpened());
		assertFalse(elevatorController.isCabinDoorOpening());
		assertFalse(elevatorController.isCabinDoorClosing());
		assertFalse(elevatorController.isCabinDoorClosed());
		
		assertEquals(1,elevatorController.cabinFloorNumber());
	}

	// STOP HERE!
	
	@Test
	public void test06DoorKeepsOpenedWhenOpeningIsRequested(){
		ElevatorController elevatorController = new ElevatorController();
		
		assertTrue(elevatorController.isCabinDoorOpened());
		
		elevatorController.openCabinDoor();

		assertTrue(elevatorController.isCabinDoorOpened());
		
	}

	@Test
	public void test07DoorMustBeOpenedWhenCabinIsStoppedAndClosingDoors(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorClosing());
		
		elevatorController.openCabinDoor();
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());
	}

	@Test
	public void test08CanNotOpenDoorWhenCabinIsMoving(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinMoving());
		assertTrue(elevatorController.isCabinDoorClosed());

		elevatorController.openCabinDoor();
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinMoving());
		assertTrue(elevatorController.isCabinDoorClosed());
	}

	@Test
	public void test09DoorKeepsOpeneingWhenItIsOpeneing(){
		ElevatorController elevatorController = new ElevatorController();
	
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());

		elevatorController.openCabinDoor();
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());
	}

	// STOP HERE!!
	
	@Test
	public void test10RequestToGoUpAreEnqueueWhenRequestedWhenCabinIsMoving(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorOpened();
	
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinWaitingForPeople());
		assertTrue(elevatorController.isCabinDoorOpened());
	}

	@Test
	public void test11CabinDoorStartClosingAfterWaitingForPeople(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorOpened();
		elevatorController.waitForPeopleTimedOut();

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorClosing());
	}

	@Test
	public void test12StopsWaitingForPeopleIfCloseDoorIsPressed(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorOpened();
	
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinWaitingForPeople());
		assertTrue(elevatorController.isCabinDoorOpened());

		elevatorController.closeCabinDoor();

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorClosing());

	}

	@Test
	public void test13CloseDoorDoesNothingIfIdle(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.closeCabinDoor();

		assertTrue(elevatorController.isIdle());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpened());

	}

	@Test
	public void test14CloseDoorDoesNothingWhenCabinIsMoving(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
	
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinMoving());
		assertTrue(elevatorController.isCabinDoorClosed());

		elevatorController.closeCabinDoor();

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinMoving());
		assertTrue(elevatorController.isCabinDoorClosed());
	}

	@Test
	public void test15CloseDoorDoesNothingWhenOpeningTheDoorToWaitForPeople(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
	
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());

		elevatorController.closeCabinDoor();

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());

	}
	
	// STOP HERE!!

	@Test
	public void test16ElevatorHasToEnterEmergencyIfStoppedAndOtherFloorSensorTurnsOn(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		try {
			elevatorController.cabinOnFloor(0);
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de cabina desincronizado");
		}
	}

	@Test
	public void test17ElevatorHasToEnterEmergencyIfFalling(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		try {
			elevatorController.cabinOnFloor(0);
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de cabina desincronizado");
		}
	}

	@Test
	public void test18ElevatorHasToEnterEmergencyIfJumpsFloors(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(3);
		elevatorController.cabinDoorClosed();
		try {
			elevatorController.cabinOnFloor(3);
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de cabina desincronizado");
		}
	}

	@Test
	public void test19ElevatorHasToEnterEmergencyIfDoorClosesAutomatically(){
		ElevatorController elevatorController = new ElevatorController();
		
		try {
			elevatorController.cabinDoorClosed();
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de puerta desincronizado");
		}
	}

	@Test
	public void test20ElevatorHasToEnterEmergencyIfDoorClosedSensorTurnsOnWhenClosed(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		try {
			elevatorController.cabinDoorClosed();
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de puerta desincronizado");
		}
	}

	@Test
	public void test21ElevatorHasToEnterEmergencyIfDoorClosesWhenOpening(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(1);
		try {
			elevatorController.cabinDoorClosed();
			fail();
		} catch (ElevatorEmergency elevatorEmergency) {
			assertTrue (elevatorEmergency.getMessage() == "Sensor de puerta desincronizado");
		}
	}

	// STOP HERE!!
	// More tests here to verify bad sensor function
	
	@Test
	public void test22CabinHasToStopOnTheFloorsOnItsWay(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinOnFloor(1);

		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());
	}
	
	@Test
	public void test23ElevatorCompletesAllTheRequests(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinDoorClosed();
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinOnFloor(1);
		elevatorController.cabinDoorOpened();
		elevatorController.waitForPeopleTimedOut();
		elevatorController.cabinDoorClosed();
		elevatorController.cabinOnFloor(2);
		
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());
	}
	
	@Test
	public void test24CabinHasToStopOnFloorsOnItsWayNoMatterHowTheyWellCalled(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorClosed();
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinOnFloor(1);
		
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorOpening());
	}
	
	@Test
	public void test25CabinHasToStopAndWaitForPeopleOnFloorsOnItsWayNoMatterHowTheyWellCalled(){
		ElevatorController elevatorController = new ElevatorController();
		
		elevatorController.goUpPushedFromFloor(2);
		elevatorController.cabinDoorClosed();
		elevatorController.goUpPushedFromFloor(1);
		elevatorController.cabinOnFloor(1);
		elevatorController.cabinDoorOpened();
		elevatorController.waitForPeopleTimedOut();
		
		assertTrue(elevatorController.isWorking());
		assertTrue(elevatorController.isCabinStopped());
		assertTrue(elevatorController.isCabinDoorClosing());
	}
}

