package com.example.thuchanh02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class AddExpenseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        EditText edtName = findViewById(R.id.edtExpenseName);
        EditText edtAmount = findViewById(R.id.edtAmount);
        Spinner spinnerCategory = findViewById(R.id.spinnerCategory);
        DatePicker datePicker = findViewById(R.id.datePicker);
        EditText edtNote = findViewById(R.id.edtNote);
        Button btnSave = findViewById(R.id.btnSave);

        String[] categories = {"Ăn uống", "Đi lại", "Học tập", "Giải trí", "Khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(adapter);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String amountStr = edtAmount.getText().toString().trim();

            if (name.isEmpty()) {
                edtName.setError("Tên khoản chi không được để trống");
                return;
            }
            if (amountStr.isEmpty() || Long.parseLong(amountStr) <= 0) {
                edtAmount.setError("Số tiền phải lớn hơn 0");
                return;
            }

            String category = spinnerCategory.getSelectedItem().toString();
            String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
            String note = edtNote.getText().toString().trim();

            Expense expense = new Expense(name, Long.parseLong(amountStr), category, date, note);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("NEW_EXPENSE", expense);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}