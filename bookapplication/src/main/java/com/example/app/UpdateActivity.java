package com.example.app;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/5/18.
 */
public class UpdateActivity extends AppCompatActivity {

    private Button btn_submit;
    private EditText bookName;
    private EditText bookAuthor;
    private EditText bookPrice;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        String name = getIntent().getStringExtra("name");
        final int id = getIntent().getIntExtra("id",0);
        String author = getIntent().getStringExtra("author");
        float price = getIntent().getFloatExtra("price", 0);
        btn_submit = (Button) findViewById(R.id.submit);
        bookName = (EditText) findViewById(R.id.ed_book_name);
        bookAuthor = (EditText) findViewById(R.id.ed_book_author);
        bookPrice = (EditText) findViewById(R.id.ed_book_price);
        bookName.setText(name);
        bookAuthor.setText(author);
        bookPrice.setText(String.valueOf(price));
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookName.getText().toString();
                String author = bookAuthor.getText().toString();
                float price =Float.valueOf(bookPrice.getText().toString()) ;
                Uri uri = Uri.parse("content://com.example.demo.mycontentprovider");
                ContentValues contentValues = new ContentValues();
                contentValues.put("book_name",name);
                contentValues.put("book_author",author);
                contentValues.put("book_price",price);
                ContentResolver contentResolver = getContentResolver();
                contentResolver.update(uri,contentValues,"_id=?",new String[]{String.valueOf(id)});

            }

        });


    }
    
}
