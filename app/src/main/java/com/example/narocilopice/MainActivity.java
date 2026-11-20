package com.example.narocilopice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_ITEMS = "items";

    private final List<String> mCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinnerPizza);
        RadioGroup rgSize = findViewById(R.id.rgSize);
        CheckBox cbCheese = findViewById(R.id.cbCheese);
        CheckBox cbMushrooms = findViewById(R.id.cbMushrooms);
        CheckBox cbOlives = findViewById(R.id.cbOlives);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnOrder = findViewById(R.id.btnOrder);
        RecyclerView rvCart = findViewById(R.id.rvCart);

        // Spinner: seznam pic iz strings.xml
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(
                this, R.array.pizza_types, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        // RecyclerView z adapterjem
        rvCart.setLayoutManager(new LinearLayoutManager(this));
        PizzaAdapter adapter = new PizzaAdapter(mCart, item ->
                Toast.makeText(this, getString(R.string.selected_toast, item),
                        Toast.LENGTH_SHORT).show());
        rvCart.setAdapter(adapter);

        // Gumb: dodaj v košarico
        btnAdd.setOnClickListener(v -> {
            String pizza = spinner.getSelectedItem().toString();
            RadioButton selectedSize = findViewById(rgSize.getCheckedRadioButtonId());
            String size = selectedSize.getText().toString();

            StringBuilder toppings = new StringBuilder();
            appendTopping(toppings, cbCheese);
            appendTopping(toppings, cbMushrooms);
            appendTopping(toppings, cbOlives);

            String item = pizza + " (" + size + ")";
            if (toppings.length() > 0) {
                item = item + " + " + toppings;
            }

            adapter.dodaj(item);
            rvCart.scrollToPosition(adapter.getItemCount() - 1);
            Toast.makeText(this, getString(R.string.added_toast, item),
                    Toast.LENGTH_SHORT).show();

            cbCheese.setChecked(false);
            cbMushrooms.setChecked(false);
            cbOlives.setChecked(false);
        });

        // Gumb: odpri novo aktivnost
        btnOrder.setOnClickListener(v -> {
            if (mCart.isEmpty()) {
                Toast.makeText(this, R.string.cart_empty, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putStringArrayListExtra(EXTRA_ITEMS, new ArrayList<>(mCart));
            startActivity(intent);
        });
    }

    private void appendTopping(StringBuilder sb, CheckBox checkBox) {
        if (checkBox.isChecked()) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(checkBox.getText());
        }
    }
}