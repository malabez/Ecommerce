package com.tonikamitv.page;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tonikamitv.loginregister.R;

import java.io.InputStream;

public class Main2Activity extends ActionBarActivity {
    ImageButton ib;
    int index=0;
    ImageView iv;
    TextView nametv,descriptiontv,heighttv;
    RatingBar ratingBar;
    String name,description,dob,country,height,spouse,children,image;
    SharedPreferences cart;
    float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if(savedInstanceState==null){
            index=0;
        }else{
            index=savedInstanceState.getInt("name");
        }

        ib= (ImageButton) findViewById(R.id.ib);
        iv= (ImageView) findViewById(R.id.iv);
        nametv= (TextView) findViewById(R.id.name);
        descriptiontv= (TextView) findViewById(R.id.des);
        heighttv= (TextView) findViewById(R.id.height);
        ratingBar= (RatingBar) findViewById(R.id.rat);


        Bundle b2=getIntent().getExtras();
        if (b2!=null){
            name=b2.getString("name");
            description= b2.getString("description");
            dob= b2.getString("dob");
            country= b2.getString("country");
            height= b2.getString("height");
            spouse= b2.getString("spouse");
            children= b2.getString("children");
            image= b2.getString("image");
        }
        rating=Float.parseFloat(children);
        ratingBar.setRating(rating);
        new DownloadImage(iv).execute(image);

        nametv.setText(name);
        descriptiontv.setText(description);
        heighttv.setText(height);

        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), String.valueOf(ratingBar.getRating()), Toast.LENGTH_LONG).show();
            }
        });


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                Toast.makeText(getApplicationContext(), String.valueOf(index), Toast.LENGTH_LONG).show();
            }
        });
Log.d("help", "save me");
        Toast.makeText(getApplicationContext(),"hsahsa",Toast.LENGTH_LONG).show();


        if(index>0){
        Contact contact1= new Contact(name,Integer.toString(index),image,height);
        contact1.save();
        Toast.makeText(getApplicationContext(),"this happened",Toast.LENGTH_SHORT).show();}
        /*cart = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor ca = cart.edit();
        ca.putInt(name, index);
        ca.putString("price",height);
        ca.putString("image",image);
        ca.commit();*/



    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainmain2, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ca:
                startActivity(new Intent(getApplicationContext(),com.tonikamitv.page.cart_page.class));
                break;
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("name",index);
    }


    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }

}
