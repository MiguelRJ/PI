package com.mrj.morejmoney.ui.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by
 * @author Miguel Rodriguez Jimenez
 * @date 04/01/18
 *
 */

public class AppConstants {

    public static final long NULL_INDEX = -1;
    public static final String PREF_NAME = "PI_pref";
    public static final DecimalFormat decimalformat = new DecimalFormat("0.00");
    public static final DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); // dateformat
    public static final DateFormat tf = new SimpleDateFormat("HH:mm"); // timeformat
    public static final DateFormat cf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); // completeformat

}
