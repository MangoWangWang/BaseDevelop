package com.mango.know.ui.loading.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mango.know.R;
import com.mango.know.utils.NetWorkUtil;

import static com.billy.android.loading.Gloading.*;

/**
 * simple loading status view for global usage
 * @author billy.qi
 * @since 19/3/19 23:12
 */
@SuppressLint("ViewConstructor")
public class GlobalLoadingStatusView extends LinearLayout implements View.OnClickListener {

    private final TextView mTextView;
    private final Runnable mRetryTask;
    private final ImageView mImageView;

    public GlobalLoadingStatusView(Context context, Runnable retryTask) {
        super(context);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(context).inflate(R.layout.view_global_loading_status, this, true);
        mImageView = findViewById(R.id.image);
        mTextView = findViewById(R.id.text);
        this.mRetryTask = retryTask;
        setBackgroundColor(0xFFF0F0F0);
    }

    public void setMsgViewVisibility(boolean visible) {
        mTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setStatus(int status) {
        boolean show = true;
        View.OnClickListener onClickListener = null;
        int image = R.drawable.loading;
        int str = R.string.str_none;
        switch (status) {
            case STATUS_LOAD_SUCCESS: show = false; break;
            case STATUS_LOADING: str = R.string.loading; break;
            case STATUS_LOAD_FAILED:
                str = R.string.load_failed;
                image = R.mipmap.icon_failed;
                Boolean networkConn = NetWorkUtil.isNetworkAvailable(getContext());
                if (networkConn != null && !networkConn) {
                    str = R.string.network_tip;
                    image = R.mipmap.icon_no_wifi;
                }
                onClickListener = this;
                break;
            case STATUS_EMPTY_DATA:
                str = R.string.no_data;
                image = R.mipmap.icon_empty;
                break;
            default: break;
        }
        mImageView.setImageResource(image);
        setOnClickListener(onClickListener);
        mTextView.setText(str);
        setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (mRetryTask != null) {

            mRetryTask.run();
        }
    }
}
