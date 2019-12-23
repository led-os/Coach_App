package com.jsjlzj.wayne.utils;

public class Chang {

    public static class Shan {
        private int cycle = 20;
        private int count;
        private int changCount;
        private boolean lastLight;

        public boolean isShan(boolean isLight) {
            boolean flag;
            if (lastLight == isLight) {
                count++;
                if (count > cycle) {//某颜色持续了 一个周期
                    changCount = 0;
                }
            } else {
                count = 1;
                changCount++;
            }
            lastLight = isLight;
            return changCount >= 2;//siglelight 010 or 101
        }
    }

    Shan shanR = new Shan(), shanY = new Shan(), shanG = new Shan();

    public void p(boolean r, boolean y, boolean g) {
        boolean isShanR = shanR.isShan(r);
        boolean isShanY = shanY.isShan(y);
        boolean isShanG = shanG.isShan(g);
        if (isShanR || isShanY || isShanG) {//有灯在闪
            if (true) {//单闪

            } else {//多闪

            }
        } else if (r || y || g) {//有灯常亮

        } else {//无灯亮

        }
    }
}
