package net.xshivan.ex2receiver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent serviceIntent = new Intent(this, ReceiverService.class);
        startService(serviceIntent);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
