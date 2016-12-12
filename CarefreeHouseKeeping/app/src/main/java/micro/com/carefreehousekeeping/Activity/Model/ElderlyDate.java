package micro.com.carefreehousekeeping.Activity.Model;

/**
 * Created by Administrator on 2016/12/10.
 */

public class ElderlyDate {

    private String headURL;
    private String evaluateURL;
    private String name;
    private int age;
    private String experience;
    private String workSpace;
    private String briefIntroduction;
    private int guanZhu_count;
    private int pingLun_count;
    private int zhan_count;

    public ElderlyDate(String headURL, String evaluateURL, String name, int age, String experience, String workSpace, String briefIntroduction, int guanZhu_count, int pingLun_count, int zhan_count) {
        this.headURL = headURL;
        this.evaluateURL = evaluateURL;
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.workSpace = workSpace;
        this.briefIntroduction = briefIntroduction;
        this.guanZhu_count = guanZhu_count;
        this.pingLun_count = pingLun_count;
        this.zhan_count = zhan_count;
    }


    public ElderlyDate(){}

    public String getHeadURL() {
        return headURL;
    }

    public void setHeadURL(String headURL) {
        this.headURL = headURL;
    }

    public String getEvaluateURL() {
        return evaluateURL;
    }

    public void setEvaluateURL(String evaluateURL) {
        this.evaluateURL = evaluateURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getWorkSpace() {
        return workSpace;
    }

    public void setWorkSpace(String workSpace) {
        this.workSpace = workSpace;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public int getGuanZhu_count() {
        return guanZhu_count;
    }

    public void setGuanZhu_count(int guanZhu_count) {
        this.guanZhu_count = guanZhu_count;
    }

    public int getPingLun_count() {
        return pingLun_count;
    }

    public void setPingLun_count(int pingLun_count) {
        this.pingLun_count = pingLun_count;
    }

    public int getZhan_count() {
        return zhan_count;
    }

    public void setZhan_count(int zhan_count) {
        this.zhan_count = zhan_count;
    }
}
