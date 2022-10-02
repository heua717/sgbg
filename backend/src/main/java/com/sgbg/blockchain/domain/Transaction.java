package com.sgbg.blockchain.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long id;

    private String hash;

    private String nonce;

    private String blockHash;

    private String blockNumber;

    private String transactionIndex;

    private String fromHash;

    private String toHash;

    private String value;

    private String gasPrice;

    private String gas;

    private String input;

    private String creates;

    private String publicKey;

    private String raw;

    private String r;

    private String s;

    private String v;

    private String storedAt;

    private Boolean relatedToMoney;
    // 돈 관련 있는 것과 아닌 것으로 나누고 싶다고 누군가가 말했음


    @Builder
    public Transaction(long id, String hash, String nonce, String blockHash, String blockNumber, String transactionIndex, String fromHash, String toHash, String value, String gasPrice, String gas, String input, String creates, String publicKey, String raw, String r, String s, String v, String storedAt, Boolean relatedToMoney) {
        this.id = id;
        this.hash = hash;
        this.nonce = nonce;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.transactionIndex = transactionIndex;
        this.fromHash = fromHash;
        this.toHash = toHash;
        this.value = value;
        this.gasPrice = gasPrice;
        this.gas = gas;
        this.input = input;
        this.creates = creates;
        this.publicKey = publicKey;
        this.raw = raw;
        this.r = r;
        this.s = s;
        this.v = v;
        this.storedAt = storedAt;
        this.relatedToMoney = relatedToMoney;
    }
}
