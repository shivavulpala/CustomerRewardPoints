package com.rewards.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardsResponse {

    List<CustomerRewards> customerRewardsList;
    int totalRewards;
    ApiError error;
}
