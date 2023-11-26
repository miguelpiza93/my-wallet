package com.my.wallet.Wallet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.wallet.Wallet.entities.Expense;
import com.my.wallet.Wallet.services.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ExpenseControllerTest {

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    void testGetAll() throws Exception {
        // Arrange
        List<Expense> expectedExpenses = Arrays.asList(
                new Expense("Expense 1", 100.0, "Food"),
                new Expense("Expense 2", 150.0, "Travel")
        );

        when(expenseService.getAll()).thenReturn(expectedExpenses);

        // Act and Assert
        mockMvc.perform(get("/api/v1/expense"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].description").value("Expense 1"))
                .andExpect(jsonPath("$[1].amount").value(150.0));

        verify(expenseService, times(1)).getAll();
    }

    @Test
    void testCreate() throws Exception {
        // Arrange
        Expense expense = new Expense("Expense 1", 100.0, "Food");

        // Act and Assert
        mockMvc.perform(post("/api/v1/expense")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(expense)))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).create(any(Expense.class));
    }

    @Test
    void testRemove() throws Exception {
        // Arrange
        Long expenseId = 1L;

        // Act and Assert
        mockMvc.perform(delete("/api/v1/expense/{expenseId}", expenseId))
                .andExpect(status().isOk());

        verify(expenseService, times(1)).remove(expenseId);
    }
}
