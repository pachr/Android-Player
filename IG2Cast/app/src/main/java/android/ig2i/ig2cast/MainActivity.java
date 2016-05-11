package android.ig2i.ig2cast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView coucou = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coucou = new TextView(this);
        coucou.setText("Coucou je m'appelle Paco");
        setContentView(coucou);

    }
}
