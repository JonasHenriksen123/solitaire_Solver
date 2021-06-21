package group_01.solverapi.picrecaccess;

import group_01.solverapi.exceptions.BadInputException;
import group_01.solverapi.model.Card;
import group_01.solverapi.model.Position;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.lang.NonNull;

public class CardStateDTO implements ICardStateDTO {
    private Card playStack;
    private Card[] topStacks;
    private Card[][] bottomStacks;

    public CardStateDTO(String input) throws ParseException {
        initCardState(input);
    }

    @Override
    public void initCardState(@NonNull String input) throws ParseException {
        input = input.replace('\'', '"');
        input = input.replace("None", "{}");
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(input);

        if (
                !(json.containsKey("bottom")
                        && json.containsKey("left")
                        && json.containsKey("right"))
        ) {
            throw new ParseException(1);
        }

        //get bottomstacks
        bottomStacks = readBottomStacks((JSONArray) json.get("bottom"));

        //get topstacks
        topStacks = readTopStacks((JSONArray) json.get("right"));

        //get playstack
        playStack = readPlayStack((JSONObject)json.get("left"));
    }

    private Card[][] readBottomStacks(JSONArray cards) throws ParseException {
        if (cards.size() != 7) {
            throw new ParseException(2);
        }

        Card[][] stacks = new Card[7][];

        for (int i = 0; i < 7; i++) {
            JSONArray tempCards = (JSONArray) cards.get(i);
            if (tempCards.isEmpty()) {
                continue;
            }
            Card[] newList = new Card[tempCards.size()];
            int a = 0;
            for (Object o : tempCards) {
                JSONObject card = (JSONObject) o;
                Card tempCard = Card.toCard((String) card.get("classname"));
                newList[a] = tempCard;
                a++;
            }

            stacks[i] = newList;
        }


        return stacks;
    }

    private Card[] readTopStacks(JSONArray cards) throws ParseException {
       if (cards.size() != 4) {
           throw new ParseException(3);
       }

       Card[] stacks = new Card[4];

       for (int i = 0; i < 4; i++) {
           JSONObject card = (JSONObject) cards.get(i);
           if (card.isEmpty()) {
               stacks[i] = null;
               continue;
           }

           Card tempCard = Card.toCard((String)card.get("classname"));
           stacks[i] = tempCard;
       }

       return stacks;
    }

    private Card readPlayStack(JSONObject card) throws ParseException {
        if (card.isEmpty()) {
            throw new ParseException(4);
        }

        return Card.toCard((String)card.get("classname"));
    }


    @Override
    public Card[] getCardsFromStack(@NonNull Position pos) throws BadInputException {
        switch (pos.getStackRow()) {
            case PLAY_STACK: {
                return new Card[]{playStack};
            }
            case TOP_STACKS: {
                int p = pos.getPosition();
                if (p > 3 || p < 0)
                    throw new BadInputException(String.format("Trying to get stack %s from top stacks failed", p));
                return new Card[]{topStacks[p]};
            }
            case BOTTOM_STACKS: {
                int p = pos.getPosition();
                if (p > 6 || p < 0)
                    throw new BadInputException(String.format("Trying to get stack %s from bottom stacks failed", p));
                return bottomStacks[p];
            }
            default: {
                throw new BadInputException(String.format("Trying to get unknown stack: %s failed", pos.getStackRow().toString()));
            }
        }
    }
}
