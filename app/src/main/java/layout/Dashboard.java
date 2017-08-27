package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nougatstudio.sotd.R;
import com.nougatstudio.sotd.adapters.Carditem;
import com.nougatstudio.sotd.adapters.CardsAdapter;

import java.util.ArrayList;

import link.fls.swipestack.SwipeStack;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment{
    private SwipeStack cardStack;
    private CardsAdapter cardsAdapter;
    private ArrayList<Carditem> cardItems;
    private View btnCancel;
    private View btnLove;
    private int currentPosition;
private View root;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        cardStack = (SwipeStack)root.findViewById(R.id.swipeStack);
        setCardStackAdapter();
        currentPosition = 0;

        //Handling swipe event of Cards stack
        cardStack.setListener(new SwipeStack.SwipeStackListener() {
            @Override
            public void onViewSwipedToLeft(int position) {
                currentPosition = position + 1;
                Log.d("SWIPE:","Left");
                Toast toast = Toast.makeText(getActivity(), "Swipe Left", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onViewSwipedToRight(int position) {
                currentPosition = position + 1;
                Log.d("SWIPE:","Right");
                Toast toast = Toast.makeText(getActivity(), "Swipe Right", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            public void onStackEmpty() {

            }


        });

     return root;
    }

    private void setCardStackAdapter() {
        cardItems = new ArrayList<>();

        cardItems.add(new Carditem(R.drawable.a, "Aditi Rao Haidri", "23"));
        cardItems.add(new Carditem(R.drawable.f, "Neha Suman", "22"));
        cardItems.add(new Carditem(R.drawable.f, "Disha  patani", "25"));
        cardItems.add(new Carditem(R.drawable.e, "Abhishek", "22"));
        cardItems.add(new Carditem(R.drawable.c, "Nisha", "19"));
        cardItems.add(new Carditem(R.drawable.d, "Nidhi", "21"));
        cardItems.add(new Carditem(R.drawable.b, "Angel Priya", "Kyu Btau ?"));

        cardsAdapter = new CardsAdapter(getActivity(), cardItems);
        cardStack.setAdapter(cardsAdapter);
    }


}
