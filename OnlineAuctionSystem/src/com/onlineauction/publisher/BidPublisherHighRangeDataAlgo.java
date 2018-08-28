package com.onlineauction.publisher;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onlineauction.model.Bidder;
import com.onlineauction.model.WinnerBidData;

public class BidPublisherHighRangeDataAlgo implements BidPublisher {

	public WinnerBidData getWinningBid(List<Bidder> bidders) {

		HashMap<Integer, BigDecimal> bidIdToCurrentBidAmtMap = new HashMap<>();

		WinnerBidData currentBidWinner = putStartBidAsCurrentBidInMapAndReturnTopBidder(bidders,
				bidIdToCurrentBidAmtMap);

		boolean winnerFound = false;
		int biddersIndexCount = 0;

		while (!winnerFound && bidders.size() > biddersIndexCount) {

			Bidder bidder = bidders.get(biddersIndexCount);
			biddersIndexCount++;

			if (bidder.getBidderID() != currentBidWinner.getBidderID()) {
				winnerFound = true;

				while (getcurrBidderNxtBidAmt(bidder, bidIdToCurrentBidAmtMap).compareTo(bidder.getMaxBid()) <= 0) {
					winnerFound = false;
					bidIdToCurrentBidAmtMap.put(bidder.getBidderID(),
							getcurrBidderNxtBidAmt(bidder, bidIdToCurrentBidAmtMap));

					if (bidIdToCurrentBidAmtMap.get(bidder.getBidderID())
							.compareTo(currentBidWinner.getBidAmount()) > 0) {
						currentBidWinner = new WinnerBidData(bidder.getBidderID(),
								bidIdToCurrentBidAmtMap.get(bidder.getBidderID()));
						break;
					}
				}

			}

			if (biddersIndexCount == bidders.size())
				biddersIndexCount = 0;
		}
		return getWinningBidder(bidders, currentBidWinner, bidIdToCurrentBidAmtMap);
	}

	private BigDecimal getcurrBidderNxtBidAmt(Bidder bidder, Map<Integer, BigDecimal> currentBidIdToBidAmtMap) {
		return currentBidIdToBidAmtMap.get(bidder.getBidderID()).add(bidder.getAutoIncAmount());
	}

	private WinnerBidData getWinningBidder(List<Bidder> bidders, WinnerBidData currentWinnerBidData,
			Map<Integer, BigDecimal> currentBidIdToBidAmtMap) {
		Bidder winner = null;
		for (Bidder bid : bidders) {
			if (currentBidIdToBidAmtMap.get(bid.getBidderID()).equals(currentWinnerBidData.getBidAmount())) {
				if (winner == null) {
					winner = bid;
				} else {
					if (winner.getTimestamp().after(bid.getTimestamp())) {
						winner = bid;
					}
				}
			}
		}
		return new WinnerBidData(winner.getBidderID(), currentWinnerBidData.getBidAmount());
	}

	private WinnerBidData putStartBidAsCurrentBidInMapAndReturnTopBidder(List<Bidder> bidders,
			Map<Integer, BigDecimal> currentBidIdToBidAmtMap) {
		BigDecimal currentMaxBid = BigDecimal.ZERO;
		int winnerId = 0;
		for (Bidder bidder : bidders) {
			if (bidder.getStartBid().compareTo(currentMaxBid) > 0) {
				currentMaxBid = bidder.getStartBid();
				winnerId = bidder.getBidderID();
			}
			currentBidIdToBidAmtMap.put(bidder.getBidderID(), bidder.getStartBid());
		}
		return new WinnerBidData(winnerId, currentMaxBid);
	}

}
