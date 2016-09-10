package edu.orangecoastcollege.cs273.dkim127.shippingcalculator;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private Context mContext = this;

    private ShipItem mShipItem;

    private TextView mAddedCostTextView;
    private TextView mBaseCostTextView;
    private TextView mTotalCostTextView;
    private EditText mUserInputEditText;

    private NumberFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize the model
        mShipItem = new ShipItem();

        // initialize currency formatter
        formatter = NumberFormat.getCurrencyInstance();

        // initialize and link up controls to their respective views
        mAddedCostTextView = (TextView) findViewById(R.id.addedCostTextView);
        mBaseCostTextView = (TextView) findViewById(R.id.baseCostTextView);
        mTotalCostTextView = (TextView) findViewById(R.id.totalShippingCostTextView);
        mUserInputEditText = (EditText) findViewById(R.id.userInputEditText);

        // set display to their default values
        mAddedCostTextView.setText(formatter.format(0));
        mBaseCostTextView.setText(formatter.format(0));
        mTotalCostTextView.setText(formatter.format(0));
        mUserInputEditText.setText("0");

        // add listener for the user input
        mUserInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try
                {
                    mShipItem.setWeight(Double.parseDouble(s.toString()));
                }
                catch (NumberFormatException e)
                {
                    // something went terribly wrong
                    new AlertDialog.Builder(mContext)
                            .setTitle("OH MY GOD")
                            .setMessage("WHAT HAVE YOU DONE??")
                            .setPositiveButton("I'M SO SORRY!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "YOU ARE A TERRIBLE PERSON", Toast.LENGTH_LONG)
                                            .show();
                                }
                            })
                            .show();
                    mShipItem.setWeight(0);
                    mUserInputEditText.setText("0");
                }
                finally
                {
                    mAddedCostTextView.setText(String.valueOf(
                            formatter.format(mShipItem.getAddedCost())));
                    mBaseCostTextView.setText(String.valueOf(
                            formatter.format(mShipItem.getBaseCost())));
                    mTotalCostTextView.setText(String.valueOf(
                            formatter.format(mShipItem.getTotalCost())));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
    }
}
