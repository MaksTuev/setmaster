/*
 * Copyright 2016 Maxim Tuev.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.agna.setmaster.ui.screen.profile.setting.holder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agna.setmaster.R;
import com.agna.setmaster.domain.setting.RingSetting;
import com.agna.setmaster.ui.screen.profile.setting.SettingGridAdapter;

/**
 *
 */
public class RingSettingHolder extends RecyclerView.ViewHolder {
    protected final SettingGridItemPreviewProgress value;
    protected final TextView name;
    protected final ImageView icon;
    private final View container;

    public RingSettingHolder(View itemView, SettingGridAdapter.OnSettingHolderClickListener listener) {
        super(itemView);
        container = itemView.findViewById(R.id.setting_container);
        icon = (ImageView) itemView.findViewById(R.id.setting_icon);
        name = (TextView) itemView.findViewById(R.id.setting_name);
        value = (SettingGridItemPreviewProgress) itemView.findViewById(R.id.setting_value);
        container.setOnClickListener(v -> listener.onClick(v, getAdapterPosition()));
    }

    public void bind(RingSetting setting) {
        icon.setImageResource(setting.isEnabled()
                ? R.drawable.ic_setting_ring_on
                : R.drawable.ic_setting_ring_off);
        name.setText(R.string.setting_name_ring);
        value.setValue(setting.getValue());
    }

    public static RingSettingHolder newInstance(ViewGroup parent, SettingGridAdapter.OnSettingHolderClickListener listener) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.setting_volume_grid_item_layout, parent, false);
        return new RingSettingHolder(v, listener);
    }


}
