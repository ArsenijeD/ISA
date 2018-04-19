package com.example.service;

import java.util.ArrayList;

import com.example.domain.Ad;
import com.example.domain.Bid;

public interface BidService {

	ArrayList<Bid> getBidsByAd(Ad ad);
}
