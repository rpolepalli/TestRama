package me.rama.feign.model;

public class Confirmation {
	private String confirm;

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Confirmation(String confirm) {
		super();
		this.confirm = confirm;
	}
}