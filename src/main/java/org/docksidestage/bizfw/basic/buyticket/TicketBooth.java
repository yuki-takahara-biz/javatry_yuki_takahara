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

// TODO takahara JavaDocコメント、せっかくなのでauthorを追加しましょう by jflute (2026/10/09)
/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;
    private static final int ONE_DAY_PRICE = 7400; // when 2019/06/15
    private static final int TWO_DAY_PRICE = 13200;
    private static final int FOUR_DAY_PRICE = 22400;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private int quantity = MAX_QUANTITY;
    private Integer salesProceeds; // null allowed: until first purchase

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
    // TODO takahara 戻り値を追加したので、JavaDocにも追加しましょう by jflute (2026/10/09)
    // (日本語でOK)
    /**
     * Buy one-day passport, method for park guest.
     * @param handedMoney The money (amount) handed over from park guest. (NotNull, NotMinus)
     * @throws TicketSoldOutException When ticket in booth is sold out.
     * @throws TicketShortMoneyException When the specified money is short for purchase.
     */
    // 返り値の型をTicketにした
    public Ticket buyOneDayPassport(Integer handedMoney) {
        checkQuantity(); // test_class_letsFix_refactor_recycleで修正
        if (handedMoney < ONE_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity; // 場所を移動した
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + ONE_DAY_PRICE;
        } else { // first purchase
            salesProceeds = ONE_DAY_PRICE;
        }
        // Ticketを返すようにする
        return new Ticket(handedMoney);
    }

    public TicketBuyResult buyTwoDayPassport(Integer handedMoney) {
        checkQuantity();
        if (handedMoney < TWO_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + TWO_DAY_PRICE;
        } else { // first purchase
            salesProceeds = TWO_DAY_PRICE;
        }
        return new TicketBuyResult(TWO_DAY_PRICE, handedMoney);
    }

    public TicketBuyResult buyFourDayPassport(Integer handedMoney) {
        checkQuantity();
        if (handedMoney < FOUR_DAY_PRICE) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
        --quantity;
        if (salesProceeds != null) { // second or more purchase
            salesProceeds = salesProceeds + FOUR_DAY_PRICE;
        } else { // first purchase
            salesProceeds = FOUR_DAY_PRICE;
        }
        return new TicketBuyResult(FOUR_DAY_PRICE, handedMoney);
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
    // TODO takahara ということで、↑を踏まえてメソッド名を変えてみましょう by jflute (2026/10/09)
    public void checkQuantity() {
        if (quantity <= 0) {
            throw new TicketSoldOutException("Sold out");
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
    public int getQuantity() {
        return quantity;
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
