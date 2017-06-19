package test.jiyun.com.bloodpressureguard.bluetooth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;

import static test.jiyun.com.bloodpressureguard.bluetooth.BluetoothMsg.bytes;
import static test.jiyun.com.bloodpressureguard.bluetooth.BluetoothMsg.bytes1;


public class ClientActivity extends Activity {

    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter; // Bluetooth适配器
    private BluetoothDevice device;             // 蓝牙设备
    private Button btnOne, btnTwo;
    private BluetoothSocket socket;     // 客户端socket
    private ClientThread mClientThread; // 客户端运行线程
    private Map<String, String> myInfo;
    private ProgressDialog progressDialog;
    private boolean first = true;
    private int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        ButterKnife.bind(this);

        init();
    }


    // 变量初始化
    private void init() {
        // TODO Auto-generated method stub
        mContext = this;
        show(mContext);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        myInfo = new HashMap<>();

        // 注册receiver监听
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);

        // 获取已经配对过的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            Log.i("进来了", "size是0");
            for (BluetoothDevice device : pairedDevices) {
                String deviceAddress = device.getAddress();
                String address = deviceAddress.substring(deviceAddress.length() - 17);
                myInfo.put(device.getName(), address);
            }
        }

        btnOne = (Button) findViewById(R.id.btn_find);
        btnOne.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (device != null && device.getBondState() == 12)
                    Toast.makeText(mContext, "已经连接成功，可以开始测量", Toast.LENGTH_SHORT).show();
                else
                    connect();
            }
        });

        btnTwo = (Button) findViewById(R.id.btn_start);
        btnTwo.setEnabled(false);
        btnTwo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                sendMessageHandler(bytes);
                LinkDetectedHandler.sendEmptyMessageDelayed(6, 4000);
            }
        });

    }

    private void openBlueTooth() {

        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                // 直接打开蓝牙
                mBluetoothAdapter.enable();
            }
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        openBlueTooth();
        // 扫描
        scanDevice();
    }

    /**
     * 蓝牙设备扫描过程中(mBluetoothAdapter.startDiscovery())会发出的消息
     * ACTION_FOUND 扫描到远程设备
     * ACTION_DISCOVERY_FINISHED 扫描结束
     */
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                // 通过EXTRA_DEVICE附加域来得到一个BluetoothDevice设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                // 如果这个设备是不曾配对过的，添加到list列表
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    String deviceAddress = device.getAddress();
                    String address = deviceAddress.substring(deviceAddress.length() - 17);
                    String name = device.getName();
                    Log.i("name----------", "deviceAddress:" + deviceAddress);

                    if (!TextUtils.isEmpty(name)) {
                        Log.i("name----------", "name:" + name);
                        myInfo.put(name, address);
                        if ("KBB3-1".equals(name)) {
                            LinkDetectedHandler.obtainMessage(5, address).sendToTarget();
                            mBluetoothAdapter.cancelDiscovery();
                        }
                    } else {
                        Log.i("name----------", "有一个空" + address);
                        if ("98:77:70:50:B5:15".equals(address) || "98:77:70:50:B5:13".equals(address)) {
                            myInfo.put("KBB3-1", address);
                            LinkDetectedHandler.obtainMessage(5, address).sendToTarget();
                            mBluetoothAdapter.cancelDiscovery();
                        }
                    }
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                LinkDetectedHandler.sendEmptyMessage(4);
            }
        }
    };

    // Handler更新UI
    private Handler LinkDetectedHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String ss = (String) msg.obj;
                    Log.i("split", ss);
                    String[] split = ss.split(",");
                    Log.i("split", String.valueOf(split.length));
                    break;
                case 2:
                    Toast.makeText(mContext, "连接成功", Toast.LENGTH_SHORT).show();
                    btnTwo.setEnabled(true);
                    progressDialog.dismiss();
                    break;
                case 3:
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "连接失败，请重试", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    progressDialog.dismiss();
                    Toast.makeText(mContext, "没有发现蓝牙设备，请检查是否开启", Toast.LENGTH_SHORT).show();
                    break;
                case 5:
                    device = null;
                    device = mBluetoothAdapter.getRemoteDevice(msg.obj + "");
                    mClientThread = new ClientThread();
                    mClientThread.start();
                    Log.i("进来了", "handler" + msg.obj);
                    break;
                case 6:
                    Log.i("ClientActivity", "handler");
                    sendMessageHandler(bytes1);
                    LinkDetectedHandler.sendEmptyMessageDelayed(6, 250);
                    break;
                case 7:
                    Toast.makeText(mContext, "设配配对成功，可以连接", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    break;
            }
        }

    };

    private int tag = 1;

    // 开启客户端连接服务端
    private class ClientThread extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (device != null) {
                try {
                    socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    // 通过socket连接服务器，这是一个阻塞过程，直到连接建立或者连接失效
                    socket.connect();
                    if (tag == 1)
                        LinkDetectedHandler.sendEmptyMessage(2);
                    else
                        LinkDetectedHandler.sendEmptyMessage(7);
                    // 可以开启读数据线程
                    ReadThread r = new ReadThread();
                    r.start();
                } catch (IOException e) {
                    Log.e("error ", e.getMessage());
                    LinkDetectedHandler.sendEmptyMessage(3);
                }
            }
        }
    }


    private class ReadThread extends Thread {
        // 通过socket获取InputStream流
        @Override
        public void run() {
            byte[] buffer = new byte[1024];
            int bytes;
            InputStream is = null;
            try {
                is = socket.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    StringBuffer stringBuffer = new StringBuffer();
                    if ((bytes = is.read(buffer)) > 0) {
                        byte[] data = new byte[bytes];
                        for (int i = 0; i < data.length; i++) {
                            data[i] = buffer[i];
                            Log.i("-----------====", data[i] + "");
                            stringBuffer.append(buffer[i] + ",");
                        }
                        Log.d("ReadThread", stringBuffer.toString());

                    }
                } catch (IOException e) {
                    try {
                        is.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }
            }
        }
    }


    // 发送数据
    private void sendMessageHandler(byte[] bytes) {
        if (socket == null) {
            Toast.makeText(mContext, "没有可用的连接", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            OutputStream os = socket.getOutputStream();
            os.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 停止服务
    private void closeClient() {
        new Thread() {
            public void run() {
                if (mClientThread != null) {
                    mClientThread.interrupt();
                    mClientThread = null;
                }
                if (LinkDetectedHandler != null) {
                    LinkDetectedHandler.removeCallbacksAndMessages(null);
                }
                try {
                    if (socket != null) {
                        socket.close();
                        socket = null;
                    }
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }.start();
    }

    // 扫描设备
    private void scanDevice() {
        // TODO Auto-generated method stub
        if (mBluetoothAdapter.isDiscovering()) {
            Log.i("进来了", "扫描结束");
            mBluetoothAdapter.cancelDiscovery();
        } else if (first) {
            first = false;
            // 每次扫描前都先判断一下是否存在已经配对过的设备
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    String deviceAddress = device.getAddress();
                    String address = deviceAddress.substring(deviceAddress.length() - 17);
                    Set<String> keySet = myInfo.keySet();
                    if (keySet.size() > 0) {
                        for (String s : keySet) {
                            if (!s.equals(deviceAddress)) {
                                myInfo.put(device.getName(), address);
                            }
                        }
                    } else {
                        myInfo.put(device.getName(), address);
                    }
                }
            }
            mBluetoothAdapter.cancelDiscovery();
        }
    }

    private void show(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("正在连接，请稍等...");
        progressDialog.setCancelable(false);
    }

    private void connect() {
        progressDialog.show();
        if (myInfo.size() > 0) {
            Log.i("进来了", "我是myInfo不等于0");
            Set<String> keySet = myInfo.keySet();
            for (String name : keySet) {
                Log.i("进来了name", name);
                String address = myInfo.get(name);
                Log.i("进来了name", address);
                if ("KBB3-1".equals(name)) {
                    Log.i("进来了", "我是equals");
                    device = mBluetoothAdapter.getRemoteDevice(address);
                    mClientThread = new ClientThread();
                    mClientThread.start();
                    return;
                }
            }
            mBluetoothAdapter.startDiscovery();
            tag = 2;
        } else {
            Log.i("进来了", "else");
            mBluetoothAdapter.startDiscovery();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
            // 关闭蓝牙
            mBluetoothAdapter.disable();
        }
        unregisterReceiver(mReceiver);
        closeClient();
        BluetoothMsg.serviceOrCilent = BluetoothMsg.ServerOrCilent.NONE;
    }
}
