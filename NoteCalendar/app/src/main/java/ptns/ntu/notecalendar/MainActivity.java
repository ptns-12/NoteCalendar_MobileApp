package ptns.ntu.notecalendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Hiển thị CalendarFragment làm màn hình mặc định khi khởi chạy
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new CalendarFragment())
                    .commit();
            //Đặt trạng thái "Selected" cho Calendar
            bottomNavigationView.setSelectedItemId(R.id.nav_calendar);
        }

        // Lắng nghe sự kiện khi người dùng chọn item
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                // Xử lý sự kiện chọn tab
                if (item.getItemId() == R.id.nav_calendar) {
                    selectedFragment = new CalendarFragment();
                } else if (item.getItemId() == R.id.nav_notes) {
                    selectedFragment = new NotesFragment();
                } else if (item.getItemId() == R.id.nav_done) {
                    selectedFragment = new DoneFragment();
                }

                // Thay thế fragment trong FrameLayout
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, selectedFragment)
                            .commit();
                }
                return true;
            }
        });

    }
}