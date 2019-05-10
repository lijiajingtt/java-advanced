package week1;
import java.io.Serializable;

public class student implements Serializable{//贴标签，判断是Serializable否是类型，可以序列化。可以被写入读出
    private String name,sex,province,cities;
    private int number;
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public void setCity(String city) {
        this.cities = city;
    }
    public String toString() {
        return   "name:"+name+ ",sex:" + sex + ",number:" + number
                + ",province:" + province + ",cities:" + cities ;
    }
}
