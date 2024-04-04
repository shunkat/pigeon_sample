package com.example.batterylevel

import BatteryHostApi
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine

// 生成されたインタフェースを上書きして中身を実装する
private class BatteryLevelApi(private val context: Context) : BatteryHostApi {
    override fun getBatteryLevel(): Long {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            // Int型からLo　ng型への変換
            return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY).toLong()
        } else {
            val intent = context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
            // intentがnullでない場合のみ計算を行う
            intent?.let {
                val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
                val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
                return (level * 100 / scale).toLong()
            }
            // intentがnullの場合、適切なデフォルト値を返す
            return -1
        }
    }
}

class MainActivity: FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)

        val api = BatteryLevelApi(this)
        BatteryHostApi.setUp(flutterEngine.dartExecutor.binaryMessenger, api)
    }

}

