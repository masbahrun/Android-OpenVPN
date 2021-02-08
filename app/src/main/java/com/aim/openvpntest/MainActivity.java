package com.aim.openvpntest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aim.openvpn.CustomVPN;
import com.aim.openvpn.OnVPNStatusChangeListener;

public class MainActivity extends AppCompatActivity {

    private Button btnConnect;
    private CustomVPN customVPN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConnect = findViewById(R.id.start);
        customVPN = new CustomVPN(MainActivity.this, getApplicationContext());
        customVPN.launchVPN("https://cdn.discordapp.com/attachments/806482940297084948/806770668598984744/21f4fc869df57a59.ovpn");


        customVPN.setOnVPNStatusChangeListener(new OnVPNStatusChangeListener() {

            @Override
            public void onProfileLoaded(boolean profileLoaded) {
                if(profileLoaded){
                    btnConnect.setEnabled(true);
                }else {
                    btnConnect.setEnabled(false);
                }

            }
            @Override
            public void onVPNStatusChanged(boolean vpnActivated) {
                if(vpnActivated) {
                    btnConnect.setText("disconnect");
                } else {
                    btnConnect.setText("connect");
                }
            }


        });


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customVPN.init();

            }
        });

    }

//    @Override
//    protected void onStop() {
//        customVPN.onStop();
//        super.onStop();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        customVPN.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        customVPN.onPause();
    }


    @Override
    public void finish() {
        super.finish();
        customVPN.cleanup();
    }


}
