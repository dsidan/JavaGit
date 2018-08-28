package com.onlineauction.publisher;

import java.math.BigDecimal;
import java.util.List;

import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;

public class BidPublisherMaxValAlgo implements BidPublisher {

	public WinnerBidData getWinningBid(List<Bidder> bidders) {

		BigDecimal possibleWinBid = BigDecimal.ZERO;
		BigDecimal secondPossibleWinBid = BigDecimal.ZERO;
		BigDecimal maxPossibleBidAmtByBidder = BigDecimal.ZERO;
		Bidder winningBidder = bidders.get(0);

		if (bidders.size() == 1) {
			possibleWinBid = winningBidder.getStartBid();
		} else {
			for (Bidder bidder : bidders) {
				maxPossibleBidAmtByBidder = calculateMaxBidAmountByBidder(bidder);
				if ((possibleWinBid.compareTo(maxPossibleBidAmtByBidder) == 0)
						&& (winningBidder.getTimestamp().after(bidder.getTimestamp()))) {
					winningBidder = bidder;
				}
				if (possibleWinBid.compareTo(maxPossibleBidAmtByBidder) < 0) {
					secondPossibleWinBid = possibleWinBid;
					possibleWinBid = maxPossibleBidAmtByBidder;
					winningBidder = bidder;
				} else if (secondPossibleWinBid.compareTo(maxPossibleBidAmtByBidder) < 0) {
					secondPossibleWinBid = maxPossibleBidAmtByBidder;
				}
			}

			while (possibleWinBid.subtract(winningBidder.getAutoIncAmount()).compareTo(secondPossibleWinBid) > 0) {
				possibleWinBid = possibleWinBid.subtract(winningBidder.getAutoIncAmount());
			}

		}
		return new WinnerBidData(winningBidder.getBidderID(), possibleWinBid);

	}

	private BigDecimal calculateMaxBidAmountByBidder(Bidder bidder) {
		BigDecimal nextBid = bidder.getStartBid();
		while (nextBid.add(bidder.getAutoIncAmount()).compareTo(bidder.getMaxBid()) <= 0) {
			nextBid = nextBid.add(bidder.getAutoIncAmount());
		}
		return nextBid;

	}

}
