package com.example.jokebot;

public class Joke {

    private String jokeSetup;
    private String jokePunchline;

    public Joke() {
        this.jokeSetup = "";
        this.jokePunchline = "";
    }

    public Joke(String jokeSetup, String jokePunchline) {
        this.jokeSetup = jokeSetup;
        this.jokePunchline = jokePunchline;
    }

    public String getJokeSetup() {
        return jokeSetup;
    }

    public void setJokeSetup(String jokeSetup) {
        this.jokeSetup = jokeSetup;
    }

    public String getJokePunchline() {
        return jokePunchline;
    }

    public void setJokePunchline(String jokePunchline) {
        this.jokePunchline = jokePunchline;
    }

    @Override
    public String toString() {
        return "Joke{" +
                "jokeSetup='" + jokeSetup + "'\n" +
                ", jokePunchline='" + jokePunchline + '\'' +
                '}';
    }
}
