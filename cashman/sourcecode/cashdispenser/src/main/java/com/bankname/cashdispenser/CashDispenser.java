package com.bankname.cashdispenser;

public class CashDispenser {
	
	private String noteDenomintation;
	private long noteCount;
	private long withdrawalAmount;
	
	public String getNoteDenomintation() {
		return noteDenomintation;
	}
	public void setNoteDenomintation(String noteDenomintation) {
		this.noteDenomintation = noteDenomintation;
	}
	public long getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(long noteCount) {
		this.noteCount = noteCount;
	}
	public long getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(long withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}	

}
