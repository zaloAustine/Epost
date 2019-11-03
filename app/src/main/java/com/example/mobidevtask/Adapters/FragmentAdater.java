package com.example.mobidevtask.Adapters;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdater  extends FragmentPagerAdapter {

    public FragmentAdater(@NonNull FragmentManager fm) {
        super(fm);
    }

    private final List<Fragment> myFR = new ArrayList<>();

    public FragmentAdater(@NonNull FragmentManager fm, int behavior) {

        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return myFR.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
    public void AddFragment(Fragment fragment){
        myFR.add(fragment);

    }
}
