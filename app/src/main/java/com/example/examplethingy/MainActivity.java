package com.example.examplethingy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static ThingyAction stringToAction(String str) {
        switch (str) {
            case "Count words":
                return ThingyAction.COUNT_WORDS;
            case "Sort lines":
                return ThingyAction.SORT_LINES;
            case "Sort words":
                return ThingyAction.SORT_WORDS;
            case "To lower case":
                return ThingyAction.LOWER_CASE;
            case "To upper case":
                return ThingyAction.UPPER_CASE;
            default:
                return ThingyAction.INVALID;
        }
    }

    private static String join(String[] strings, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i != 0) {
                builder.append(delimiter);
            }
            builder.append(strings[i]);
        }
        return builder.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] choices = {
            "Count words",
            "Sort lines",
            "Sort words",
            "To lower case",
            "To upper case",
        };
        Spinner actionChoice = findViewById(R.id.action_choice);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            choices
        );
        actionChoice.setAdapter(adapter);
    }

    public void onAction(View view) {
        Spinner actionChoice = findViewById(R.id.action_choice);
        String string = actionChoice.getSelectedItem().toString();
        ThingyAction action = stringToAction(string);

        EditText inputText = findViewById(R.id.input_text);
        EditText outputText = findViewById(R.id.output_text);
        String input = inputText.getText().toString();
        String output;

        switch (action) {
            case INVALID:
                output = "Invalid option!";
                break;
            case COUNT_WORDS:
                output = Integer.toString(input.split("\\s+").length);
                break;
            case LOWER_CASE:
                output = input.toLowerCase();
                break;
            case UPPER_CASE:
                output = input.toUpperCase();
                break;
            case SORT_LINES:
                String[] sorted = input.split("\n");
                Arrays.sort(sorted);
                output = join(sorted, "\n");
                break;
            case SORT_WORDS:
                String[] sorted2 = input.split("\\s+");
                Arrays.sort(sorted2);
                output = join(sorted2," ");
                break;
            default:
                output = "Huh???";
                break;
        }

        outputText.setText(output);
    }
}

enum ThingyAction {
    INVALID,

    COUNT_WORDS,
    LOWER_CASE,
    UPPER_CASE,
    SORT_LINES,
    SORT_WORDS,
}
