package tw.com.softworld.api.broadcastSample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tw.com.softworld.api.R;
import tw.com.softworld.messagescenter.BroadcastCenter;
import tw.com.softworld.messagescenter.CustomReceiver;
import tw.com.softworld.messagescenter.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class InboxFragment1 extends Fragment {

    private BroadcastCenter bc;
    private CustomReceiver cr;
    private TextView tv;


    public InboxFragment1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("Hello blank fragment1");
        cr = new CustomReceiver() {
            @Override
            public void onBroadcastReceive(Result result) {
                doSomething(result);
            }
        };
        bc = new BroadcastCenter(getActivity(), cr);
        bc.gotMessages("A001");
        return view;
    }

    private void doSomething(Result result) {
        String text = result.getBundle().getString("bundle1");
        tv.setText(text);
    }

    @Override
    public void onDestroy() {
        super.onDestroyView();
        bc.release();
    }
}
