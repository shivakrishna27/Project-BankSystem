package com.example.banksystem.service;

import com.example.banksystem.model.Transaction;
import com.example.banksystem.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class BankService {

    @Autowired
    private TransactionRepository transactionRepository;

    private static final int MAX_RECENT_TRANSACTIONS = 5;

    // Arrays to track recent transaction types
    private String[] recentTransactionTypes = new String[MAX_RECENT_TRANSACTIONS];
    private int transactionIndex = 0;


    public void deposit(String pin, int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Deposit amount must be positive");
        }

        Transaction tx = new Transaction(pin, new Date(), "Deposit", amount);
        transactionRepository.save(tx);

        addRecentTransactionType("Deposit");
    }


    public void withdraw(String pin, int amount) {
        if (amount <= 0) {
            throw new RuntimeException("Withdrawal amount must be positive");
        }

        if (getBalance(pin) < amount) {
            throw new RuntimeException("Insufficient Balance");
        }

        Transaction tx = new Transaction(pin, new Date(), "Withdrawal", amount);
        transactionRepository.save(tx);

        addRecentTransactionType("Withdrawal");
    }

    public int getBalance(String pin) {
        List<Transaction> txs = transactionRepository.findByPin(pin); // Collections Framework

        int balance = 0; //arithametic
        for (Transaction tx : txs) {
            if ("Deposit".equals(tx.getType())) { //control statements
                balance += tx.getAmount();
            } else if ("Withdrawal".equals(tx.getType())) {
                balance -= tx.getAmount();
            }
        }
        return balance;
    }


    public List<Transaction> getMiniStatement(String pin) { //Collections frameWork
        return transactionRepository.findByPin(pin);
    }


    public String[] getRecentTransactionTypes() {
        return Arrays.copyOf(recentTransactionTypes, transactionIndex);
    }

    private void addRecentTransactionType(String type) {
        if (transactionIndex < MAX_RECENT_TRANSACTIONS) { // Conditional Statements
            recentTransactionTypes[transactionIndex++] = type;
        } else {

            for (int i = 1; i < MAX_RECENT_TRANSACTIONS; i++) {
                recentTransactionTypes[i - 1] = recentTransactionTypes[i];
            }
            recentTransactionTypes[MAX_RECENT_TRANSACTIONS - 1] = type;
        }
    }
}
