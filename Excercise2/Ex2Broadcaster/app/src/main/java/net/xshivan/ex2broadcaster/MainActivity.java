package net.xshivan.ex2broadcaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public void handleBtnSendClick(View view) {
        String message = ((EditText)findViewById(R.id.txtBoxMessage)).getText().toString();
        Intent intent = new Intent();
        intent.putExtra("message", message);
        intent.setAction("net.xshivan.permissions.NOTIFY_BROADCAST_SENT");
        sendBroadcast(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
