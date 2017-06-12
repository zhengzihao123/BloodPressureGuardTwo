package test.jiyun.com.bloodpressureguard.bean;

import java.util.List;

/**
 * Created by 韩志军 on 2017/6/12.
 */

public class ChangShiBean {

    /**
     * code : 10000
     * data : [{"id":"1494642","title":"高血压冠心病如何保健有效","description":"高血压冠心病如何保健?高血压冠心病会严重折磨大家的精神，给患者带来不小的负担，想要规避疾病危害，大家一定要正确的了解疾病，尤其要知道高血压冠心病如何保健，这样才能够帮助大家尽早恢复身体健康，保证大家的安全。高血压冠心病如何保健：1.合理调整饮食","pubdate":"1490954642"},{"id":"1495153","title":"高血压在医院怎样进行治疗","description":"高血压这种疾病在我国的发病范围是特别广的，这种疾病大多都发生在中老年朋友身上，不过随着人们物质生活水平的不断提高，很多青年朋友患上了高血压这种疾病，这种疾病给人们的身体健康，带来了极其严重的危害，所以患者朋友应该及时进行治疗，而对于高血压如何进行治疗","pubdate":"1490954547"},{"id":"1155023","title":"高血压宝妈要吃一些什么好呢","description":"人们也要知道在妊娠期的时候患上高血压是比较严重的，它的出现会影响到胎儿的健康，因此宝妈们在面对这种病的时候更是要引起注意，而知道这些后更要注意饮食上面的问题，那么，高血压宝妈吃什么好呢?","pubdate":"1481186236"},{"id":"1155026","title":"让你患上高血压的四大因素","description":"现在患上高血压的人也越来越多了，而这些人们更是要去做好一定的了解，尤其是高血压的出现会导致人们的血压升高，因此人们在发现后更是要了解导致疾病出现的一些因素，那么，让你患上高血压的四大因素?","pubdate":"1481186236"},{"id":"1155021","title":"高血压宝妈要吃什么好呢","description":"人们也要知道在妊娠期的时候患上高血压是比较严重的，它的出现会影响到胎儿的健康，因此宝妈们在面对这种病的时候更是要引起注意，而知道这些后更要注意饮食上面的问题，那么，高血压宝妈吃什么好呢?","pubdate":"1481186231"},{"id":"1155019","title":"高血压宝妈吃什么好呢","description":"人们也要知道在妊娠期的时候患上高血压是比较严重的，它的出现会影响到胎儿的健康，因此宝妈们在面对这种病的时候更是要引起注意，而知道这些后更要注意饮食上面的问题，那么，高血压宝妈吃什么好呢?","pubdate":"1481186230"}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1494642
         * title : 高血压冠心病如何保健有效
         * description : 高血压冠心病如何保健?高血压冠心病会严重折磨大家的精神，给患者带来不小的负担，想要规避疾病危害，大家一定要正确的了解疾病，尤其要知道高血压冠心病如何保健，这样才能够帮助大家尽早恢复身体健康，保证大家的安全。高血压冠心病如何保健：1.合理调整饮食
         * pubdate : 1490954642
         */

        private String id;
        private String title;
        private String description;
        private String pubdate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPubdate() {
            return pubdate;
        }

        public void setPubdate(String pubdate) {
            this.pubdate = pubdate;
        }
    }
}
