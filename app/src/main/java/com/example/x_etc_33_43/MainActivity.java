package com.example.x_etc_33_43;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.x_etc_33_43.fragment.Fragment_ICcz;
import com.example.x_etc_33_43.fragment.Fragment_clsf;
import com.example.x_etc_33_43.fragment.Fragment_dzbc;
import com.example.x_etc_33_43.fragment.Fragment_ewm;
import com.example.x_etc_33_43.fragment.Fragment_gsETC;
import com.example.x_etc_33_43.fragment.Fragment_gslk;
import com.example.x_etc_33_43.fragment.Fragment_lxxx;
import com.example.x_etc_33_43.fragment.Fragment_tcc;
import com.example.x_etc_33_43.fragment.Fragment_tqxx;
import com.example.x_etc_33_43.fragment.Fragment_xccz;
import com.example.x_etc_33_43.fragment.Fragment_xwmt;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private NavigationView nav;
    private DrawerLayout dra;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.setText("智能交通");
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.gslk:
                                Fragment1(new Fragment_gslk());
                                break;
                            case R.id.gsETC:
                                Fragment1(new Fragment_gsETC());
                                break;
                            case R.id.lxxx:
                                Fragment1(new Fragment_lxxx());
                                break;
                            case R.id.tqxx:
                                Fragment1(new Fragment_tqxx());
                                break;
                            case R.id.ewm:
                                Fragment1(new Fragment_ewm());
                                break;
                            case R.id.dzbc:
                                Fragment1(new Fragment_dzbc());
                                break;
                            case R.id.xwmt:
                                Fragment1(new Fragment_xwmt());
                                break;
                            case R.id.ICcz:
                                Fragment1(new Fragment_ICcz());
                                break;
                            case R.id.clsf:
                                Fragment1(new Fragment_clsf());
                                break;
                            case R.id.tcc:
                                Fragment1(new Fragment_tcc());
                                break;
                            case R.id.xccz:
                                Fragment1(new Fragment_xccz());
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });
            }
        });


    }

    private void Fragment1(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment).commit();
    }

    private void initView() {
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
        nav = findViewById(R.id.nav);
        dra = findViewById(R.id.dra);
    }
}