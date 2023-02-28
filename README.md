# 🪜 블랙잭 게임 구현 미션

## Pair: 져니 [⛄️](http://github.com/cl8d), 지토 [👽](https://github.com/apptie)

## ✔️ 기능 요구사항

### Players (참가자들)

- [x] 플레이어의 수는 1명 ~ 7명까지 가능하다.
- [ ] 플레이어의 이름은 중복될 수 없다.
- **Player (참가자)**
    - [ ] 플레이어가 가지고 있는 카드의 합이 21을 초과하면 해당 플레이어는 탈락한다.
        - **PlayerName (참가자 이름)**
            - [x] 플레이어의 이름의 앞뒤를 공백을 제거한다.
            - [x] 플레이어의 이름은 공백이 들어갈 수 없다.
            - [x] 플레이어의 이름은 1자 ~ 20자까지 가능하다.
        - **PlayerRole (참가자 역할)**
            - [x] 플레이어는 일반 참가자와 딜러로 구분된다.
            - [x] 플레이어의 역할이 딜러인지 확인한다.
        - **PlayerCard (참가자가 가진 카드)**
            - [ ] 플레이어는 자신이 가진 카드에 대한 정보를 가지고 있어야 한다.
            - [ ] 카드에 적힌 숫자의 합을 계산한다.

### Cards (카드들)

- [ ] 카드는 중복될 수 없다.
- [ ] 52장의 카드를 섞은 카드뭉치(덱)을 반환한다.
- [ ] 생성할 카드에 대한 카드 패턴과 카드 번호를 뽑는다.
- **Card (카드)**
    - [ ] 카드 패턴과 카드 번호를 조합하여, 카드를 생성한다.
        - **CardPattern (카드 패턴)**
            - [x] '하트', '스페이드', '다이아', '클로버'를 반환한다.
        - **CardNumber (카드 번호)**
            - [x] 2~10, Ace, King, Queen, Jack을 반환한다.

### Deck (섞인 카드뭉치)

- [ ] 카드 뭉치를 관리한다.
- [ ] 가장 위에 있는 카드 한 장을 꺼낸다.

### Generator (무작위 값 생성기)

- [ ] 범위 내의 값을 랜덤하게 생성한다.

### GameResult (블랙잭의 게임 결과)

- [ ] 일반 참가자가 가진 카드의 합인 21에 가장 가까운 일반 참가자가 승리한다.
- [ ] 딜러의 카드 합이 21 초과면, 모든 플레이어가 승리한다.
- [ ] 일반 참가자와 딜러의 카드 합이 모두 21을 초과하면, 딜러가 승리한다.
- [ ] 일반 참가자와 딜러가 모두 21을 초과하지 않으면서 동일한 카드의 합을 가지고 있다면, 모두를 무승부로 간주한다.

### CardManager (카드 관리자)

- [ ] 게임이 시작되면, 각 플레이어에게 카드 2장을 랜덤하게 나눠줘야 한다.
- [ ] 플레이어가 원한다면, 랜덤하게 카드 1장을 건네준다.
- [ ] 모든 일반 참가자가 카드를 받지 않는다고 했을 때, 딜러가 가진 카드의 합이 16 이하면 17 이상이 될 때까지 카드를 반복해서 받는다.

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

