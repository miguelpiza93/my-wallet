package com.my.wallet.Wallet.services;

import com.my.wallet.Wallet.entities.Expense;
import com.my.wallet.Wallet.repositories.ExpenseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExpenseServiceTest {

    @Mock
    private ExpenseRepository expenseRepository;

    private ExpenseService expenseService;

    @BeforeEach
    void setUp() {
        expenseService = new ExpenseService(expenseRepository);
    }

    @Test
    void testGetAll() {
        // Arrange
        Expense expense1 = new Expense("Expense 1", 100.0, "Food");
        Expense expense2 = new Expense("Expense 2", 150.0, "Food");
        List<Expense> expectedExpenses = Arrays.asList(expense1, expense2);

        when(expenseRepository.findAll()).thenReturn(expectedExpenses);

        // Act
        List<Expense> actualExpenses = expenseService.getAll();

        // Assert
        assertEquals(expectedExpenses, actualExpenses);
        verify(expenseRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        // Arrange
        Expense expense = new Expense("Expense 1", 100.0, "Food");

        // Act
        expenseService.create(expense);

        // Assert
        verify(expenseRepository, times(1)).save(expense);
    }

    @Test
    void testRemoveExpenseExists() {
        // Arrange
        Long expenseId = 1L;
        when(expenseRepository.existsById(expenseId)).thenReturn(true);

        // Act
        expenseService.remove(expenseId);

        // Assert
        verify(expenseRepository, times(1)).deleteById(expenseId);
    }

    @Test
    void testRemoveExpenseDoesNotExist() {
        // Arrange
        Long expenseId = 1L;
        when(expenseRepository.existsById(expenseId)).thenReturn(false);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> expenseService.remove(expenseId));
    }
}
