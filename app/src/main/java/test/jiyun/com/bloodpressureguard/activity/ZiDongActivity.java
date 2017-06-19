package test.jiyun.com.bloodpressureguard.activity;

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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import test.jiyun.com.bloodpressureguard.R;
import test.jiyun.com.bloodpressureguard.base.BaseActivity;
import test.jiyun.com.bloodpressureguard.bluetooth.BluetoothMsg;
import test.jiyun.com.bloodpressureguard.sq.MyManager;
import test.jiyun.com.bloodpressureguard.sq.Student;

import static test.jiyun.com.bloodpressureguard.bluetooth.BluetoothMsg.bytes;
import static test.jiyun.com.bloodpressureguard.bluetooth.BluetoothMsg.bytes1;

/**
 * Created by 韩志军 on 2017/6/15.
 */

public class ZiDongActivity extends BaseActivity {

    /*高压和低压高度*/
    @Bind(R.id.mDiYa)
    TextView mDiYa;
    @Bind(R.id.mGaoYa)
    TextView mGaoYa;

    /*高压和低压数据*/
    @Bind(R.id.mDiYaText)
    TextView mDiYaText;
    @Bind(R.id.mGaoYaText)
    TextView mGaoYaText;
    @Bind(R.id.btnOne)
    TextView btnOne;
    @Bind(R.id.btnTwo)
    TextView btnTwo;
    @Bind(R.id.mXL)
    TextView mXL;


    private Context mContext;
    private BluetoothAdapter mBluetoothAdapter; // Bluetooth适配器
    private BluetoothDevice device;             // 蓝牙设备
    private BluetoothSocket socket;     // 客户端socket
    private ClientThread mClientThread; // 客户端运行线程
    private Map<String, String> myInfo;
    private ProgressDialog progressDialog;
    private boolean first = true;
    private boolean[] boo = {false};
    private boolean[] booA = {true};

    private int mA;
    private int mB;
    private int mC;
    private int a;
    private int b;
    private float gg = 140f;
    private float dd = 140f;


    private LinearLayout.LayoutParams mGYLayoutParams;
    private LinearLayout.LayoutParams mDYLayoutParams;
    private LinearLayout.LayoutParams mLayoutParams;

    private int g = 0;
    private int d = 0;

    /*高低压位置*/
    private int heightG = 0;
    private int heightD = 0;
    private int width = 200;

    /*数据库管理*/
    private MyManager myManager;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case 1:
                    g += 2;
                    d += 2;
                    getDongHua(g, d);

