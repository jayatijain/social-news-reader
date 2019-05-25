package com.paypal.formatter.pojos;

import com.paypal.formatter.pojos.Constants.TYPES;
import com.paypal.formatter.Iface.Input;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.*;


@AllArgsConstructor
public class Pojo implements Input {

    @NotNull
    public long id; // identifier to link Tweet and Processed Pojo containing entity information for tweet
    @NotNull
    public Map<Position, Word> words;

    public boolean isValid() {

        Set<Position> positions = words.keySet();
        SortedSet<Position> sortedPositions = new TreeSet();
        sortedPositions.addAll(positions);
        Iterator iterator = sortedPositions.iterator();
        Position prev = null;

        while (iterator.hasNext()) {
            if (prev == null) {
                prev = (Position) iterator.next();
                if (!prev.isValid()) {
                    return false;
                }
            } else {
                Position curr = (Position) iterator.next();
                if (prev.end < curr.start && curr.isValid()) {
                    return false;
                }
            }
        }
        return words.values().stream().allMatch(Word::isValid);
    }

    @AllArgsConstructor
    public static class Position implements Input, Comparable {
        @Getter
        int start;
        @Getter
        int end;

        public boolean isValid() {
            return start < end;
        }

        public int compareTo(Object o) {
            Position position = (Position) o;
            return this.start < position.start ? 1 : -1;
        }
    }

    @AllArgsConstructor
    public static class Word implements Input {

        @Getter
        String text;
        @Getter
        TYPES type;

        public Word(String text){
            this.text = text;
            this.type = TYPES.NONE;
        }

        public boolean isValid() {
            return text.length() > 0;
        }
    }

//    public static void main(String[] args) {
//        HashMap<Position, Word> map = new HashMap<>();
//        map.put(new Position(1,2),new Word("jayati"));
//        Pojo pojo = new Pojo(1L, new HashMap<>(new Position(1,2),) )
//    }
}
