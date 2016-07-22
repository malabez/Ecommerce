package com.tonikamitv.page;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.orm.SugarRecord;
import com.tonikamitv.loginregister.R;

import java.util.ArrayList;
import java.util.List;


public class cart_page extends ActionBarActivity{
    List<Contact> shoplist;
    ArrayAdapter adap;
    TextView tv111;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_page);

        shoplist= SugarRecord.listAll(Contact.class);
        tv111= (TextView) findViewById(R.id.tv111);
        Log.d("thiss",shoplist.toString());
        tv111.setText(shoplist.toString());
        ListView listview1 = (ListView)findViewById(R.id.list1);
        adap = new newadaptor(getApplicationContext(), R.layout.row_cart,shoplist);
        listview1.setAdapter(adap);
    }
}
