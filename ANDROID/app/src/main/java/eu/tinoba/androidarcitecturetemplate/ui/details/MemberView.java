package eu.tinoba.androidarcitecturetemplate.ui.details;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import eu.tinoba.androidarcitecturetemplate.R;

/**
 * @author antonio on 02/02/18
 */

public class MemberView extends LinearLayout {

    TextView name;
    TextView email;

    public MemberView(Context context) {
        this(context, null);
    }

    public MemberView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MemberView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.view_member_detail, this, true);

        name = (TextView) layout.findViewById(R.id.member_name);
        email = (TextView) layout.findViewById(R.id.member_mail);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setEmail(String name) {
        this.email.setText(name);
    }
}
