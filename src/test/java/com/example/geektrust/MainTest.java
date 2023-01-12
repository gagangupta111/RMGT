package com.example.geektrust;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void testMain() throws IOException {
        String[] args = new String[]{"sample_input/input1.txt"};
        Main.main(args);
    }

    @Test
    public void testMain2() throws IOException {
        String[] args = new String[]{"sample_input/input2.txt"};
        Main.main(args);
    }

    @Test
    public void testMain3() throws IOException {
        String[] args = new String[]{"sample_input/input3.txt"};
        Main.main(args);
    }

}