package com.example.app;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_submit;
    private EditText bookName;
    private EditText bookAuthor;
    private EditText bookPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn_submit = (Button) findViewById(R.id.submit);
        bookName = (EditText) findViewById(R.id.ed_book_name);
        bookAuthor = (EditText) findViewById(R.id.ed_book_author);
        bookPrice = (EditText) findViewById(R.id.ed_book_price);
        btn_submit.setOnClickListener(this);
    }





    @Override
    public void onClick(View v) {
        Uri uri = Uri.parse("content://com.example.demo.mycontentprovider");
        ContentValues contentValues = new ContentValues();
        String book_name = bookName.getText().toString();
        String book_author = bookAuthor.getText().toString();
        float f = Float.valueOf(bookPrice.getText().toString());
        contentValues.put("book_name", book_name);
        contentValues.put("book_price", f);
        contentValues.put("book_author",book_author);
//        Log.d("TAG", "onClick: "+book_name+book_author+f);
        getContentResolver().insert(uri, contentValues);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
