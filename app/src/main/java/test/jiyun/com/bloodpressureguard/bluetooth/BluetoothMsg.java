package test.jiyun.com.bloodpressureguard.bluetooth;

/*
 * 
 * @author   liaohongfei
 * 
 * @Date  2015 2015-1-27  上午9:53:52
 * 
 */
public class BluetoothMsg {

    /**
     * 蓝牙连接类型
     *
     * @author liao
     */
    public enum ServerOrCilent {
        NONE,
    }

    //蓝牙连接方式
    public static ServerOrCilent serviceOrCilent = ServerOrCilent.NONE;
    //连接蓝牙地址  
//    public static String BlueToothAddress = null;
    //通信线程是否开启  
//    public static boolean isOpen = false;

    public static byte[] bytes = {(byte) 0xEB, 0x20, 0x00, (byte) 0xf5, (byte) 0xEB};
    //获取数据
    public static byte[] bytes1 = {(byte) 0xEB, 0x21, (byte) 0xf4, (byte) 0xEB};
}
