package test.jiyun.com.bloodpressureguard.model.biz;

import test.jiyun.com.bloodpressureguard.model.callback.ResaultCallBack;

/**
 * Created by ASUS on 2017/6/12.
 */

public interface DoctorOnline {

    void hotDoctor(int PageNum, ResaultCallBack callBack, Class bean);

    void queryExpert(int PageNum, String province, String title, String keyword,
                     int IsPlus, ResaultCallBack callBack, Class bean);

    void freePlusDetail(String expertid, String id, ResaultCallBack callBack, Class bean);

    void expertReply(int PageNum, String expertId, ResaultCallBack callBack, Class bean);

    void visitingSchedule(String id,ResaultCallBack callBack, Class bean);
}
