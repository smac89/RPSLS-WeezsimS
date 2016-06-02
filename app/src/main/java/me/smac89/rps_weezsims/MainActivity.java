package me.smac89.rps_weezsims;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner player1Choice, player2Choice;
    private TextView p1prompt, p2prompt;
    private Button action;

    public static final String TAG = MainActivity.class.getSimpleName();

    static {
        try {
            System.loadLibrary("c++_shared");
            Log.d(TAG, "Loaded the shared library");
            System.loadLibrary("rpsls");
            Log.d(TAG, "The native rpsls library was loaded successfully");
        } catch (UnsatisfiedLinkError ule) {
            Log.e(TAG, "Warning: Could not load native library: " + ule.getMessage());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player1Choice = (Spinner) findViewById(R.id.player1Choice);
        player2Choice = (Spinner) findViewById(R.id.player2Choice);
        p1prompt = (TextView) findViewById(R.id.player1Prompt);
        p2prompt = (TextView) findViewById(R.id.player2Prompt);
        action = (Button) findViewById(R.id.action_button);

        action.setOnClickListener(this);

        // Set the names of the players, this could be a real name or just use these
        // placeholders
        p1prompt.setText(String.format(p1prompt.getText().toString(), "Player 1"));
        p2prompt.setText(String.format(p2prompt.getText().toString(), "Player 2"));

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.rps_choices, android.R.layout.simple_spinner_dropdown_item);
        player1Choice.setAdapter(adapter);
        player2Choice.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        try {
            String p1Choice = new String(player1Choice.getSelectedItem().toString().getBytes(),
                    "UTF-8").toLowerCase();
            String p2Choice = new String(player2Choice.getSelectedItem().toString().getBytes(),
                    "UTF-8").toLowerCase();

            String msg = rpsls.rps(p1Choice, p2Choice);
            showDialogWithMessage(msg);
        } catch (UnsupportedEncodingException ue) {
            Log.e(TAG, "Could not convert the input to UTF-8");
        }
    }

    private void showDialogWithMessage(String msg) {
        new LovelyStandardDialog(this)
                .setTitle("Match Result")
                .setMessage(msg)
                .show();
    }
}
