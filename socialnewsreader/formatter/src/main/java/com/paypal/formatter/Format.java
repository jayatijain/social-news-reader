package com.paypal.formatter;

import com.paypal.formatter.Iface.Wrapper;
import com.paypal.formatter.Iface.WrapperSelector;
import com.paypal.formatter.pojos.Constants;
import com.paypal.formatter.pojos.Pojo;
import com.paypal.formatter.pojos.Pojo.Position;
import com.paypal.formatter.pojos.Tweet;
import com.paypal.formatter.Impl.WrapperSelectorImpl;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import static com.paypal.formatter.pojos.Pojo.Word;

@NoArgsConstructor
public class Format {
    private static final WrapperSelector wrapperSelector = new WrapperSelectorImpl();
    private Wrapper wrapper;


    public String format(Pojo input, Tweet tweet) {
        String output = tweet.tweet;

        if (!input.isValid() || !tweet.isValid() || tweet.id != input.id) {
            return null;
        }

        Map<Position, Word> finalWordMap = new HashMap<>();

        for (Position position : input.words.keySet()) {
            Word wordToReplace = input.words.get(position);
            wrapper = wrapperSelector.selectWrapper(wordToReplace.getType());
            String replaced = wrapper.wrap(wordToReplace.getText());
            finalWordMap.put(position, new Word(replaced));
        }

        //Maps dont preserve order hence, sort in reverse order
        List<Entry<Position, Word>> entries =
                finalWordMap.entrySet().stream()
                        .sorted((o1, o2) -> o1.getKey().getEnd() < o2.getKey().getEnd() ? 1 : -1)
                        .collect(Collectors.toList());

        //now replace
        for (Entry<Position, Word> entry : entries) {
            String pre = output.substring(0, entry.getKey().getStart());
            String post = output.substring(entry.getKey().getEnd() +1);
            output = pre + entry.getValue().getText() + post;
        }

        return output;

    }

    public static void main(String[] args) {
        Format format = new Format();
        String tweet = "@Jayati, PayPal url is www.paypal.com";
        Word word1 = new Word("Jayati", Constants.TYPES.USERNAME);
        Word word2 = new Word("PayPal", Constants.TYPES.ENTITY);
        Word word3 = new Word("www.paypal.com", Constants.TYPES.LINK);
        Position p1 = new Position(1,6);
        Position p2 = new Position(9,14);
        Position p3 = new Position(23,36);

        Map<Position, Word> map = new HashMap<>();
        map.put(p1,word1);
        map.put(p2,word2);
        map.put(p3,word3);

        Pojo pojo = new Pojo(1L, map);

        String output = format.format(pojo,new Tweet(1L, tweet));
        System.out.println(output);
    }

}
