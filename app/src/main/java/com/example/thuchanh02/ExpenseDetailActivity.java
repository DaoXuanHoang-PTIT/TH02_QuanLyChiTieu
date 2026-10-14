package com.example.thuchanh02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DecimalFormat;

public class ExpenseDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

        Expense expense = (Expense) getIntent().getSerializableExtra("EXPENSE");

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvAmount = findViewById(R.id.tvDetailAmount);
        TextView tvCategory = findViewById(R.id.tvDetailCategory);
        TextView tvDate = findViewById(R.id.tvDetailDate);
        TextView tvNote = findViewById(R.id.tvDetailNote);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnDelete = findViewById(R.id.btnDelete);

        if (expense != null) {
            DecimalFormat formatter = new DecimalFormat("#,###");
            tvName.setText(expense.getName());
            tvAmount.setText(formatter.format(expense.getAmount()) + " đ");
            tvCategory.setText(expense.getCategory());
            tvDate.setText(expense.getDate());
            tvNote.setText(expense.getNote().isEmpty() ? "(Không có ghi chú)" : expense.getNote());
        }

        btnBack.setOnClickListener(v -> finish());

        btnDelete.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            if (expense != null) {
                resultIntent.putExtra("DELETE_EXPENSE_ID", expense.getId());
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}