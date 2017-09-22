package jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;

@ManagedBean
public class InterestBean {
	private double amount, rate, interest;

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	// ActionListener
	public void calculate(ActionEvent evt) {
		interest = amount * rate / 100; 
	}

}











