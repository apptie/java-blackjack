package domain.participant;

import domain.card.Card;
import java.util.List;

public final class Dealer extends Participant {

    private static final int STANDARD_GIVEN_SCORE = 17;

    private Dealer(final String name) {
        super(name);
    }

    public static Dealer create() {
        return new Dealer(DEALER_NAME);
    }

    public Result calculateResult(Participant player) {
        final ParticipantCard playerCard = player.participantCard;

        if (checkDealerWin(playerCard)) {
            return Result.LOSE;
        }
        if (checkPlayerWin(playerCard)) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    @Override
    public boolean canDraw() {
        return participantCard.canDraw(STANDARD_GIVEN_SCORE);
    }

    private boolean checkDealerWin(final ParticipantCard playerCard) {
        final ParticipantCard dealerCard = this.participantCard;

        return playerCard.checkBust()
                || dealerCard.checkBlackJack()
                || dealerCard.checkBust() && playerCard.checkBust()
                || !dealerCard.checkBust() && dealerCard.checkGreaterScoreThan(playerCard);
    }

    private boolean checkPlayerWin(final ParticipantCard playerCard) {
        final ParticipantCard dealerCard = this.participantCard;

        return dealerCard.checkBust()
                || playerCard.checkBlackJack()
                || playerCard.checkGreaterScoreThan(dealerCard);
    }

    @Override
    public List<Card> getStartCard() {
        return List.of(participantCard.getFirstCard());
    }
}
