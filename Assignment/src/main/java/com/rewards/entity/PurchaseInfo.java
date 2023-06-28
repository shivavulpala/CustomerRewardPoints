package com.rewards.entity;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

@Entity
@EntityScan
@Data
@Table(name="purchase_info")
public class PurchaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "customer_id")
    private int customer_id;
    @Column(name = "customer_name")
    private String customer_name;
    @Column(name = "txn_amount")
    private int txn_amount;
    @Column(name = "txn_month")
    private String txn_month;
}