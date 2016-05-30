package com.example.app;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //ui
    private ListView bookListView;
    private Button btnAdd;
    private Button btnDel;
    private final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookListView = (ListView) findViewById(R.id.book_list);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnDel = (Button) findViewById(R.id.btn_del);
        getContacts();

    }

    @Override
    protected void onResume() {
        Uri uri = Uri.parse("content://com.example.demo.mycontentprovider");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        cursor.requery();
        super.onResume();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                Intent intent = new Intent(this, AddActivity.class);
                startActivity(intent);
                break;
        }

    }

    private void getContacts() {
        Uri uri = Uri.parse("content://com.example.demo.mycontentprovider");
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        MyCuscorAdapter adapter = new MyCuscorAdapter(this, cursor, true);
        bookListView.setAdapter(adapter);

    }



    class MyCuscorAdapter extends CursorAdapter {

        public MyCuscorAdapter(Context context, Cursor c, boolean autoRequery) {
            super(context, c, autoRequery);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.book_item, null);
            ViewHolder holder = new ViewHolder(view);
            view.setTag(holder);
            return view;
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {
            ViewHolder holder = (ViewHolder) view.getTag();
            final String bookName = cursor.getString(cursor.getColumnIndex("book_name"));
            final String bookAuthor = cursor.getString(cursor.getColumnIndex("book_author"));
            final float price = cursor.getFloat(cursor.getColumnIndex("book_price"));
            holder.tv_name.setText(bookName);
            holder.tv_author.setText(bookAuthor);
            holder.tv_price.setText(String.valueOf(price));
            holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   int id =  cursor.getInt(cursor.getColumnIndex("_id"));
                Intent intent = new Intent(MainActivity.this,UpdateActivity.class);
                    intent.putExtra("name",bookName);
                    intent.putExtra("author",bookAuthor);
                    intent.putExtra("price",price);
                    intent.putExtra("id",id);
                    startActivity(intent);

                }
            });
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri uri = Uri.parse("content://com.example.demo.mycontentprovider");
                    ContentResolver contentResolver = getContentResolver();
                    contentResolver.delete(uri,"_id=?",new String[]{String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")))});
                    cursor.requery();
                }
            });
        }

    }

    class ViewHolder {
        private TextView tv_name;
        private TextView tv_author;
        private TextView tv_price;
        public Button btnUpdate;
        public Button btnDelete;

        public ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.tv_book_name);
            tv_author = (TextView) view.findViewById(R.id.tv_book_author);
            tv_price = (TextView) view.findViewById(R.id.tv_book_price);
            btnUpdate = (Button) view.findViewById(R.id.btn_update);
            btnDelete = (Button) view.findViewById(R.id.btn_del);
        }

    }
}
