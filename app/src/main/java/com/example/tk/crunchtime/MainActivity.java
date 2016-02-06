package com.example.tk.crunchtime;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int cal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("after set content view");

        EditText jjmin = (EditText) findViewById(R.id.jjmin);
        EditText jogmin = (EditText) findViewById(R.id.jogmin);
        EditText pureps = (EditText) findViewById(R.id.pureps);
        EditText sureps = (EditText) findViewById(R.id.sureps);
        EditText calories = (EditText) findViewById(R.id.editText5);

        final EditText[] eTexts = {jjmin, jogmin, pureps, sureps, calories};


        jjmin.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence charText = v.getText();
                    int minutes;
                    if (charText.length() != 0) {
                        minutes = Integer.parseInt(charText.toString());
                    } else {
                        minutes = 0;
                    }
                    cal = (int) (minutes * 10);
                    update(v, eTexts);
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        jogmin.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence charText = v.getText();
                    int minutes;
                    if (charText.length() != 0) {
                        minutes = Integer.parseInt(charText.toString());
                    } else {
                        minutes = 0;
                    }
                    cal = (int) (minutes*100/12);
                    update(v, eTexts);
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        pureps.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence charText = v.getText();
                    int minutes;
                    if (charText.length() != 0) {
                        minutes = Integer.parseInt(charText.toString());
                    } else {
                        minutes = 0;
                    }
                    cal = (int) (minutes * 10 / 35);
                    update(v, eTexts);
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        sureps.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence charText = v.getText();
                    int minutes;
                    if (charText.length() != 0) {
                        minutes = Integer.parseInt(charText.toString());
                    } else {
                        minutes = 0;
                    }
                    cal = (int) (minutes / 2);
                    update(v, eTexts);
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        calories.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    CharSequence charText = v.getText();
                    if (charText.length() != 0) {
                        cal = Integer.parseInt(charText.toString());
                    } else {
                        cal = 0;
                    }
                    update(v, eTexts);
                    hideKeyboard();
                    handled = true;
                }
                return handled;
            }
        });
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = this.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void update(View v, EditText[] eTexts) {
        for (EditText eText : eTexts) {
            int id = eText.getId();
            if (id != v.getId()) {
                eText.setText(Integer.toString(calculateEquivalentWorkout(id)));
            }
        }
    }

    protected int calculateEquivalentWorkout(int id) {
        switch (id) {
            case R.id.pureps: {
                return (int) (cal*35/10);
            }case R.id.sureps: {
                return (int) (cal*2);
            }case R.id.jjmin: {
                return (int) (cal/10);
            }case R.id.jogmin: {
                return (int) (cal*12/100);
            }case R.id.editText5: {
                return cal;
            }
        }
        return 0;
    }
}
