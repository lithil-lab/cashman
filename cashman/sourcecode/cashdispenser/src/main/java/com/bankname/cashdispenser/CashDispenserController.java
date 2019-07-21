package com.bankname.cashdispenser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankname.cashdispenseservice.CashDispenserService;

/**
 * The rest controller class for ATM operations.
 * 
 * @author Lithil Kuriakose
 *
 */
@RestController
public class CashDispenserController {

	/**
	 * @param withdrawalAmount
	 * @return the message after dispensing the cash
	 */
	@RequestMapping("/cashdispenser")
	public String dispenseCash(@RequestParam(value = "withdrawalAmount", defaultValue = "0") String withdrawalAmount) {
		CashDispenserService cashDispenserService = new CashDispenserService();
		String result = cashDispenserService.dispenseCash(new Long(withdrawalAmount));
		return result;
	}

	/**
	 * @param dollar20Count
	 * @param dollar50Count
	 * @return the message after initialising the ATM
	 */
	@RequestMapping("/initialiseATM")
	public String initialiseATM(@RequestParam(value = "dollar20Count", defaultValue = "0") String dollar20Count,
			@RequestParam(value = "dollar50Count", defaultValue = "0") String dollar50Count) {
		CashDispenserService cashDispenserService = new CashDispenserService();
		String result = cashDispenserService.initialiseCash(new Long(dollar20Count), new Long(dollar50Count));
		return result;
	}

	/**
	 * @param dollar20Count
	 * @param dollar50Count
	 * @return the message after refilling the ATM
	 */
	@RequestMapping("/refillATM")
	public String refillATM(@RequestParam(value = "dollar20Count", defaultValue = "0") String dollar20Count,
			@RequestParam(value = "dollar50Count", defaultValue = "0") String dollar50Count) {
		CashDispenserService cashDispenserService = new CashDispenserService();
		String result = cashDispenserService.refillATM(new Long(dollar20Count), new Long(dollar50Count));
		return result;
	}

}
