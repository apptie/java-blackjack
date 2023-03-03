# 🪜 블랙잭 게임 구현 미션

## Pair: 져니 [⛄️](http://github.com/cl8d), 지토 [👽](https://github.com/apptie)

## ✔️ 기능 요구사항

### Participants (참가자들)

- [x] 플레이어의 수는 1명 ~ 7명까지 가능하다.
- [x] 플레이어의 이름은 중복될 수 없다.

### Participant (참가자)

- 플레이어와 딜러를 포괄하는 추상 클래스.
    - [x] 카드를 한 장 받는다.
    - [x] 카드에 적힌 숫자의 합을 계산한다.
    - [x] 가진 모든 카드를 조회한다.
    - [x] 버스트인지 확인한다.
    - [x] 블랙잭인지 확인한다.
    - [x] 참가자의 이름을 조회한다.
    - [x] 분배된 카드를 조회한다.
    - [x] 딜러가 카드를 더 받을 수 있는지 확인한다.
    - **ParticipantName (참가자 이름)**
        - [x] 플레이어의 이름의 앞뒤를 공백을 제거한다.
        - [x] 플레이어의 이름은 공백이 들어갈 수 없다.
        - [x] 플레이어의 이름은 1자 ~ 20자까지 가능하다.
    - **ParticipantCard (참가자가 가진 카드)**
        - [x] 모든 카드를 조회한다.
        - [x] 가장 첫 번째 카드를 조회한다.
        - [x] 카드에 적힌 숫자의 합을 계산한다.
        - [x] 버스트 (카드의 합이 21 초과)인지 확인한다.
        - [x] 블랙잭 (카드가 2장이면서, 합이 21)인지 확인한다.
        - [x] 카드를 추가한다.

### Player (플레이어)

- [x] 플레이어는 '딜러'라는 이름을 가질 수 없다.

### Dealer (딜러)

- [x] 첫 번째 카드를 조회한다.
- [x] 딜러가 카드를 더 받을지 여부를 반환한다.

### Deck (카드들)

- [x] 52장의 카드 뭉치를 생성한다.
- [x] 카드 한 장을 뽑는다.
- **Card (카드)**
    - [x] 카드 패턴과 카드 번호를 조합하여, 카드를 생성한다.
    - [x] 카드가 Ace인지 확인한다.
    - **CardPattern (카드 패턴)**
        - [x] '하트', '스페이드', '다이아', '클로버'를 반환한다.
    - **CardNumber (카드 번호)**
        - [x] 2~10, Ace, King, Queen, Jack을 반환한다.
        - [x] 카드가 Ace인지 확인한다.
        - [x] 카드의 번호를 조회한다.
- **CardRandomShuffler**
    - [x] 주어진 카드 뭉치를 랜덤으로 섞는다.

### CardShuffler (카드 셔플러)

- [x] 주어진 카드 뭉치를 섞는다.

### GameResult (블랙잭의 게임 결과)

- [x] 딜러가 버스트라면, 모든 플레이어가 승리한다.
- [x] 플레이어가 버스트라면, 딜러가 승리한다.
- [x] 일반 참가자와 딜러 모두 버스트라면, 딜러가 승리한다.
- [x] 딜러가 블랙잭이라면, 딜러가 승리한다.
- [x] 플레이어가 블랙잭이라면, 플레이어가 승리한다.
- [x] 딜러와 플레이어 중에 21에 가장 가까운 참가자가 승리한다.
- [x] 위의 모든 경우가 아니면서 딜러와 플레이어가 동일한 카드의 합을 가지고 있다면, 무승부로 간주한다.

### GameManager (게임 관리자)

- [x] 참가자가 원한다면, 랜덤하게 카드 1장을 건네준다.
- [x] 딜러가 카드를 더 받을 수 있는지 확인한다.

### InputView

- [ ] 플레이어의 이름을 쉼표로 구분하여 입력받는다.
- [ ] 게임에 참여할 일반 참가자의 이름을 입력받는다.
- [ ] 일반 참가자가 카드를 한 장 더 받을지에 대한 유무를 입력받는다.

### OutputView

- [ ] 게임 시작 시, 플레이어의 카드 정보를 출력한다.
- [ ] 카드를 받을지에 대한 여부를 입력하면, 현재 가지고 있는 카드를 출력한다.
- [ ] 게임이 완료되면, 플레이어의 카드 종류와 카드의 합을 출력한다.
- [ ] 게임이 완료되면, 각 플레이어의 카드 합을 판단하여 최종 승패를 출력한다.

---

## ✔ 기능 다이어그램

<img src = "https://s3.us-west-2.amazonaws.com/secure.notion-static.com/fc61c841-19f2-40ec-9485-076afd44de74/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20230228%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20230228T055401Z&X-Amz-Expires=86400&X-Amz-Signature=cd100845afc10ac9bdc0c902cdd7c6156f8dd36e1e94345b2245a4bce7066f8f&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject">

---

## ✔️ 프로그래밍 요구사항

- indent(인덴트, 들여쓰기) depth를 2를 넘지 않도록 구현한다. 1까지만 허용한다.
- 3항 연산자를 쓰지 않는다.
- else 예약어를 쓰지 않는다. (switch/case 포함)
- 모든 기능을 TDD로 구현해 단위 테스트가 존재해야 한다. 단, UI(System.out, System.in) 로직은 제외
- 핵심 로직을 구현하는 코드와 UI를 담당하는 로직을 구분한다.
- 함수(또는 메서드)의 길이가 10라인을 넘어가지 않도록 구현한다.
- 함수(또는 메소드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- 배열 대신 컬렉션을 사용한다.
- 모든 원시 값과 문자열을 포장한다
- 줄여 쓰지 않는다(축약 금지).
- 일급 컬렉션을 쓴다.
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 딜러와 플레이어에서 발생하는 중복 코드를 제거해야 한다.

