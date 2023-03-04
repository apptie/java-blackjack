package domain.participant;

import domain.card.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;
    private static final int DEALER_ORDER = 0;
    private static final int PARTICIPANT_START_ORDER = 1;

    private final List<Participant> participants;

    private Participants(final List<String> playerNames) {
        final List<String> trimPlayerNames = processTrimPlayerNames(playerNames);

        validateDuplicateNames(trimPlayerNames);
        validatePlayerCount(trimPlayerNames);

        this.participants = makeParticipants(trimPlayerNames);
    }

    public static Participants create(final List<String> playerNames) {
        return new Participants(playerNames);
    }

    public void addCard(final int participantOrder, final Card card) {
        final Participant participant = participants.get(participantOrder);

        participant.addCard(card);
    }

    public Participant findDealer() {
        return participants.get(DEALER_ORDER);
    }

    public List<Participant> findPlayers() {
        return participants.subList(PARTICIPANT_START_ORDER, participants.size());
    }

    private List<String> processTrimPlayerNames(final List<String> playerNames) {
        return playerNames.stream().map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplicateNames(final List<String> playerNames) {
        final Set<String> uniqueNames = new HashSet<>(playerNames);

        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(final List<String> playerNames) {
        final int playerCount = playerNames.size();

        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }

    private List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::create)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Participant> makeParticipants(final List<String> playerNames) {
        final List<Participant> participants = new ArrayList<>();
        final List<Player> players = makePlayers(playerNames);

        participants.add(Dealer.create());
        participants.addAll(players);
        return participants;
    }

    public int size() {
        return participants.size();
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
