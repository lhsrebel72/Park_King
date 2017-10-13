package com.example.maupi.parkking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

//everything seems to be working but i still need to implement error checks
public class getPaymentInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_payment_info);

    }

    public void onSubmit(View view){
        PaymentInfo p = new PaymentInfo();
        if(1 == 1) {

            EditText address = (EditText) findViewById(R.id.address);
            EditText zip = (EditText) findViewById(R.id.zip);
            EditText cityState = (EditText) findViewById(R.id.cityState);
            EditText securityCode = (EditText) findViewById(R.id.securityCode);
            EditText expirationDate = (EditText) findViewById(R.id.expirationDate);
            EditText cardNum = (EditText) findViewById(R.id.cardNum);
            EditText name = (EditText) findViewById(R.id.name);
            EditText country = (EditText) findViewById(R.id.country);

            String addressString = address.getText().toString();
            String zipString = zip.getText().toString();
            String cityStateString = cityState.getText().toString();
            String securityCodeString = securityCode.getText().toString();
            String expirationDateString = expirationDate.getText().toString();
            String cardNumString = cardNum.getText().toString();
            String nameString = name.getText().toString();
            String countryString = country.getText().toString();

            p.setAddress(addressString);
            p.setCardNum(cardNumString);
            p.setCityState(cityStateString);
            p.setCountry(countryString);
            p.setExpDate(expirationDateString);
            p.setNameOnCard(nameString);
            p.setSecurityCode(securityCodeString);
            p.setZip(zipString);

            Toast c =  Toast.makeText(getPaymentInfo.this, "payment info accepted", Toast.LENGTH_SHORT);
            c.show();


            //helper.insertContact(c); insert into database using something like that?
        }
    }
}


