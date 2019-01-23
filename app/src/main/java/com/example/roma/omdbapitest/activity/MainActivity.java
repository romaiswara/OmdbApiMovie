package com.example.roma.omdbapitest.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.roma.omdbapitest.R;
import com.example.roma.omdbapitest.fragment.FavoriteFragment;
import com.example.roma.omdbapitest.fragment.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.frameContain)
    FrameLayout frameLayout;
    @BindView(R.id.bottomNavigation)
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        addFragment(new SearchFragment());

        navigationView.setOnNavigationItemSelectedListener(this);


    }

    private void addFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                .replace(R.id.frameContain, fragment, fragment.getTag())
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menuSearch:
                addFragment(new SearchFragment());
                return true;
            case R.id.menuFavorite:
                addFragment(new FavoriteFragment());
                return true;
        }
        return false;
    }
}
