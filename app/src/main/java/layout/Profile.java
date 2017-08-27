package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.nougatstudio.sotd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment implements  View.OnClickListener{

Button inviteButton;
    View root;
    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root  = inflater.inflate(R.layout.fragment_profile, container, false);
        inviteButton = (Button)root.findViewById(R.id.inviteButton);
        inviteButton.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.inviteButton:
                Toast toast = Toast.makeText(getActivity(), "Invite Click", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
    }
}
