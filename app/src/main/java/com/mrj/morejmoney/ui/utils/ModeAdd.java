package com.mrj.morejmoney.ui.utils;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *      MODE_ADD = 0
 *      MODE_EDIT = 1
 */

public class ModeAdd {

    public static final int MODE_ADD = 0;
    public static final int MODE_EDIT = 1;
    private int mode;

    public ModeAdd(int mode){
        if (mode < MODE_ADD || mode > MODE_EDIT){
            throw new IllegalArgumentException("Inlavid ModeAdd "+mode);
        }
        this.mode = mode;
    }

    public int getMode(){
        return mode;
    }

    public void setMode(int mode){
        this.mode = mode;
    }
}
