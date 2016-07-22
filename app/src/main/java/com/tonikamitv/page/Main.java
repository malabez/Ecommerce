package com.tonikamitv.page;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tonikamitv.loginregister.R;

public class Main extends ActionBarActivity {

    ArrayList<Actors> actorsList;

    ActorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitypage_main);
        actorsList = new ArrayList<Actors>();
        new JSONAsyncTask().execute("http://fee.site88.net/new.php");

        ListView listview = (ListView)findViewById(R.id.list);
        adapter = new ActorAdapter(getApplicationContext(), R.layout.row, actorsList);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long id) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), actorsList.get(position).getName(), Toast.LENGTH_LONG).show();
                String name=actorsList.get(position).getName();
                String description= actorsList.get(position).getDescription();
                String dob= actorsList.get(position).getDob();
                String country= actorsList.get(position).getCountry();
                String height= actorsList.get(position).getHeight();
                String spouse= actorsList.get(position).getSpouse();
                String children= actorsList.get(position).getChildren();
                String image= actorsList.get(position).getImage();
                Intent i=new Intent(getApplicationContext(),Main2Activity.class);
                Bundle b1=new Bundle();
                b1.putString("name",name);
                b1.putString("description",description);
                b1.putString("dob",dob);
                b1.putString("country",country);
                b1.putString("height",height);
                b1.putString("spouse",spouse);
                b1.putString("children",children);
                b1.putString("image",image);
                i.putExtras(b1);
                startActivity(i);

            }
        });
    }
    @Override
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



    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Main.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONArray jarray = jsono.getJSONArray("actors");

                    for (int i = 0; i < jarray.length(); i++) {
                        JSONObject object = jarray.getJSONObject(i);

                        Actors actor = new Actors();

                        actor.setName(object.getString("name"));
                        actor.setDescription(object.getString("description"));
                        actor.setDob(object.getString("dob"));
                        actor.setCountry(object.getString("country"));
                        actor.setHeight(object.getString("height"));
                        actor.setSpouse(object.getString("spouse"));
                        actor.setChildren(object.getString("children"));
                        actor.setImage(object.getString("image"));

                        actorsList.add(actor);
                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            adapter.notifyDataSetChanged();
            if(result == false)
                Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }






}

