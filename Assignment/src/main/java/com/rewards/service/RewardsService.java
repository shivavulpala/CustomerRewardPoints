package com.rewards.service;

import com.rewards.entity.PurchaseInfo;
import com.rewards.model.CustomerRewards;
import com.rewards.model.RewardsResponse;
import com.rewards.repository.RewardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RewardsService {


    @Autowired
    RewardsRepository rewardsRepository;

    List<CustomerRewards> customerRewardsList = new ArrayList<>();

   public RewardsResponse calculateRewardPoints(int customerId){
       List<PurchaseInfo> purchaseInfoList = new ArrayList<>();
       RewardsResponse rewardsResponse = new RewardsResponse();
       int totalRewards = 0;
       try {
           purchaseInfoList = rewardsRepository.findById(customerId);
       }catch (Exception e){
           log.error("exception", e);
       }
      for(PurchaseInfo purchaseInfo:purchaseInfoList){
          CustomerRewards customerRewards =new CustomerRewards();
          if (purchaseInfo.getCustomer_id() == customerId) {
              customerRewards.setCustomerId(purchaseInfo.getCustomer_id());
              customerRewards.setCustomerName(purchaseInfo.getCustomer_name());
              customerRewards.setTxnMonth(purchaseInfo.getTxn_month());
              customerRewards.setId(purchaseInfo.getId());
              customerRewards.setTxnAmount(purchaseInfo.getTxn_amount());
              int rewardPts = caculateRewards(customerRewards.getTxnAmount());
              customerRewards.setRewardPts(rewardPts);
              totalRewards += rewardPts;
              customerRewardsList.add(customerRewards);
          }
      }
      rewardsResponse.setTotalRewards(totalRewards);
      rewardsResponse.setCustomerRewardsList(customerRewardsList);
      log.info("Total size of customer rewards list - {}", customerRewardsList.size());
      return rewardsResponse;
   }

    private int caculateRewards(int txn_amount) {

        int points=0;
        if (txn_amount <= 50) {
            log.info("No points earned as transaction amount is less than 50");
        } else if (txn_amount > 100) {
            int rewardsBelow100 = 2 * (txn_amount - 100);
            int rewardsAbove100= 100 - 50;
            points = rewardsBelow100+ rewardsAbove100;
        } else if (txn_amount == 100) {
            int rewardsBelow100= 0;
            int rewardsAbove100= 100 - 50;
            points = rewardsBelow100+ rewardsAbove100;
        } else  {
            int rewardsBelow100= 0;
            int rewardsAbove100= txn_amount - 50;
            points = rewardsBelow100+ rewardsAbove100;
        }
        log.info("Total points earned for transaction amount-{} is {} ", txn_amount, points);
        return points;
   }
}
