package controller;

import domain.CardSelector;
import domain.card.Card;
import domain.card.Deck;
import domain.game.GameManager;
import domain.participant.Result;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static view.message.Message.DEALER_DRAW_MESSAGE;

public class GameController {

    private static final int START_GIVEN_COUNT = 2;
    private static final int PARTICIPANT_GIVEN_COUNT = 1;
    private static final int PLAYER_ORDER_OFFSET = 1;
    private static final int DEALER_ORDER = 0;

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start(final CardSelector cardSelector) {
        Participants participants = makeParticipants();
        Deck deck = Deck.create(cardSelector);
        GameManager gameManager = GameManager.create(deck, participants);
        handCards(participants, gameManager);
        printParticipantCards(participants);
        drawPlayersCard(participants, gameManager);
        handleDealerCards(participants, gameManager);
        printGameResult(participants);
        printFinalGameResult(participants);
    }

    private void printGameResult(final Participants participants) {
        OutputView.print(System.lineSeparator().trim());
        Participant dealer = participants.findDealer();
        printParticipantCardResult(dealer);
        List<Participant> players = participants.findPlayers();
        players.forEach(this::printParticipantCardResult);
    }

    private void printParticipantCardResult(final Participant participant) {
        List<Card> participantCards = participant.getCard();
        int participantScore = participant.calculateScore();
        outputView.printCardResult(participant.getName(), participantCards, participantScore);
    }

    private Participants makeParticipants() {
        return inputView.getInputWithRetry(() -> {
            List<String> participantNames = inputView.getParticipantNames();
            return Participants.create(participantNames);
        });
    }

    private void handCards(final Participants participants, final GameManager gameManager) {
        int size = participants.size();
        IntStream.range(0, size)
            .forEach(participantOrder -> gameManager.giveCards(participantOrder, START_GIVEN_COUNT));
    }

    private void printParticipantCards(final Participants participants) {
        outputView.printParticipantMessage(participants);
        printDealerCard(participants);
        printPlayerCards(participants);
    }

    private void printDealerCard(final Participants participants) {
        Dealer dealer = (Dealer) participants.findDealer();
        Card dealerFirstCard = dealer.getFirstCard();
        outputView.printDealerCard(dealer.getName(), dealerFirstCard);
    }

    private void printPlayerCards(final Participants participants) {
        List<Participant> players = participants.findPlayers();
        for (Participant player : players) {
            List<Card> playerCards = player.getCard();
            outputView.printPlayerCard(player.getName(), playerCards);
        }
    }

    private void drawPlayersCard(final Participants participants, final GameManager gameManager) {
        List<Participant> players = participants.findPlayers();
        for (int playerIndex = 0; playerIndex < players.size(); playerIndex++) {
            Participant player = players.get(playerIndex);
            handleDrawCard(gameManager, playerIndex, player);
        }
    }

    private void handleDrawCard(final GameManager gameManager, final int playerIndex, final Participant player) {
        DrawCardCommand drawCardCommand;
        do {
            drawCardCommand = getDrawCardCommand(player);
            if (drawCardCommand.isDrawAgain()) {
                gameManager.giveCards(playerIndex + PLAYER_ORDER_OFFSET, PARTICIPANT_GIVEN_COUNT);
            }
            outputView.printPlayerCard(player.getName(), player.getCard());
        } while (canDrawCard(player, drawCardCommand));
    }

    private boolean canDrawCard(final Participant player, final DrawCardCommand drawCardCommand) {
        return player.canDraw() && drawCardCommand.isDrawAgain();
    }

    private DrawCardCommand getDrawCardCommand(final Participant player) {
        return inputView.getInputWithRetry(() -> {
            String command = inputView.getDrawCardCommand(player.getName());
            return DrawCardCommand.findCardCommand(command);
        });
    }

    private void handleDealerCards(final Participants participants, final GameManager gameManager) {
        Dealer dealer = (Dealer) participants.findDealer();
        OutputView.print(System.lineSeparator().trim());
        while (dealer.canDraw()) {
            gameManager.giveCards(DEALER_ORDER, PARTICIPANT_GIVEN_COUNT);
            OutputView.print(String.format(DEALER_DRAW_MESSAGE.getMessage(), dealer.getName()));
        }
    }

    private void printFinalGameResult(final Participants participants) {
        Dealer dealer = (Dealer) participants.findDealer();
        Map<String, Result> playerGameResults = makeFinalGameResult(participants, dealer);
        outputView.printFinalGameResult(dealer.getName(), playerGameResults);
    }

    private Map<String, Result> makeFinalGameResult(final Participants participants, final Dealer dealer) {
        return participants.findPlayers().stream()
                .collect(Collectors.toMap(Participant::getName, dealer::calculateResult,
                (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }
}
