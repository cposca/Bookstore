package bean;

import java.util.Date;

public class ReviewBean {
	private String name;
	private Date date;
	private String review;
	private String bid;

	public ReviewBean(String name, Date date, String review, String bid) {
		this.name = name;
		this.date = date;
		this.review = review;
		this.bid = bid;
	}

	public String getBid() {
		return bid;
	}

	public Date getDate() {
		return date;
	}

	public String getReview() {
		return review;
	}

	public String getName() {
		return name;
	}
}
