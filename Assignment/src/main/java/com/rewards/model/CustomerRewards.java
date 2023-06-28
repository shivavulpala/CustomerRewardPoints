package com.rewards.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRewards {

    int id;
    int customerId;
    String customerName;
    String txnMonth;
    int txnAmount;
    int rewardPts;
}
