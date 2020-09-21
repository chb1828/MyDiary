package mys.chb.scheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import mys.chb.scheduler.ui.IndexFragment;
import mys.chb.scheduler.ui.record.RecordFragment;
import mys.chb.scheduler.ui.setting.SettingFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private IndexFragment fragmentIndex = new IndexFragment();
    private SettingFragment fragmentSetting = new SettingFragment();
    private RecordFragment fragmentRecord = new RecordFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout,fragmentIndex).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());


    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.indexItem:
                    transaction.replace(R.id.frameLayout, fragmentIndex).commitAllowingStateLoss();
                    break;
                case R.id.recordItem:
                    transaction.replace(R.id.frameLayout, fragmentRecord).commitAllowingStateLoss();
                    break;
                case R.id.settingItem:
                    transaction.replace(R.id.frameLayout, fragmentSetting).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}