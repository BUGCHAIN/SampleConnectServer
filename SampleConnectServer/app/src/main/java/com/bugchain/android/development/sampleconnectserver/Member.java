package com.bugchain.android.development.sampleconnectserver;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;


public class Member extends ActionBarActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recyclerview);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(Member.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        new LoadMember().execute();
    }

    private class LoadMember extends AsyncTask<Void,Void,Void>{

        private ProgressDialog pg;

        private List<MemberObject> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pg = new ProgressDialog(Member.this);
            pg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pg.setMessage("Loading...");
            pg.setCancelable(false);
            pg.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            list = new MemberObject().getMemberObject(new ConnectServer().getMember());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    pg.dismiss();
                    if(list.size()>0){
                        recyclerView.setAdapter(new ItemViewAdapter(list));
                    }
                }
            },3000);

        }
    }


}
