package com.guestlogix.marianozorrilla.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gradients {

    private final static List<List<String>> gradients = new ArrayList<List<String>>() {{
        add(new ArrayList<String>() {{
            add("#FF5E3A");
            add("#FF2A68");
        }});
        add(new ArrayList<String>() {{
            add("#FF9500");
            add("#FF5E3A");
        }});
        add(new ArrayList<String>() {{
            add("#C644FC");
            add("#5856D6");
        }});
        add(new ArrayList<String>() {{
            add("#EF4DB6");
            add("#C643FC");
        }});
        add(new ArrayList<String>() {{
            add("#55EFCB");
            add("#5BCAFF");
        }});
        add(new ArrayList<String>() {{
            add("#FB2B69");
            add("#FF5B37");
        }});
        add(new ArrayList<String>() {{
            add("#C86EDF");
            add("#E4B7F0");
        }});
        add(new ArrayList<String>() {{
            add("#87FC70");
            add("#0BD318");
        }});
        add(new ArrayList<String>() {{
            add("#1AD6FD");
            add("#1D62F0");
        }});
    }};

    public static List<String> getGradient() {
        Random random = new Random();
        return gradients.get(random.nextInt(gradients.size() - 1));
    }
}
