package com.bumbumapps.stylishtext.menu;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import com.bumbumapps.stylishtext.R;
import com.bumbumapps.stylishtext.fragments.StylistProcessTextFragment;
import com.bumbumapps.stylishtext.interfaces.OnTextSelectedListener;
import java.util.Objects;

@TargetApi(Build.VERSION_CODES.M)
public class StylishProcessTextActivity extends AppCompatActivity implements OnTextSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CharSequence text = getIntent().getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT);
        if (text != null) {
            setContentView(R.layout.activity_process_text);
            Toolbar toolbar = findViewById(R.id.toolbar);
            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);

            setSupportActionBar(toolbar);
            mTitle.setText(toolbar.getTitle());

            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            String input = text.toString();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, StylistProcessTextFragment.newInstance(input)).commit();
        } else {
            finish();
        }
    }

    @Override
    public void onTextSelected(String text) {
        overridePendingTransition(0, 0);
        finish();
    }


}
