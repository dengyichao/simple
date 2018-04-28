package dyc.mybatis.simple.model;

import java.util.Arrays;

public class SysUserWithBLOBs extends SysUser {
    private String userInfo;

    private byte[] headImg;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public byte[] getHeadImg() {
        return headImg;
    }

    public void setHeadImg(byte[] headImg) {
        this.headImg = headImg;
    }

    @Override
    public String toString() {
        return "SysUserWithBLOBs{" +
                "userInfo='" + userInfo + '\'' +
                ", headImg=" + Arrays.toString(headImg) +
                '}';
    }
}