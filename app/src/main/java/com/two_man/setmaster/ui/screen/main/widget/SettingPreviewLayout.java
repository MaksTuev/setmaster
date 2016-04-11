package com.two_man.setmaster.ui.screen.main.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.two_man.setmaster.R;
import com.two_man.setmaster.entity.setting.MediaVolumeSetting;
import com.two_man.setmaster.entity.setting.RingSetting;
import com.two_man.setmaster.entity.setting.Setting;

import java.util.ArrayList;

/**
 *
 */
public class SettingPreviewLayout extends LinearLayout {
    public SettingPreviewLayout(Context context) {
        super(context);
        initView(context);
    }

    public SettingPreviewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SettingPreviewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SettingPreviewLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    protected void initView(Context context) {
        inflate(context);
    }

    public void showSettings(ArrayList<Setting> settings) {
        for (Setting setting : settings) {
            ImageView settingView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.setting_preview_layout_item, this, false);
            settingView.setImageResource(getSettingImage(setting));
            this.addView(settingView);
        }
    }

    @DrawableRes
    private int getSettingImage(Setting setting) {
        if (setting instanceof RingSetting) {
            RingSetting ringSetting = (RingSetting) setting;
            return ringSetting.isEnabled()
                    ? R.drawable.ic_setting_ring_on
                    : R.drawable.ic_setting_ring_off;
        } else if (setting instanceof MediaVolumeSetting){
            MediaVolumeSetting ringSetting = (MediaVolumeSetting) setting;
            return ringSetting.isEnabled()
                    ? R.drawable.ic_setting_media_volume_on
                    : R.drawable.ic_setting_media_volume_off;
        } else {
            throw new IllegalArgumentException("Setting "+ setting.getClass().getSimpleName()+ "not supported");
        }

    }

    private void inflate(Context context) {
        inflate(context, R.layout.setting_preview_layout, this);
    }


}