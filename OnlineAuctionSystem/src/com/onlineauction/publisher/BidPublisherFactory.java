package com.onlineauction.publisher;

public class BidPublisherFactory {

	private final BidPublisher bidPublisherMaxValAlgo;
	private final BidPublisher bidPublisherHighRangeDataAlgo;

	public BidPublisherFactory() {
		bidPublisherMaxValAlgo = new BidPublisherMaxValAlgo();
		bidPublisherHighRangeDataAlgo = new BidPublisherHighRangeDataAlgo();
	}

	public BidPublisher createBidPublisher(String logic) {
		if (logic.equalsIgnoreCase("MaxVal"))
			return bidPublisherMaxValAlgo;
		else if (logic.equalsIgnoreCase("HighRangeData"))
			return bidPublisherHighRangeDataAlgo;
		else
			return bidPublisherMaxValAlgo;
	}

}
