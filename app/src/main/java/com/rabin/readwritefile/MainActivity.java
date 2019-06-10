package com.rabin.readwritefile;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button button, readBtn;
    private EditText editText;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        readBtn = findViewById(R.id.read);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.result);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                    String message = editText.getText().toString();
                    writeToFile(message);
                    Toast.makeText(MainActivity.this,"Data written to file",
                            Toast.LENGTH_LONG).show();
                    textView.setText("  ");
                }
            }
        });

        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(readFromFile());
            }
        });
    }

    private void writeToFile(String message) {
        // Context.MODE_PRIVATE means not sharing data with other application
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("todoList.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(message);
            outputStreamWriter.close(); // closing the stream

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readFromFile() {
        String result = "";
        InputStream inputStream = null;
        try {
            inputStream = openFileInput("todoList.txt");

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String tempString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while (((tempString = bufferedReader.readLine()) != null)) {
                    stringBuilder.append(tempString);
                }
                inputStream.close();
                result = stringBuilder.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
