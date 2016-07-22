package com.tonikamitv.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonikamitv.loginregister.R;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ejassar on 7/21/16.
 */
public class newadaptor extends ArrayAdapter<Contact> {
    List<Contact> shoplist;
    LayoutInflater vi;
    int Resource;
    ViewHolder holders;
    public newadaptor(Context context, int resource, List<Contact> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        shoplist = objects;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holders = new ViewHolder();
            v = vi.inflate(Resource, null);
            holders.image = (ImageView) v.findViewById(R.id.productimage);
            holders.name = (TextView) v.findViewById(R.id.product);
            holders.price = (TextView) v.findViewById(R.id.value);
            holders.number = (TextView) v.findViewById(R.id.number);
            v.setTag(holders);
        } else {
            holders = (ViewHolder) v.getTag();
        }
        holders.image.setImageResource(R.drawable.ic_launcher);
        new DownloadImageTask(holders.image).execute(shoplist.get(position).getimage());
        holders.name.setText(shoplist.get(position).getname());
        holders.price.setText(shoplist.get(position).getprice());
        holders.number.setText(shoplist.get(position).getphone());

        return v;

    }
    static class ViewHolder {
        public ImageView image;
        public TextView name;
        public TextView price;
        public TextView number;

    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
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
            result=Bitmap.createScaledBitmap(result, 100,100, true);
            bmImage.setImageBitmap(result);

        }

    }

}
