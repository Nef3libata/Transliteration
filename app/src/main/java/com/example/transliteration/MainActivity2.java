package com.example.transliteration;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

//Number Activity
public class MainActivity2 extends AppCompatActivity {
    String wordStr;
    TextView resultTextView;
    EditText answerText;
    TextView randomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        randomText = (TextView) findViewById(R.id.randomText);
        resultTextView = (TextView) findViewById(R.id.result);
        answerText = (EditText) findViewById(R.id.answer);

        findViewById(R.id.newBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.check).setOnClickListener(buttonClickListener);
        generateRandomStr();
    }

    //generates random encouragement
    public String rnBravo() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("Yay! That's correct!", "Good job!", "Well done!");

        int randomIndex = rand.nextInt(givenList.size());
        String randomBravo = givenList.get(randomIndex);
        return randomBravo;
    }

    //generates random number
    public void generateRandomStr(){

        String Chars = "۱۲۳۴۵۶۷۸۹۰";
        StringBuilder word = new StringBuilder();
        Random rnd = new Random();
        while (word.length() < 4) {
            int index = (int) (rnd.nextFloat() * Chars.length());
            word.append(Chars.charAt(index));
        }

        wordStr = word.toString();
        randomText.setText(wordStr);
    }


    private  View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View view) {

            //Hides keyboard by button click
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

            switch (view.getId()) {
                //Check button
                case R.id.check:
                    String result = "Welcome";

                    String answer = answerText.getText().toString();
                    String transliteration = wordStr.replace("۱", "1").replace("۳", "3").replace("۲", "2").replace("۴", "4").replace("۵", "5").replace("۶", "6").replace("۷", "7").replace("۸", "8").replace("۹", "9").replace("۰", "0");
                    if (transliteration.equals(answer)) {
                        result = rnBravo();
                    } else {
                        result = "Not exactly, it's actually:" + " " + transliteration;
                    }
                    resultTextView.setText(result + " ");

                    break;

                //New button
                case R.id.newBtn:

                    resultTextView.setText(" ");
                    answerText.setText(" ");
                    generateRandomStr();
                    break;


            }
        }


    };
}