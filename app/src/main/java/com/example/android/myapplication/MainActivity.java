package com.example.android.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        Log.v("Main Activity","Name :"+ name);

        CheckBox cbTopping = (CheckBox) findViewById(R.id.cbTopping);
        boolean hasWhippedCream = cbTopping.isChecked();
        Log.v("Main Activity", "Use Whipped Cream : " + hasWhippedCream);

        CheckBox cbToppingChocolate = (CheckBox) findViewById(R.id.cbToppingChocolate);
        boolean hasChocolate = cbToppingChocolate.isChecked();
        Log.v("Main Activity", "Use Chocolate : " + hasChocolate);

        int price = calculatePrice(hasWhippedCream,hasChocolate);
        String priceMessage= createOrderSummary(name,price, hasWhippedCream, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, name + " Order Form");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    // g perlu lagi soalnya sudah di apus
     //   displayMessage(priceMessage);


        /**
         * buat display angkanya
         display(quantity);
         displayPrice(quantity*5);
         */
    }
    public int calculatePrice(boolean addWhipCream, boolean addChocolate){
        int basePrice = 5;
        //add 1 dollar for whip cream
        if(addWhipCream){
           basePrice = basePrice + 1;
        }
        //add 2 dollar for chocolate
        if(addChocolate){
            basePrice = basePrice + 2;
        }
        return quantity*basePrice;
    }

    /**
     * @param name of the customer
     * @param price of the order
     * @param addWhipCream is whether the user want whipped cream or not
     * @param addChocolate is whether the user want chocolate or not
     * @return text summary
     */
    private String createOrderSummary(String name,int price, boolean addWhipCream, boolean addChocolate){
        String priceMessage= "Name : " + name;
        priceMessage = priceMessage + "\nAdd Whipped Cream? : " + addWhipCream;
        priceMessage = priceMessage + "\nAdd Chocolate?  : " + addChocolate;
        priceMessage = priceMessage + "\nQuantity : " + quantity;
        priceMessage = priceMessage + "\nTotal $" + price;
        priceMessage = priceMessage + "\nThankyou";
        return priceMessage;
    }

    public void increment(View view) {
        if(quantity==100){
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {
        if(quantity == 1){
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * biar yg kluar angka

     private void displayPrice(int number) {
     TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
     priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
     }
     */

    /**
     * This method displays the given text on the screen.
     */
    /**
     // g perlu lagi soalnya ud di apus yang order summary text view
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/
}
