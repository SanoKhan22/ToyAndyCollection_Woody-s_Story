package com.sano.toystorycollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.*;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

        ViewPager viewPager;
        TabLayout tabLayout;
    private AdView adView;
     //   Toolbar toolbar;
        FragmentsAdapter fragmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AudienceNetworkAds.initialize(this);

        //fb ads

        viewPager = findViewById(R.id.viewPager);

        tabLayout = findViewById(R.id.tabView);
        adView = new AdView(this, "863029114193636_863029624193585", AdSize.BANNER_HEIGHT_50);

        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();
                      //end fb ads
    ///////////////////////////////////////////ACTION BAR

        // Set the ActionBar background




     //  toolbar = findViewById(R.id.toolbar);

        fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPager.setAdapter(fragmentsAdapter);

       viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

          //   Fragment Change when particlular   TAB  is selected
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager.ad
        //   Tab  changer when Fragment or ViewPager Item is Swiped
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                getSupportActionBar().setTitle(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText());


//
//                ===============   StatusBAR COLOR CHANGE =========================

//                tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(MainActivity.this,
//                        R.color.white));


                switch (position){
                    case 0:
                       // ===============STATUS BAR ======== ACTIONBAR === TABLAYOUT === COLOR =============== //
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this,
                                R.color.rdkin_A)));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                                R.color.rdkin));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.rdkin_S));
                        }
                        // ================================================================================== //
                        break;
                    case 1:
                        // ======================= ACTIONBAR === TABLAYOUT === COLOR =============== //
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this,
                                R.color.grue_A)));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                                R.color.grue));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.grue_S));

                        }
                        break;
                    // ================================================================================== //
                    case 2:
                        // ======================= ACTIONBAR === TABLAYOUT === COLOR =============== //
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this,
                                R.color.reorg_A)));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                                R.color.reorg));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.reorg_S));
                        }
                        break;
                    // ================================================================================== //
                    case 3:
                        // ======================= ACTIONBAR === TABLAYOUT === COLOR =============== //
                        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this,
                                R.color.dkule_A)));
                        tabLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this,
                                R.color.dkule));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,
                                    R.color.dkule_S));
                        }
                        break;
                }
                // ================================================================================== //
            }
            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //getSupportActionBar().setTitle(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getText());
        //    tabLayout.setupWithViewPager(viewPager);

    }

    //fb ads 
    @Override
    protected void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
