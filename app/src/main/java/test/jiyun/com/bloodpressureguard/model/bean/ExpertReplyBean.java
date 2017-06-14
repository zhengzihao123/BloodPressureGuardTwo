package test.jiyun.com.bloodpressureguard.model.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/6/13.
 */

public class ExpertReplyBean {

    /**
     * data : [{"document_id":"119999","title":"低压高2年","club_id":"12344205","reply":"郝俊生先生，您好！    首先，如果您体重超重，建议您通过锻炼减轻体重。再有，健康的生活方式也是必要的，如果经常处于精神紧张、压力过大、等失眠状态，血压也会持续增高，药物治疗效果也会不明显。建议您口服替米沙坦80mg/日治疗。如有可能，可以看我门诊。"},{"document_id":"121148","title":"男27岁血压高半年多医生开的螺内脂","club_id":"12382322","reply":"您的血压增高为继发性高血压,您可能患有醛固酮增多症,目前这种疾病控制血压的有效药物为醛固酮拮抗剂-螺内脂,建议您看内分泌科,进一步明确病因,指导治疗."},{"document_id":"132052","title":"我有高血压史，最近眼圈、嘴唇发黑需做哪方面的检查","club_id":"12766095","reply":"您好.首先我认为您目前服用的降压药不能达到预防心血管并发症的目的，而且长期服用对胃有刺激。建议您改用其他降压药。您目前情况可看消化内科。"},{"document_id":"132223","title":"是否可以服用波立维","club_id":"12773817","reply":"您好。不知您心肌缺血到什么程度？如果您有冠心病、心绞痛或者做过冠状动脉支架，可以服用波立维。建议您最好到医院就诊，医生会根据您具体情况指导治疗。"},{"document_id":"132783","title":"我用我父亲的电话预约挂号成功但无法领取加号凭证怎么办","club_id":"12791152","reply":"请您直接到诊室找我说明情况.这周四您过来,我给您开了加号条不知您为什么走了.如果需要节后周四您在过来找我.我在1177房间."},{"document_id":"210944","title":"卵圆孔未关闭","club_id":"15114004","reply":"您好。卵圆孔未闭合只有通过B超检查,所用的镇静剂应该是根据孩子的体重计算出来的，不要担心。"},{"document_id":"224786","title":"心血管疾病","club_id":"15416779","reply":"您好。不知您现在服什么药物没有？心动过缓是否与药物有关需要排除。如果与药物无关，出心动过缓外，是否有乏力、黑蒙、头晕等不适。如果没有上述不适，可观察。"},{"document_id":"225602","title":"心脏有时会刺痛 胸闷 上不来气","club_id":"13496060","reply":"您好。如果是全天胸闷症状不缓解，应该与心脏关系不大。您是否工作压力较大、劳累或休息欠佳？"},{"document_id":"235086","title":"感觉前胸和后背疼","club_id":"15654002","reply":"您好。前胸后背如果是持续疼，不一定是心肌缺血，建议您到当地医院心内科作进一步检查。"},{"document_id":"242733","title":"突发性心肌梗塞，血脂血压一直正常","club_id":"15825702","reply":"建议您行造影检查，必要时行支架，是冠状动脉再通。"}]
     * code : 10000
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
         * document_id : 119999
         * title : 低压高2年
         * club_id : 12344205
         * reply : 郝俊生先生，您好！    首先，如果您体重超重，建议您通过锻炼减轻体重。再有，健康的生活方式也是必要的，如果经常处于精神紧张、压力过大、等失眠状态，血压也会持续增高，药物治疗效果也会不明显。建议您口服替米沙坦80mg/日治疗。如有可能，可以看我门诊。
         */

        private String document_id;
        private String title;
        private String club_id;
        private String reply;

        public String getDocument_id() {
            return document_id;
        }

        public void setDocument_id(String document_id) {
            this.document_id = document_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getClub_id() {
            return club_id;
        }

        public void setClub_id(String club_id) {
            this.club_id = club_id;
        }

        public String getReply() {
            return reply;
        }

        public void setReply(String reply) {
            this.reply = reply;
        }
    }
}
