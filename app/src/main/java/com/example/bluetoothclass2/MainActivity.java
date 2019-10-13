package com.example.bluetoothclass2;

import android.app.Activity;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String TAG = "BlueTooth_MainActivity";
    private BlueToothController mController = new BlueToothController();
    private Toast mToast;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
            Log.d(TAG, "onReceive: " + state);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    showToast("STATE_OFF");
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    showToast("STATE_TURNING_ON");
                    break;
                case BluetoothAdapter.STATE_ON:
                    showToast("STATE_ON");
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    showToast("STATE_TURNING_OFF");
                    break;
                default:
                    showToast("unknown state");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(receiver, filter);
    }

    public void isSupportBlueTooth(View view) {
        boolean ret = mController.isSupportBlueTooth();
        showToast("support bluetooth ? " + ret);
    }

    public void isBlueToothEnable(View view) {
        boolean ret = mController.getBlueToothStatus();
        showToast("bluetooth enable ?" + ret);
    }

    public void turnOnBluetooth(View view) {
        mController.turnOnBlueTooth(this, 0);
    }

    public void turnOffBlueTooth(View view) {
        mController.turnOffBlueTooth();
    }

    public void showToast(String text) {
        if(mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            showToast("打开成功");
        } else {
            showToast("打开失败");
        }
    }
}
