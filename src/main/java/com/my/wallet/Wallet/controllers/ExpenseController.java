package com.my.wallet.Wallet.controllers;

import com.my.wallet.Wallet.entities.Expense;
import com.my.wallet.Wallet.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<Expense> getAll() {
        return expenseService.getAll();
    }

    @PostMapping
    public void create(@RequestBody Expense expense){
        expenseService.create(expense);
    }

}
