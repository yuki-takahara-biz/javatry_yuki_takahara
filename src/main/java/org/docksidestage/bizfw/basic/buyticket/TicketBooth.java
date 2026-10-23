/*
 * Copyright 2019-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

// done takahara JavaDocコメント、せっかくなのでauthorを追加しましょう by jflute (2026/10/09)
/**
 * @author jflute, takahara
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;
    private static final int NIGHT_ONLY_TWO_DAY_PRICE = 7400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase
    private int price;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    // you can rewrite comments for your own language by jflute
    // e.g. Japanese
    // /**
    // * 1Dayパスポートを買う、パークゲスト用のメソッド。
    // * @param handedMoney パークゲストから手渡しされたお金(金額) (NotNull, NotMinus)
    // * @throws TicketSoldOutException ブース内のチケットが売り切れだったら
    // * @throws TicketShortMoneyException 買うのに金額が足りなかったら
    // */
    // done takahara 戻り値を追加したので、JavaDocにも追加しましょう by jflute (2026/10/09)
    // done jflute JavaDocを書きました！
    // (日本語でOK)
    // TODO takahara JavaDoc, 戻り値の説明、@returnを追加でお願いします by jflute (2026/10/23)
    // TODO takahara すでに "Buy one-day passport" ではない。 by jflute (2026/10/23)
    // TODO takahara もし、ticketTypeに allDoy (おーるどい) と間違い指定されてもある程度動いちゃう by jflute (2026/10/23)
    // TODO takahara 修行++: originalEntranceCount が int で間違った値を入れることができちゃう。 by jflute (2026/10/23)
    // これ本当はどうにかしたい。(とはいえ、元々のbuyOneDay...()方式に戻すのはアレなので今のままでどうにか)
    // 一方で、元々のbuyOneDay...()方式の特徴も改めて知ることができた。
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @param originalEntranceCount 何日利用できるチケットか
     * @param ticketType 一日中使用できる場合はallDay、夜のみ使用できる場合はnightOnly
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    public TicketBuyResult buyPassport(int handedMoney, int originalEntranceCount, String ticketType) {
        isQuantityEnough();
        isHandedMoneyEnough(handedMoney, originalEntranceCount, ticketType);
        calculatePrice(originalEntranceCount, ticketType);

        --quantity;
        if (salesProceeds != null) {
            salesProceeds += price;
        } else {
            salesProceeds = price;
        }
        return new TicketBuyResult(price, handedMoney, originalEntranceCount, ticketType);
    }

    // TODO takahara step4でやったように、メソッドの定義順序、呼び出し順序に合わせてみましょう by jflute (2026/10/23)

    // done jflute "入場回数"と"一日/夜のみ"の情報からpriceを導出するメソッドを定義しました
    /**
     * "入場回数"と"一日/夜のみ"の情報からpriceを導出するメソッド
     * @param originalEntranceCount 入場回数
     * @param ticketType "一日/夜のみ"の情報
     */
    private void calculatePrice(int originalEntranceCount, String ticketType) {
        // TODO takahara 3を入れた場合、どうなる？汎用buyメソッドで3を指定できちゃうので by jflute (2026/10/23)
        // 基本的には例外で落とすとかの方が良い。スルーは誤動作になっちゃう。
        // 3だとpriceに前の人の金額が入ってて、売上計上までされてしまう。
        // かつ、在庫が減ってしまう。(実際は買えてないのに)
        // TODO takahara priceに前の人の情報が残ってるのが怖い by jflute (2026/10/23)
        // 頑張れば間違った売上計上は防ぐことはできるが、万が一でも違う人の情報を使いたくない。
        // (問題なかったとしても、残すはやめておきたい)
        // TODO takahara buy利用者も内部利用も "allDay" とハードコードしているので... by jflute (2026/10/23)
        // スペルミスとかで簡単に破綻してしまう。コードの表現変更もしたくなったときにやりづらい。
        // (内部利用だけだったらまだしも、たくさんいる想定のbuy利用者もハードコードになるので)
        if (ticketType.equals("allDay")) {
            if (originalEntranceCount == 1) {
                price = ONE_DAY_PRICE;
            } else if (originalEntranceCount == 2) {
                price = TWO_DAY_PRICE;
            } else if (originalEntranceCount == 4) {
                price = FOUR_DAY_PRICE;
            }
        } else if (ticketType.equals("nightOnly")) {
            if (originalEntranceCount == 2) {
                price = NIGHT_ONLY_TWO_DAY_PRICE;
            }
        }
    }

    // test_class_letsFix_refactor_recycleで追加したメソッド
    // #1on1: $今見たら、priceの部分だけが違うだけで、もうちょい抽象化できるかも (2026/10/09)
    // TODO takahara ↑の通り、もうちょい再利用のスコープを広めてみましょう by jflute (2026/10/09)
    // TODO takahara checkQuantity()にpublicになってる。公開する必要がないのでprivateでいいかなと by jflute (2026/10/09)
    // #1on1: checkQuantity() という名前、「在庫をチェックする」 (2026/10/09)
    // $若干、抽象的過ぎる？
    // 過ぎるかどうかは置いておいて、事実抽象的ではある。
    // 「在庫をチェックする」という言葉なら、在庫の枯渇チェックもあれば、在庫の品質チェックもあるかも。
    // 逆に言うと、色々と入れることができる(汎用性)。
    // 一方で、メソッド名だけでは何をやってるかわからない(直感性)。
    // SoldOutだけとは限定していないメソッドになっている。
    // 汎用性を重視したい場面なのか、直感性を重視したい場面なのか。
    // そこに意図があるかどうか。
    //
    // 後で在庫チェックの種類が増えた時、修正が少なく済むのは汎用性。
    // 一方で、そこまで気にせず、もっと呼び出し側の直感性を重視するか？
    // これ以上、Passportの種類がもっと増えるのかどうか？次第でもある。
    //
    // $今回の場合は、一つだから、直感性の方がいいかも？
    // 確かに、在庫のチェック、これ以上増える可能性があるかと言ったらかなり少ない。
    // なので、その考え方もとても良いと思う。
    //
    // done takahara ということで、↑を踏まえてメソッド名を変えてみましょう by jflute (2026/10/09)
    // done jflute 関数名をcheckQuantityから修正しました！
    // TODO takahara isだと、ほぼほぼ戻り値booleanイメージになってしまう by jflute (2026/10/23)
    // なので、throwしてる感のあるメソッド名を付けた方が良いかなと。
    // $assertとか
    // e.g. assertQuantityEnough(), throwIfSoldOut()
    // Good, assertの方が前提条件を書く感じになるので一般的かなと。
    // assertの後ろは期待する状態(正しい状態)が来る。
    // checkQuantityEnough(), checkSoldOut() だと、どっちだと例外？
    // (checkQuantity()は目的語も抽象的だし動詞も抽象的だった)
    //
    // 会話上の抽象度のお話。抽象度が変わると会話が変になったり違う意味をもったりする。
    // プログラムでも同じ話なので、抽象度のコントロールというのが大事。
    /**
     * チケットの数量が十分かどうかを確かめるメソッド。
     */
    private void isQuantityEnough() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
        }
    }

    // #1on1: 銀座のjazzbarのお話。jazzfluteのお話。jfluteのお話 (2026/10/23)
    // done flute handedMoneyが買いたいチケットに対して十分かどうかを判別する関数を追加しました！
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @param originalEntranceCount 何日利用できるチケットか
     * @param ticketType 一日中使用できる場合はallDay、夜のみ使用できる場合はnightOnly
     */
    private void isHandedMoneyEnough(int handedMoney, int originalEntranceCount, String ticketType) {
        if (ticketType.equals("allDay")) {
            if (originalEntranceCount == 1 && handedMoney < ONE_DAY_PRICE) {
                throw new TicketShortMoneyException("Short Money");
            } else if (originalEntranceCount == 2 && handedMoney < TWO_DAY_PRICE) {
                throw new TicketShortMoneyException("Short Money");
            } else if (originalEntranceCount == 3 && handedMoney < FOUR_DAY_PRICE) {
                // TODO takahara ↑3になっちゃってる。4です。 by jflute (2026/10/23)
                throw new TicketShortMoneyException("Short Money");
            }
        } else if (ticketType.equals("nightOnly")) {
            if (originalEntranceCount == 2 && handedMoney < NIGHT_ONLY_TWO_DAY_PRICE) {
                throw new TicketShortMoneyException("Short Money");
            }
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    /**
     * チケットの量を返すメソッド。
     * @return quantity チケットの量
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * 何円の利益を得たかを返すメソッド。
     * @return salesProceeds 利益の総数
     */
    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
