package com.example.bluetoothclass2;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

/**
 *  蓝牙适配器
 */
public class BlueToothController {

    private BluetoothAdapter mAdapter;
    public BlueToothController() {
        mAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    /**
     * 是否支持蓝牙
     * @return true 支持， false 不支持
     */
    public boolean isSupportBlueTooth() {
        if(mAdapter != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取蓝牙状态
     * @return
     */
    public boolean getBlueToothStatus() {
        assert (mAdapter != null);
        return mAdapter.isEnabled();
    }

    /**
     * 打开蓝牙
     */
    public void turnOnBlueTooth(Activity activity, int requestCode) {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(intent, requestCode);
        //mAdapter.enable();
    }

    /**
     * 关闭蓝牙
     */
    public void turnOffBlueTooth() {
        mAdapter.disable();
    }


}
