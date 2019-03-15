package com.itrainasia.myform;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText nameEditText, emailEditText, cellEditText,
            phoneEditText, messageEditText;
    Spinner spinner;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        cellEditText = findViewById(R.id.cellEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        messageEditText = findViewById(R.id.messageEditText);
        spinner = findViewById(R.id.spinner);
        autoCompleteTextView = findViewById(R.id.autoCompleteTextView);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.departments,
                android.R.layout.simple_list_item_1);

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> autoCadapter = ArrayAdapter.createFromResource(
                this, R.array.states,
                android.R.layout.simple_expandable_list_item_1);
        autoCadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        autoCompleteTextView.setAdapter(autoCadapter);

        spinner.setOnItemSelectedListener(this);


    }

    public void sendPressed(View view) {

        AlertDialog.Builder  myAlertBuilder= new AlertDialog.Builder(MainActivity.this);
        myAlertBuilder.setTitle("Please confirm!");
        myAlertBuilder.setMessage("Are you sure to send the info");
        myAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "Name is " + nameEditText.getText().toString() +
                            " Email is " + emailEditText.getText().toString() +
                        " Cell is " + cellEditText.getText().toString() +
                        " Phone is " + phoneEditText.getText().toString() +
                        " Message is " + messageEditText.getText().toString() ;
                Log.d("debug", message);
            }
        });
        myAlertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "User cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        });
        myAlertBuilder.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
Toast.makeText(MainActivity.this,
        "Items selected is "+ ((TextView)view).getText().toString()
        ,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.about_us_menu:
                Intent intent = new Intent(MainActivity.this,
                        AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.send_email_menu:
                String[] addresses =  {"feedback@itrain.com.my"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/html");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Second day feedback");
                emailIntent.putExtra(Intent.EXTRA_EMAIL,addresses);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Here is the feedback");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
            case R.id.exit_menu:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
