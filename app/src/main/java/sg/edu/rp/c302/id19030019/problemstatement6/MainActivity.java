package sg.edu.rp.c302.id19030019.problemstatement6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button btnReadLater;
    ArrayList<Fragment> fragments;
    PagerAdapter adapter;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReadLater = findViewById(R.id.btnReadLater);
        viewPager = findViewById(R.id.pager);
        fragments = new ArrayList<>();
        sharedPreferences = getSharedPreferences("L06 PS", Context.MODE_PRIVATE);

        FragmentManager fm = getSupportFragmentManager();
        fragments.add(new Frag1());
        fragments.add(new Frag2());
        fragments.add(new Frag3());

        adapter = new FactsFragmentPagerAdapter(fm, fragments);
        viewPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(v -> {
            onPause();
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("currpage", viewPager.getCurrentItem());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int currPage = sharedPreferences.getInt("currpage", 0);
        viewPager.setCurrentItem(currPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int max = viewPager.getChildCount();
        switch (item.getItemId()) {
            case R.id.itemNext:
                if (viewPager.getCurrentItem() < max - 1) {
                    int nextPage = viewPager.getCurrentItem() + 1;
                    viewPager.setCurrentItem(nextPage, true);
                }
                return true;
            case R.id.itemPrevious:
                if (viewPager.getCurrentItem() > 0) {
                    int previousPage = viewPager.getCurrentItem() - 1;
                    viewPager.setCurrentItem(previousPage, true);
                }
                return true;
            case R.id.itemRandom:
                Random random = new Random();
                viewPager.setCurrentItem(random.nextInt(max), true);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}