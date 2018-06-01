package com.jdan.newsonline.util;

import android.content.Context;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * ToastUtils
 *
 */
public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }
    private static Toast toast;


    public static void show(Context context, int resId) {
        show(context, context.getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration) {
        show(context, context.getResources().getText(resId), duration);
    }

    public static void show(Context context, CharSequence text) {

        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(final Context context, CharSequence text, int duration) {
        synchronized (context) {
            if (toast != null && toast.getDuration() == duration) {
                toast.setText(text);
            } else {
                toast = Toast.makeText(context.getApplicationContext(), text, duration);
            }
            toast.show();
        }
        ThreadManager.INSTANCE.getSinglePool().execute(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                synchronized (context) {
                    toast = null;
                }
            }
        });
    }

    public static void show(Context context, int resId, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, String format, Object... args) {
        show(context, String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(Context context, int resId, int duration, Object... args) {
        show(context, String.format(context.getResources().getString(resId), args), duration);
    }

    public static void show(Context context, String format, int duration, Object... args) {
        show(context, String.format(format, args), duration);
    }
}
