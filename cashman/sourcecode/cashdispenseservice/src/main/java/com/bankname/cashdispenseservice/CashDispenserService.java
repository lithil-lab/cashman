package com.bankname.cashdispenseservice;

import com.bankname.atminit.ATMCashCount;

/**
 * Service class which handles the operations of ATM.
 * 
 * @author Lithil Kuriakose
 *
 */
public class CashDispenserService {

	/**
	 * @param withdrawalAmount
	 * @return the string value with result of cash dispensing operation
	 */
	public String dispenseCash(long withdrawalAmount) {

		String result = "";
		if (withdrawalAmount < CashDispenserConstant.DOLLAR50) {// WITHDRAWAL AMOUNT < 50 DOLLARS BLOCK
			if (withdrawalAmount % CashDispenserConstant.DOLLAR20 > 0) {
				result = "ERROR !!!!!!!! Cannot dispense this amount";
			} else {
				long noOf20DollarsRequired = withdrawalAmount / CashDispenserConstant.DOLLAR20;

				long availableCountOf20Dollars = ATMCashCount.dollar20Count;
				if (availableCountOf20Dollars >= noOf20DollarsRequired) {

					ATMCashCount.dollar20Count -= noOf20DollarsRequired;
					result = "Dispensing cash - Please collect " + noOf20DollarsRequired + " of $20 notes. Now "
							+ ATMCashCount.dollar20Count + " of $20 notes left";

				} else {
					result = "SORRY... NOT enough cash in ATM!!! ";
				}
			}
		} else if (withdrawalAmount % CashDispenserConstant.DOLLAR50 > 0) {// WITHDRAWAL AMOUNT > 50 DOLLARS
			long excessMoney = withdrawalAmount % CashDispenserConstant.DOLLAR50;
			long noOf20DollarsRequired = 0;
			long noOf50DollarsRequired = 0;
			if (excessMoney % CashDispenserConstant.DOLLAR20 > 0) {

				if (withdrawalAmount % CashDispenserConstant.DOLLAR20 > 0) {
					result = checkforEnoughBalanceAndDispense(withdrawalAmount, ATMCashCount.dollar50Count,
							ATMCashCount.dollar20Count, CashDispenserConstant.DOLLAR50, CashDispenserConstant.DOLLAR20);

				} else {
					noOf20DollarsRequired = withdrawalAmount / CashDispenserConstant.DOLLAR20;
					if (noOf20DollarsRequired <= ATMCashCount.dollar20Count) {
						ATMCashCount.dollar20Count -= noOf20DollarsRequired;

						result = "Dispensing cash - Please collect " + noOf20DollarsRequired + " of $20 notes.  Now "
								+ ATMCashCount.dollar20Count + " of $20 notes left";

					} else {

						result = checkforEnoughBalanceAndDispense(withdrawalAmount, ATMCashCount.dollar20Count,
								ATMCashCount.dollar50Count, CashDispenserConstant.DOLLAR20, CashDispenserConstant.DOLLAR50);

					}

				}
			} else {
				noOf20DollarsRequired = excessMoney / CashDispenserConstant.DOLLAR20;
				noOf50DollarsRequired = withdrawalAmount / CashDispenserConstant.DOLLAR50;
				if (noOf50DollarsRequired <= ATMCashCount.dollar50Count
						&& noOf20DollarsRequired <= ATMCashCount.dollar20Count) {

					ATMCashCount.dollar50Count -= noOf50DollarsRequired;
					ATMCashCount.dollar20Count -= noOf20DollarsRequired;
					result = "Dispensing cash - Please collect " + noOf20DollarsRequired + " of $20 notes and "
							+ noOf50DollarsRequired + "  of $50 notes. Now " + ATMCashCount.dollar50Count
							+ " of $50 and " + ATMCashCount.dollar20Count + " of $20 left";

				} else {
					result = "Not enough $20 or $50 notes";
				}

			}
		} else {
			// WITHDRAWAL AMOUNT IS MULTIPLE OF 50 DOLLARS BLOCK
			//long noOf20DollarsRequired = 0;
			long noOf50DollarsRequired = withdrawalAmount / CashDispenserConstant.DOLLAR50;
			if (noOf50DollarsRequired <= ATMCashCount.dollar50Count) {
				ATMCashCount.dollar50Count -= noOf50DollarsRequired;
				result = "Dispensing cash - Please collect " + noOf50DollarsRequired + " of $50 notes Now "
						+ ATMCashCount.dollar50Count + " of $50 left";
			} else {
				result = checkforEnoughBalanceAndDispense(withdrawalAmount, ATMCashCount.dollar50Count,
						ATMCashCount.dollar20Count, CashDispenserConstant.DOLLAR50, CashDispenserConstant.DOLLAR20);
			}

		}
		return result;

	}

	/**
	 * @param dollar20Count
	 * @param dollar50Count
	 * @return the string value with the success message after initializing cash
	 */
	public String initialiseCash(long dollar20Count, long dollar50Count) {
		ATMCashCount.dollar20Count = dollar20Count;
		ATMCashCount.dollar50Count = dollar50Count;
		return "Cash count successfully initialised";
	}

	/**
	 * @param dollar20Count
	 * @param dollar50Count
	 * @return the success message after refilling the ATM
	 */
	public String refillATM(long dollar20Count, long dollar50Count) {
		ATMCashCount.dollar20Count += dollar20Count;
		ATMCashCount.dollar50Count += dollar50Count;
		return "ATM is refilled successfully";
	}

	/**
	 * @param withdrawalAmount
	 * @param cashCount
	 * @param otherCashCount
	 * @param cashDenomination
	 * @param otherDenomination
	 * @return
	 */
	private static String checkforEnoughBalanceAndDispense(long withdrawalAmount, long cashCount, long otherCashCount,
			int cashDenomination, int otherDenomination) {
		boolean canDispenseCash = false;
		long noOfCashDenominationRequired = 0;
		long noOfOtherDenominationRequired = 0;
		String result = "";
		for (int i = 0; i < cashCount; i++) {
			long multiple = withdrawalAmount - cashDenomination * i;
			if (multiple % otherDenomination == 0 && multiple / otherDenomination <= otherCashCount) {
				canDispenseCash = true;
				noOfCashDenominationRequired = i;
				noOfOtherDenominationRequired = multiple / otherDenomination;
				;
				break;
			}

		}
		if (canDispenseCash) {
			if (cashDenomination == CashDispenserConstant.DOLLAR20) {
				ATMCashCount.dollar20Count -= noOfCashDenominationRequired;
				ATMCashCount.dollar50Count -= noOfOtherDenominationRequired;
				result = "Dispensing cash - Please collect " + noOfCashDenominationRequired + " of $20 notes and "
						+ noOfOtherDenominationRequired + "  of $50 notes. Now " + ATMCashCount.dollar50Count
						+ " of $50 and " + ATMCashCount.dollar20Count + " of $20 left";
			} else {
				ATMCashCount.dollar20Count -= noOfOtherDenominationRequired;
				ATMCashCount.dollar50Count -= noOfCashDenominationRequired;
				result = "Dispensing cash - Please collect " + noOfCashDenominationRequired + " of $50 notes and "
						+ noOfOtherDenominationRequired + "  of $20 notes. Now " + ATMCashCount.dollar50Count
						+ " of $50 and " + ATMCashCount.dollar20Count + " of $20 left";
			}
		}

		else {
			result = "ERROR !!!!!!!! Cannot dispense this amount";
		}
		return result;
	}

}
