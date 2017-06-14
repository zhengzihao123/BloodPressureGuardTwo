package test.jiyun.com.bloodpressureguard.model.biz;

import java.util.HashMap;
import java.util.Map;

import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;
import test.jiyun.com.bloodpressureguard.model.http.RetrofitUtil;

/**
 * Created by ASUS on 2017/6/12.
 */

public class DoctorOnlineImpl implements DoctorOnline {

    private static DoctorOnlineImpl doctorOnline;

    private DoctorOnlineImpl() {
    }

    public static DoctorOnlineImpl getInstance() {
        if (doctorOnline == null) {
            synchronized (DoctorOnlineImpl.class) {
                doctorOnline = new DoctorOnlineImpl();
                return doctorOnline;
            }
        }
        return doctorOnline;
    }

    @Override
    public void hotDoctor(int PageNum, ResaultCallBack callBack, Class bean) {
        Map<String, String> params = new HashMap<>();
        params.put("tag", "BloodAndroid");
        params.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        params.put("act", "zhuanjia");
        params.put("fun", "HotDoctor");
        params.put("pageNum", String.valueOf(PageNum));
        params.put("pageCount", "4");

        RetrofitUtil.getInstance().getRetrofit("index.php", params, callBack, bean);
    }

    @Override
    public void queryExpert(int PageNum, String province, String title, String keyword,
                            int IsPlus, ResaultCallBack callBack, Class bean) {
        Map<String, String> params = new HashMap<>();
        params.put("tag", "BloodAndroid");
        params.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        params.put("act", "zhuanjia");
        params.put("fun", "SearchDoctor");
        params.put("pageNum", String.valueOf(PageNum));
        params.put("pageCount", "10");
        params.put("province", province);
        params.put("title", title);
        params.put("keyword", keyword);
        params.put("illid", "%E9%AB%98%E8%A1%80%E5%8E%8B");
        params.put("IsPlus", String.valueOf(IsPlus));
        params.put("level", "");
        RetrofitUtil.getInstance().getRetrofit("index.php", params, callBack, bean);
    }

    @Override
    public void freePlusDetail(String expertId, String id, ResaultCallBack callBack, Class bean) {
        Map<String, String> params = new HashMap<>();
        params.put("tag", "BloodAndroid");
        params.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        params.put("act", "zhuanjia");
        params.put("fun", "DoctorDetail");
        params.put("expertid", expertId);
        params.put("id", id);
        RetrofitUtil.getInstance().getRetrofit("index.php", params, callBack, bean);
    }

    @Override
    public void expertReply(int PageNum, String expertId, ResaultCallBack callBack, Class bean) {
        Map<String, String> params = new HashMap<>();
        params.put("tag", "BloodAndroid");
        params.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        params.put("act", "zhuanjia");
        params.put("fun", "DoctorRely");
        params.put("expertid", expertId);
        params.put("pageNum", String.valueOf(PageNum));
        RetrofitUtil.getInstance().getRetrofit("index.php", params, callBack, bean);
    }

    @Override
    public void visitingSchedule(String id, ResaultCallBack callBack, Class bean) {
        Map<String, String> params = new HashMap<>();
        params.put("tag", "BloodAndroid");
        params.put("sign", "2c19b2821ebc5306c3ac37bac5b4288b");
        params.put("act", "app");
        params.put("fun", "doctor");
        params.put("source", "xywy_app");
        params.put("id", id);
        RetrofitUtil.getInstance().getRetrofit("index.php", params, callBack, bean);
    }
}
