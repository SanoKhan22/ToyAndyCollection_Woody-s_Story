package com.sano.toystorycollection;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentsAdapter extends FragmentPagerAdapter {

    public FragmentsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return new Numbers();
                case 1:
                    return new Colors();
                case 2:
                    return new Relatives();
                case 3:
                    return new Phrases();
                default:
                    return  null;
            }


    }

    @Override
    public int getCount() {
        return 4;
    }

}
