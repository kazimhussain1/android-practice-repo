package com.example.jokebot;

import java.util.ArrayList;

public class JokeBot extends Bot {

    protected ArrayList<Joke> jokesIKnow = null;

    public JokeBot() {

    }

    public JokeBot(String name) {
        super(name);
        this.jokesIKnow = JokeWriter.getJokeListOne();
    }

    public void tellJoke(){


        Joke aJoke = JokeWriter.getJokeListOne().get( (new Double(JokeWriter.getJokeListOne().size() * Math.random()).intValue()));

        talk(aJoke.getJokeSetup());
        talk(aJoke.getJokePunchline());
    }
}
