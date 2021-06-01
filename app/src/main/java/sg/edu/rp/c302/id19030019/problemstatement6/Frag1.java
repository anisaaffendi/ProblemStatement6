package sg.edu.rp.c302.id19030019.problemstatement6;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.Random;

public class Frag1 extends Fragment {

    LinearLayout layout;
    Button btnFrag1;
    ImageView iv;
    String[] colors = {"#B5B4E5", "#ABEEAA", "#A9DCD6", "#FFD358"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag1, container, false);

        layout = view.findViewById(R.id.Frag1);
        btnFrag1 = view.findViewById(R.id.btnFrag1);
        iv = view.findViewById(R.id.iv);

        String imageurl = "https://wtffunfact.com/wp-content/uploads/2021/05/WTF-Fun-Fact-From-NFL-To-Serial-Killer.png";
        Picasso.with(getActivity()).load(imageurl).into(iv);

        Random r = new Random();
        layout.setBackgroundColor(Color.parseColor(colors[r.nextInt(colors.length)]));

        btnFrag1.setOnClickListener(v -> {
            int num = r.nextInt(colors.length);
            layout.setBackgroundColor(Color.parseColor(colors[num]));
        });
        return view;
    }
}