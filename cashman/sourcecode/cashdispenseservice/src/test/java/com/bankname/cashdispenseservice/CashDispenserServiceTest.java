package com.bankname.cashdispenseservice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CashDispenserServiceTest {

	@Test
	public void dispenseCashTest() {
		CashDispenserService service = new CashDispenserService();
		service.initialiseCash(2, 3);
		String result = service.dispenseCash(200);

		assertEquals("Test failed", "ERROR !!!!!!!! Cannot dispense this amount", result);

	}

	@Test
	public void dispenseCashTestWhenEight20NotesAndThree50Notes() {
		CashDispenserService service = new CashDispenserService();
		service.initialiseCash(8, 3);
		String result = service.dispenseCash(200);

		assertEquals("Test failed",
				"Dispensing cash - Please collect 2 of $50 notes and 5  of $20 notes. Now 1 of $50 and 3 of $20 left",
				result);

	}

}
