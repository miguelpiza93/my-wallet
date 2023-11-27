package com.my.wallet.Wallet.services;

import com.my.wallet.Wallet.entities.Expense;
import com.my.wallet.Wallet.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public List<Expense> getAll() {
        return expenseRepository.findAll();
    }

    public Expense create(Expense expense) {
        return expenseRepository.save(expense);
    }

    public void remove(Long expenseId) {
        boolean found = expenseRepository.existsById(expenseId);
        if(!found){
            throw new IllegalStateException("Expense with id " + expenseId + " does not exists");
        }
        expenseRepository.deleteById(expenseId);
    }
}
