package com.afollestad.materialdialogs.util;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.annotation.ArrayRes;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.Builder;

public class DialogUtils {
    @ColorInt
    public static int getDisabledColor(Context context) {
        return adjustAlpha(isColorDark(resolveColor(context, 16842806)) ? ViewCompat.MEASURED_STATE_MASK : -1, 0.3f);
    }

    @ColorInt
    public static int adjustAlpha(@ColorInt int color, float factor) {
        return Color.argb(Math.round(((float) Color.alpha(color)) * factor), Color.red(color), Color.green(color), Color.blue(color));
    }

    @ColorInt
    public static int resolveColor(Context context, @AttrRes int attr) {
        return resolveColor(context, attr, 0);
    }

    @ColorInt
    public static int resolveColor(Context context, @AttrRes int attr, int fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            int color = a.getColor(0, fallback);
            return color;
        } finally {
            a.recycle();
        }
    }

    public static ColorStateList resolveActionTextColorStateList(Context context, @AttrRes int colorAttr, ColorStateList fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{colorAttr});
        try {
            TypedValue value = a.peekValue(0);
            if (value == null) {
                return fallback;
            }
            if (value.type < 28 || value.type > 31) {
                ColorStateList stateList = a.getColorStateList(0);
                if (stateList != null) {
                    a.recycle();
                    return stateList;
                }
                a.recycle();
                return fallback;
            }
            fallback = getActionTextStateList(context, value.data);
            a.recycle();
            return fallback;
        } finally {
            a.recycle();
        }
    }

    public static ColorStateList getActionTextColorStateList(Context context, @ColorRes int colorId) {
        TypedValue value = new TypedValue();
        context.getResources().getValue(colorId, value, true);
        if (value.type >= 28 && value.type <= 31) {
            return getActionTextStateList(context, value.data);
        }
        if (VERSION.SDK_INT <= 22) {
            return context.getResources().getColorStateList(colorId);
        }
        return context.getColorStateList(colorId);
    }

    @ColorInt
    public static int getColor(Context context, @ColorRes int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static String resolveString(Context context, @AttrRes int attr) {
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(attr, v, true);
        return (String) v.string;
    }

    private static int gravityEnumToAttrInt(GravityEnum value) {
        switch (value) {
            case CENTER:
                return 1;
            case END:
                return 2;
            default:
                return 0;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.afollestad.materialdialogs.GravityEnum resolveGravityEnum(android.content.Context r4, @android.support.annotation.AttrRes int r5, com.afollestad.materialdialogs.GravityEnum r6) {
        /*
        r3 = 0;
        r1 = r4.getTheme();
        r2 = 1;
        r2 = new int[r2];
        r2[r3] = r5;
        r0 = r1.obtainStyledAttributes(r2);
        r1 = 0;
        r2 = gravityEnumToAttrInt(r6);	 Catch:{ all -> 0x002c }
        r1 = r0.getInt(r1, r2);	 Catch:{ all -> 0x002c }
        switch(r1) {
            case 1: goto L_0x0020;
            case 2: goto L_0x0026;
            default: goto L_0x001a;
        };	 Catch:{ all -> 0x002c }
    L_0x001a:
        r1 = com.afollestad.materialdialogs.GravityEnum.START;	 Catch:{ all -> 0x002c }
        r0.recycle();
    L_0x001f:
        return r1;
    L_0x0020:
        r1 = com.afollestad.materialdialogs.GravityEnum.CENTER;	 Catch:{ all -> 0x002c }
        r0.recycle();
        goto L_0x001f;
    L_0x0026:
        r1 = com.afollestad.materialdialogs.GravityEnum.END;	 Catch:{ all -> 0x002c }
        r0.recycle();
        goto L_0x001f;
    L_0x002c:
        r1 = move-exception;
        r0.recycle();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.util.DialogUtils.resolveGravityEnum(android.content.Context, int, com.afollestad.materialdialogs.GravityEnum):com.afollestad.materialdialogs.GravityEnum");
    }

    public static Drawable resolveDrawable(Context context, @AttrRes int attr) {
        return resolveDrawable(context, attr, null);
    }

    private static Drawable resolveDrawable(Context context, @AttrRes int attr, Drawable fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            Drawable d = a.getDrawable(0);
            if (d == null && fallback != null) {
                d = fallback;
            }
            a.recycle();
            return d;
        } catch (Throwable th) {
            a.recycle();
        }
    }

    public static int resolveDimension(Context context, @AttrRes int attr) {
        return resolveDimension(context, attr, -1);
    }

    private static int resolveDimension(Context context, @AttrRes int attr, int fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            int dimensionPixelSize = a.getDimensionPixelSize(0, fallback);
            return dimensionPixelSize;
        } finally {
            a.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, @AttrRes int attr, boolean fallback) {
        TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            boolean z = a.getBoolean(0, fallback);
            return z;
        } finally {
            a.recycle();
        }
    }

    public static boolean resolveBoolean(Context context, @AttrRes int attr) {
        return resolveBoolean(context, attr, false);
    }

    public static boolean isColorDark(@ColorInt int color) {
        return 1.0d - ((((0.299d * ((double) Color.red(color))) + (0.587d * ((double) Color.green(color)))) + (0.114d * ((double) Color.blue(color)))) / 255.0d) >= 0.5d;
    }

    public static void setBackgroundCompat(View view, Drawable d) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(d);
        } else {
            view.setBackground(d);
        }
    }

    public static void showKeyboard(@NonNull DialogInterface di, @NonNull final Builder builder) {
        final MaterialDialog dialog = (MaterialDialog) di;
        if (dialog.getInputEditText() != null) {
            dialog.getInputEditText().post(new Runnable() {
                public void run() {
                    dialog.getInputEditText().requestFocus();
                    InputMethodManager imm = (InputMethodManager) builder.getContext().getSystemService("input_method");
                    if (imm != null) {
                        imm.showSoftInput(dialog.getInputEditText(), 1);
                    }
                }
            });
        }
    }

    public static void hideKeyboard(@NonNull DialogInterface di, @NonNull Builder builder) {
        MaterialDialog dialog = (MaterialDialog) di;
        if (dialog.getInputEditText() != null) {
            InputMethodManager imm = (InputMethodManager) builder.getContext().getSystemService("input_method");
            if (imm != null) {
                View currentFocus = dialog.getCurrentFocus();
                IBinder windowToken = currentFocus != null ? currentFocus.getWindowToken() : dialog.getView().getWindowToken();
                if (windowToken != null) {
                    imm.hideSoftInputFromWindow(windowToken, 0);
                }
            }
        }
    }

    public static ColorStateList getActionTextStateList(Context context, int newPrimaryColor) {
        int fallBackButtonColor = resolveColor(context, 16842806);
        if (newPrimaryColor == 0) {
            newPrimaryColor = fallBackButtonColor;
        }
        states = new int[2][];
        states[0] = new int[]{-16842910};
        states[1] = new int[0];
        return new ColorStateList(states, new int[]{adjustAlpha(newPrimaryColor, 0.4f), newPrimaryColor});
    }

    public static int[] getColorArray(@NonNull Context context, @ArrayRes int array) {
        if (array == 0) {
            return null;
        }
        TypedArray ta = context.getResources().obtainTypedArray(array);
        int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();
        return colors;
    }

    public static <T> boolean isIn(@NonNull T find, @Nullable T[] ary) {
        if (ary == null || ary.length == 0) {
            return false;
        }
        for (T item : ary) {
            if (item.equals(find)) {
                return true;
            }
        }
        return false;
    }
}
