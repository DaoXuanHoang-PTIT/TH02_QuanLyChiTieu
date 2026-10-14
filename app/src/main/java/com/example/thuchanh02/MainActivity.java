package com.example.thuchanh02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Expense> expenseList;
    private ExpenseAdapter adapter;
    private TextView tvTotalAmount;

    private final ActivityResultLauncher<Intent> addExpenseLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Expense newExpense = (Expense) result.getData().getSerializableExtra("NEW_EXPENSE");
                    if (newExpense != null) {
                        expenseList.add(newExpense);
                        adapter.notifyDataSetChanged();
                        updateTotalAmount();
                    }
                }
            });


    private final ActivityResultLauncher<Intent> detailExpenseLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String deleteId = result.getData().getStringExtra("DELETE_EXPENSE_ID");
                    if (deleteId != null) {
                        expenseList.removeIf(expense -> expense.getId().equals(deleteId));
                        adapter.notifyDataSetChanged();
                        updateTotalAmount();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalAmount = findViewById(R.id.tvTotalAmount);
        Button btnAddExpense = findViewById(R.id.btnAddExpense);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        expenseList = new ArrayList<>();
        expenseList.add(new Expense("Ăn sáng", 30000, "Ăn uống", "12/10/2026", ""));
        expenseList.add(new Expense("Đổ xăng", 50000, "Đi lại", "12/10/2026", ""));
        expenseList.add(new Expense("Mua sách", 120000, "Học tập", "13/10/2026", "Sách Lập trình Android"));

        adapter = new ExpenseAdapter(expenseList, selectedExpense -> {
            Intent intent = new Intent(MainActivity.this, ExpenseDetailActivity.class);
            intent.putExtra("EXPENSE", selectedExpense);
            detailExpenseLauncher.launch(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        updateTotalAmount();

        btnAddExpense.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddExpenseActivity.class);
            addExpenseLauncher.launch(intent);
        });
    }

    private void updateTotalAmount() {
        long total = 0;
        for (Expense expense : expenseList) {
            total += expense.getAmount();
        }
        DecimalFormat formatter = new DecimalFormat("#,###");
        tvTotalAmount.setText(formatter.format(total) + " đ");
    }
}