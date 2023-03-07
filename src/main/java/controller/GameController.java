package controller;

import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomUniqueCardSelector;
import domain.game.GameManager;
import domain.participant.Result;
import domain.participant.Participant;
import domain.participant.Participants;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Map;

public final class GameController {

    private final InputView inputView;
    private final OutputView outputView;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        final GameManager gameManager = makeGameManager();

        startGame(gameManager);
        playForPlayers(gameManager);
        playForDealer(gameManager);
        printGameResult(gameManager);
    }

    private GameManager makeGameManager() {
        final Participants participants = makeParticipants();
        final Deck deck = Deck.create(new RandomUniqueCardSelector());

        return GameManager.create(deck, participants);
    }

    private Participants makeParticipants() {
        return InputProcessHandler.repeat(inputView::readParticipantNames,
                outputView::guideParticipantsName,
                Participants::create,
                outputView::printExceptionMessage);
    }

    private void startGame(final GameManager gameManager) {
        gameManager.giveStartCards();

        printTotalParticipantStartCards(gameManager);
    }

    private void printTotalParticipantStartCards(final GameManager gameManager) {
        final List<Participant> participants = gameManager.getParticipants();
        final List<String> participantsName = gameManager.getParticipantsName();

        outputView.printGiveParticipantStartCardMessage(participantsName);
        outputView.printTotalParticipantStartCards(participants);
    }

    private void playForPlayers(final GameManager gameManager) {
        final int playerSize = gameManager.playerSize();

        for (int playerOrder = 0; playerOrder < playerSize; playerOrder++) {
            handleDrawCard(gameManager, playerOrder);
        }
    }

    private void handleDrawCard(final GameManager gameManager, final int playerOrder) {
        final String playerName = gameManager.findPlayerNameByOrder(playerOrder);
        DrawCardCommand drawCardCommand = DrawCardCommand.CARD_DRAW_AGAIN;

        while (canDrawCard(gameManager, playerOrder, drawCardCommand)) {
            drawCardCommand = inputDrawCardCommand(playerName);

            processPlayerDrawCard(gameManager, playerOrder, drawCardCommand);
            printPlayerCards(gameManager, playerOrder, playerName);
        }
    }

    private void printPlayerCards(final GameManager gameManager, final int playerOrder, final String playerName) {
        final List<Card> playerCards = gameManager.findPlayerCardsByOrder(playerOrder);

        outputView.printParticipantCards(playerName, playerCards);
    }

    private DrawCardCommand inputDrawCardCommand(final String playerName) {
        return InputProcessHandler.repeat(inputView::readDrawCardCommand,
                () -> outputView.guideDrawCard(playerName),
                DrawCardCommand::findCardCommand,
                outputView::printExceptionMessage);
    }

    private boolean canDrawCard(final GameManager gameManager, final int playerOrder,
            final DrawCardCommand drawCardCommand) {
        return gameManager.canPlayerDrawByOrder(playerOrder) && drawCardCommand.isDrawAgain();
    }

    private void processPlayerDrawCard(final GameManager gameManager, final int playerOrder,
            final DrawCardCommand drawCardCommand) {
        if (drawCardCommand.isDrawAgain()) {
            gameManager.givePlayerCard(playerOrder);
        }
    }

    private void playForDealer(final GameManager gameManager) {
        final String dealerName = gameManager.findDealerName();
        final int drawCardCount = gameManager.drawCardsForDealer();

        outputView.guideDealerGivenCard(dealerName, drawCardCount);
    }

    private void printGameResult(final GameManager gameManager) {
        final List<Participant> totalParticipants = gameManager.getParticipants();

        outputView.printCardResult(totalParticipants);
        printFinalGameResult(gameManager);
    }

    private void printFinalGameResult(final GameManager gameManager) {
        final String dealerName = gameManager.findDealerName();
        final Map<String, Result> totalPlayerGameResult = gameManager.getTotalPlayerGameResult();

        outputView.guideFinalGameResult();
        outputView.printFinalGameResult(dealerName, totalPlayerGameResult);
    }
}
