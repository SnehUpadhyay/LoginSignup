package com.example.vohra.loginsignup;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class NavActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
Session session;
    Fragment mContent;
   final static String Fragment_Tag="MyFragment";
    //private TextView txtName;
    //private TextView txtEmail;
    //private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        session=new Session(this);
        if(!session.loggedin())
        {
            logout();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState==null)
        {
            mContent=fg1.newInstance();
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            ft.add(R.id.container_body,mContent,Fragment_Tag);
            ft.commit();
        }
        else{
            mContent=getSupportFragmentManager().getFragment(savedInstanceState,Fragment_Tag);
        }



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//        txtEmail=(TextView) findViewById(R.id.EmailView);
//        txtName=(TextView) findViewById(R.id.userView);
//        db=new DatabaseHelper(getApplicationContext());
//        String str="";
//        File file=getFilesDir();
//        try{
//            FileInputStream fis=openFileInput("pvg.txt");
//            BufferedReader bfr=new BufferedReader(new InputStreamReader(fis));
//            do{
//                String t=bfr.readLine();
//                if(t==null)
//                    break;
//                str+=t;
//            }while(true);
//            String name=db.getall(str);
//          txtName.setText("" + name);



            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
//    } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }}


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    //     Handle action bar item clicks here. The action bar will
//         automatically handle clicks on the Home/Up button, so long
  //      as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            frag1 frg=new frag1();
            FragmentManager fm=getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.container_body,frg,frg.getTag()).commit();

        }
        else if (id == R.id.nav_slideshow) {

        }
        else if (id == R.id.nav_manage)
        {
            Intent i=new Intent(NavActivity.this,Main3Activity.class);
            startActivity(i);

        }
        else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    protected  void  onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        mContent=getSupportFragmentManager().findFragmentByTag(Fragment_Tag);
        if(mContent!=null)
        {
            getSupportFragmentManager().putFragment(outState,Fragment_Tag,mContent);
        }
    }
    private void logout()
    {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(NavActivity.this,MainActivity.class));
    }
}
