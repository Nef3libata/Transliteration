package com.example.transliteration;

import android.content.Context;
import android.content.Intent;
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

//Letter activity

public class MainActivity extends AppCompatActivity {

    String wordStr;
    TextView resultTextView;
    EditText answerText;
    String result;
    TextView randomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        resultTextView = (TextView) findViewById(R.id.result);
        answerText = (EditText) findViewById(R.id.answer);
        randomText = (TextView) findViewById(R.id.randomText);

        findViewById(R.id.newBtn).setOnClickListener(buttonClickListener);
        findViewById(R.id.check).setOnClickListener(buttonClickListener);
        findViewById(R.id.numberBtn).setOnClickListener(buttonClickListener);
        generateRandomStr();
    }

    //generates a random encouragement

    public String rnBravo() {
        Random rand = new Random();
        List<String> givenList = Arrays.asList("Yay! That's correct!", "Good job", "Well done!");

        int randomIndex = rand.nextInt(givenList.size());
        String randomBravo = givenList.get(randomIndex);
        return randomBravo;

    }

    //generates random word

    public void generateRandomStr(){

        String Chars = "بپتثجچحخدذرزژسشصضطظعغفقکگلمنوهی";
        StringBuilder word = new StringBuilder();
        Random rnd = new Random();
        while (word.length() < 3) {
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

                    String answer = answerText.getText().toString();
                    String transliteration = wordStr.replace("آ", "a").replace("ب", "b").replace("۱", "1").replace("ث", "s").replace("پ", "p").replace("ت", "t").replace("ج", "j").replace("چ", "ch").replace("ح", "h").replace("خ", "kh").replace("د", "d").replace("ذ", "z").replace("ر", "r").replace("ز", "z").replace("ژ", "zh").replace("س", "s").replace("ش", "sh").replace("ص", "s").replace("ض", "z").replace("ط", "t").replace("ظ", "z").replace("ع", "'").replace("غ", "gh").replace("ف", "f").replace("ق", "gh").replace("ک", "k").replace("ل", "l").replace("م", "m").replace("ن", "n").replace("و", "u").replace("ه", "h").replace("ی", "i").replace("۳", "3").replace("۲", "2").replace("ا", "a").replace("اُ", "o").replace("اِ", "e").replace("اَ", "ä").replace("گ", "g").replace("۴", "4").replace("۵", "5").replace("۶", "6").replace("۷", "7").replace("۸", "8").replace("۹", "9").replace("۰", "0");
                    if (answer.contentEquals(transliteration)) {
                        result = rnBravo();
                    } else if (answer != transliteration){
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

                //Let's learn numbers button
                case R.id.numberBtn:

                    try {
                        Intent k = new Intent(MainActivity.this, MainActivity2.class);
                        startActivity(k);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }

            }
        }


    };
}