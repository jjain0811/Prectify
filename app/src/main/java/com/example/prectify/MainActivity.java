package com.example.prectify;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences sp;
    SharedPreferences st;
    SharedPreferences sr;
    SharedPreferences spr;

    RecyclerView mRecyclerView;
    List<UserData> myUserList;
    UserData mUserData;



    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        final TextView tve=(TextView) findViewById( R.id.TextView8 );
        mRecyclerView=findViewById( R.id.recyclerview );
        Intent c =getIntent();
        String userName = c.getStringExtra("user_name");


        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager( gridLayoutManager );

        myUserList = new ArrayList<>(  );

        mUserData = new UserData( "aaaaaa","aaaaaa",R.drawable.kabir1 );
        myUserList.add( mUserData );
        mUserData = new UserData( "bbbbb","bbbbbb",R.drawable.second );
        myUserList.add( mUserData );
        mUserData = new UserData( "ccccccccccc","cccccccc",R.drawable.mainlogo );
        myUserList.add( mUserData );
        mUserData = new UserData( "ddddddddd","dddddddddd",R.drawable.bhau );
        myUserList.add( mUserData );

        MyAdapter myAdapter= new MyAdapter( MainActivity.this,myUserList );
        mRecyclerView.setAdapter(myAdapter  );


        sp=getSharedPreferences("login",MODE_PRIVATE);
        st=getSharedPreferences("stlogin",MODE_PRIVATE);
        sr=getSharedPreferences("srlogin",MODE_PRIVATE);
        spr=getSharedPreferences("register",MODE_PRIVATE);

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        NavigationView navigationView = findViewById( R.id.nav_view );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(

                this , drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );
    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        if (drawer.isDrawerOpen( GravityCompat.START )) {
            drawer.closeDrawer( GravityCompat.START );
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Do you want to Exit ?");
            builder.setMessage("-------------------------------------------------------------------");
            builder.setCancelable( false );
            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finishAndRemoveTask();
                }
            });
            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            AlertDialog a=builder.create();
            a.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu ( Menu menu ) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main , menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected ( MenuItem item ) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent;
            intent = new Intent( MainActivity.this , QueryType.class );
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.action_settings) {
            Toast.makeText( MainActivity.this , "setting hi toh nahi ho rahi" , Toast.LENGTH_LONG ).show();
            return true;
        }
        else if (id == R.id.action_logout){
            sp.edit().putBoolean("logged",false).apply();
            st.edit().putBoolean("stlogged",false).apply();
            sr.edit().putBoolean("srlogged",false).apply();
            spr.edit().putBoolean("registered",false).apply();




            Toast.makeText( MainActivity.this , "Logged out successfully" , Toast.LENGTH_SHORT ).show();
            Intent intent;
            intent = new Intent( MainActivity.this , SelectType.class );
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected ( MenuItem item ) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {
            Toast.makeText( MainActivity.this , "BIJLI KA BILL TERA BAAP BHAREGA ??" , Toast.LENGTH_LONG ).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText( MainActivity.this , "BATA BIJLI KA BILL TERA BAAP BHAREGA KYA ??" , Toast.LENGTH_LONG ).show();
        }

        DrawerLayout drawer = findViewById( R.id.drawer_layout );
        drawer.closeDrawer( GravityCompat.START );
        return true;
    }
}