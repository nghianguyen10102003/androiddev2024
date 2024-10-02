package vn.edu.usth.usthweather;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        ForecastFragment firstFragment = new ForecastFragment();
//        getSupportFragmentManager().beginTransaction().add(
//                R.id.main, firstFragment).commit();
        PagerAdapter adapter = new HomePagerAdapter(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);

        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager);
        mediaPlayer = MediaPlayer.create(this, R.raw.audio1);
//
//        mediaPlayer.start();
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.refresh) {
//            Toast.makeText(this, "refreshing", Toast.LENGTH_SHORT).show();
            simulate();
            return true;
        } else if (itemId == R.id.setting) {
            Intent intent = new Intent(WeatherActivity.this, prefeActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    private void simulate(){
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
    // This method is executed in main thread
                String content = msg.getData().getString("server_response");

                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
    // this method is run in a worker thread
                try {
    // wait for 5 seconds to simulate a long network access
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
    // Assume that we got our data from server
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "some sample json here");
    // notify main thread
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        });
    t.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("WeatherActivity", "onStart called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("WeatherActivity", "onResume called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("WeatherActivity", "onPause called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("WeatherActivity", "onStop called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("WeatherActivity", "onDestroy called");
    }

    public class HomePagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 3;
        private final String[] titles = new String[]{"Hanoi", "Paris","Tokio"};

        public HomePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @NonNull
        @Override
        public Fragment getItem(int page) {
            switch (page){
                case 0:
                    return new Hanoi_frag();
                case 1:
                    return new paris_frag();
                case 2:
                    return new tokio_frag();

                default:
                    return new WeatherFragment();

            }
            //return new WeatherAndForecastFragment();
        }
        public CharSequence getPageTitle(int page) {
            // returns a tab title corresponding to the specified page
            return titles[page];
        }
    }
}