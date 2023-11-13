package com.my.wallet.Wallet.services;

import com.my.wallet.Wallet.entities.Expense;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    public List<Expense> getAll() {
        return List.of(
                new Expense(
                        1L,
                        "Cine",
                        25000.0,
                        "Entretenimiento"
                )
        );
    }
}
