package com.onlineauction.publisher;

import java.util.List;

import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;

public interface BidPublisher {
	
	public WinnerBidData getWinningBid(List<Bidder> bidders);

}
