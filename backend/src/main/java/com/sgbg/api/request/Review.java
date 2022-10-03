package com.sgbg.api.request;

import lombok.Getter;

@Getter
public enum Review {
    BEST(10), GOOD(5), BAD(-10);

    private final int score;
    Review(int score) {
        this.score = score;
    }
}
