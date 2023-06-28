package com.rewards.controller;

import com.rewards.model.ApiError;
import com.rewards.model.RewardsResponse;
import com.rewards.service.RewardsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RewardsController {

    @Autowired
    RewardsService rewardsService;//obj

    @ApiOperation(value = "rewards", nickname = "rewards")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server error"),
            @ApiResponse(code = 404, message = "Service not found"),
            @ApiResponse(code = 200, message = "rewards API returns reward points",
                    response = String.class, responseContainer = "String") })
    @GetMapping("/rewards/rewardpoints/{customerId}")
    public ResponseEntity<RewardsResponse> rewardPoints(@PathVariable int customerId) {

        RewardsResponse rewardsResponse = new RewardsResponse();
        if (customerId == 0 || String.valueOf(customerId).length() != 5) {
            rewardsResponse.setError(new ApiError(HttpStatus.NOT_FOUND, "No data found for CustomerId"));
            return new ResponseEntity<>(rewardsResponse, HttpStatus.NOT_FOUND);
        } else{
            rewardsResponse = rewardsService.calculateRewardPoints(customerId);
            return new ResponseEntity<>(rewardsResponse, HttpStatus.OK);
        }
    }
}