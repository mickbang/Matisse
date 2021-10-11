/*
 * Copyright 2017 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhihu.matisse.internal.ui.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.zhihu.matisse.R;

public class IncapableDialogCus extends DialogFragment {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_MESSAGE = "extra_message";

    public static IncapableDialogCus newInstance(String title, String message) {
        IncapableDialogCus dialog = new IncapableDialogCus();
        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, title);
        args.putString(EXTRA_MESSAGE, message);
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(EXTRA_TITLE);
        String message = getArguments().getString(EXTRA_MESSAGE);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.transparentDialogStyle);
        View root = getActivity().getLayoutInflater().inflate(R.layout.dialog_app_simple,null,false);
        builder.setView(root);
        TextView tvTitle = root.findViewById(R.id.titleTv);
        TextView tvMsg = root.findViewById(R.id.contentTv);
        TextView rightTv = root.findViewById(R.id.rightTv);
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }else {
            tvTitle.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(message)) {
            tvMsg.setText(message);
        }
        rightTv.setText(R.string.button_ok);
        rightTv.setOnClickListener(v->{
           dismiss();
        });
        return builder.create();
    }
}