                    if (g >= 170)
                        handler.sendEmptyMessageDelayed(4, 3000);
                    else
                        handler.sendEmptyMessageDelayed(1, 140);
                    break;
                case 2:
                    Log.d("ZiDongActivity", "走了");
                    g -= 2;
                    d -= 2;
                    getDongHua(g, d);
                    if (g <= 140) {
                        a = g;
                        b = d;
                    } else
                        handler.sendEmptyMessageDelayed(2, 1100);
                    break;
                case 3:
                    Toast.makeText(ZiDongActivity.this, "测量完成", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    g -= 4;
                    d -= 4;
                    getDongHua(g, d);

                    if (g <= 150)
                        handler.sendEmptyMessageDelayed(2, 3000);
                    else
                        handler.sendEmptyMessageDelayed(4, 1700);
                    break;
                case 7:
                    mXL.setText("心率 /" + " " + mC);
                    Log.d("ZiDongActivity", "的点点滴滴");
                    float i = (float) (a - mA) / 10;
                    float i1 = (float) (b - mB) / 10;
                    Log.d("ZiDongActivity", "mA:" + mA);
                    Log.d("ZiDongActivity", "mB:" + mB);

                    gg = gg - i;
                    dd = dd - i1;
                    getDongHuaA(gg, dd);
                    if ((int) gg >= mA && (int) dd > mB) {
                        Log.d("ZiDongActivity", "的点点滴滴dddddd");
                        handler.sendEmptyMessageDelayed(7, 350);
                    } else {
                        getShuJu(mB, mA);
                        g = 0;
                        d = 0;
                        gg = 0.0f;
                        dd = 0.0f;
                        a = 0;
                        b = 0;
                        mA = 0;
                        mB = 0;
                    }
                    break;
            }
        }
    };

    private void getDongHuaA(float g, float d) {
        heightG = (int) (g * 3);
        heightD = (int) (d * 3);
        mGYLayoutParams = new LinearLayout.LayoutParams(width, heightG);
        mDYLayoutParams = new LinearLayout.LayoutParams(width, heightD);
        mLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*高低压数据*/
        mGaoYaText.setText(((int) g) + "");
        mDiYaText.setText(((int) d) + "");
        mGaoYaText.setLayoutParams(mLayoutParams);
        mDiYaText.setLayoutParams(mLayoutParams);
        /*高低压位置*/
        mGaoYa.setLayoutParams(mGYLayoutParams);
        mDiYa.setLayoutParams(mDYLayoutParams);
    }

    private void getShuJu(int d, int g) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);

        //通过SimpleDateFormat获取24小时制时间
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String s = sdf.format(new Date());

        String[] split = str.split("-");

        String s1 = split[1];
        String s2 = split[2];

        Student student = new Student();
        student.setId(Integer.parseInt(s1));
        student.setDay(Integer.parseInt(s2));
        student.setTime(s);
        student.setDiya(d);
        student.setGaoya(g);
        student.setName("自动测量");
        boolean b = myManager.addStudent(student);
        if (b) {
            Toast.makeText(ZiDongActivity.this, "插入数据成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.zi_dong_activity;
    }

    @Override
    protected void initView() {
        myManager = new MyManager(ZiDongActivity.this);
        getDongHua(g, d);
    }


    private void getBit(byte[] b, String str) {
        /*将byte转换为一个长度为8的byte数组，数组每个值代表bit */
        LinkDetectedHandler.sendEmptyMessageDelayed(6, 250);
        byte by = b[2];
        StringBuffer sb = new StringBuffer();
        sb.append(((by >> 0) & 0x1) + ",")
                .append(((by >> 1) & 0x1) + ",")
                .append(((by >> 2) & 0x1) + ",")
                .append(((by >> 3) & 0x1) + ",")
                .append(((by >> 4) & 0x1) + ",")
                .append(((by >> 5) & 0x1) + ",")
                .append(((by >> 6) & 0x1) + ",")
                .append(((by >> 7) & 0x1) + ",");
        String[] split = sb.toString().split(",");
        /*表示正在测量*/
        if (split[7].equals("1")) {
            Log.d("ZiDongActivity", "走了");
            if (booA[0]) {
                booA[0] = false;
                handler.sendEmptyMessageDelayed(1, 2000);
            }
        } else
            Toast.makeText(this, "测量结束", Toast.LENGTH_SHORT).show();
//        /*测量结束并且数据有效*/ && split[6].equals("1")
        if (split[6].equals("1") && split[7].equals("0")) {
            Log.d("ZiDongActivity", "走了");
            String[] split1 = str.split(",");
            String s = split1[6];
            mA = Integer.parseInt(s);
            mB = Integer.parseInt(split1[8]);
            mC = Integer.parseInt(split1[10]);
            handler.removeCallbacksAndMessages(null);
            Log.d("ZiDongActivity", "移除");
            handler.sendEmptyMessage(7);
            LinkDetectedHandler.removeMessages(6);
        } else
            Toast.makeText(this, "数据无效", Toast.LENGTH_SHORT).show();


        Log.d("ZiDongActivitya", sb.toString());

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(1);
        handler.removeMessages(2);
        handler.removeMessages(3);
        handler.removeMessages(4);
    }

    private void getDongHua(int g, int d) {
        heightG = g * 3;
        heightD = d * 3;
        mGYLayoutParams = new LinearLayout.LayoutParams(width, heightG);
        mDYLayoutParams = new LinearLayout.LayoutParams(width, heightD);
        mLayoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*高低压数据*/
        mGaoYaText.setText(g + "");
        mDiYaText.setText(d + "");
        mGaoYaText.setLayoutParams(mLayoutParams);
        mDiYaText.setLayoutParams(mLayoutParams);
        /*高低压位置*/
        mGaoYa.setLayoutParams(mGYLayoutParams);
        mDiYa.setLayoutParams(mDYLayoutParams);
    }

    @Override
    protected void loadData() {
        init();
    }

    @Override
    protected void listener() {

    }


    @OnClick({R.id.btnOne, R.id.btnTwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnOne:
                if (device != null && device.getBondState() == 12)
                    Toast.makeText(mContext, "已经连接成功，可以开始测量", Toast.LENGTH_SHORT).show();
                else
                    connect();
                break;
            case R.id.btnTwo:

                if (boo[0] == false) {
                    Toast.makeText(ZiDongActivity.this, "请先配对", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMessageHandler(bytes);
                LinkDetectedHandler.sendEmptyMessageDelayed(6, 250);
                break;
        }
    }

    // 变量初始化
    private void init() {

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
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 通过EXTRA_DEVICE附加域来得到一个BluetoothDevice设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
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
                        if ("98:77:70:50:B5:15".equals(address)
                                || "98:77:70:50:B5:13".equals(address)
                                || "98:77:70:50:21:13".equals(address)) {
                            myInfo.put("KBB3-1", address);
                            LinkDetectedHandler.obtainMessage(5, address).sendToTarget();
                            mBluetoothAdapter.cancelDiscovery();
                        }
                    }
                }
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
                    myInfo.clear();
                    boo[0] = true;
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
                    break;
                case 7:
                    Toast.makeText(mContext, "设配配对成功，可以连接", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    break;
            }
        }

    };

    private int tag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    // 开启客户端连接服务端
    private class ClientThread extends Thread {
        @Override
        public void run() {
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
                        getBit(buffer, stringBuffer.toString());
                        String str = stringBuffer.toString();
                        String[] split = str.split(",");
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
                                if (s.equals("KBB3-1") && "98:77:70:50:B5:15".equals(address)
                                        || "98:77:70:50:B5:13".equals(address)
                                        || "98:77:70:50:21:13".equals(address)) {

                                }
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
