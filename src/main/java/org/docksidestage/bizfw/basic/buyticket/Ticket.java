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

/**
 * @author jflute
 * @author takahara
 */
public class Ticket {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    // #1on1: いいね、横のすらすらコメントとても良い (2026/10/23)
    // TODO takahara originalEntranceCount, ticketType は final で immutable では by jflute (2026/10/23)
    // そうしていくと、空行の入れる位置と変数の定義順序、ちょっと変えたくなるかもね。
    private final int displayPrice; // written on ticket, park guest can watch this

    private boolean alreadyIn; // true means this ticket is unavailable
    private final String ticketType; // 一日中利用可能な場合は`allDay`が入り、夜のみの場合は`nightOnly`が入るような変数を用意しました
    private final int originalEntranceCount; // 初めの入場回数を保存する
    private int remainingEntranceCount; //残りの入場回数を減らしていく

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public Ticket(int displayPrice, int originalEntranceCount, String ticketType) {

        this.displayPrice = displayPrice;

        // done takahara パスポートの種類が増えた時、TicketBoothだけじゃなくここも修正忘れないように... by jflute (2026/10/09)
        // というのをやめたい。Ticketクラスでは、パスポートの種類に依存した処理は入れないようにしたい。TicketBoothで完結したい。
        // done jflute 元の実装ではTicketクラス内のコンストラクタで、値段で場合分けして入場可能回数を指定していましたが、それをやめて、
        //  TicketBoothから入場回数のデフォルト値を受け取るような指定にしました。
        this.originalEntranceCount = originalEntranceCount;
        this.remainingEntranceCount = originalEntranceCount;

        // TODO fflute TicketBoothからチケット種別（allDay: 一日中使用可能、nightOnly: 夜のみ使用可能）を受け取るようにしました。
        this.ticketType = ticketType;
    }

    // ===================================================================================
    //                                                                             In Park
    //                                                                             =======
    /**
     * チケットを使用して入場するためのメソッド。現在が夜であるかどうかの情報を受け取る。
     * @param isNight 現在が夜であるかどうか
     */
    public void doInPark(boolean isNight) {
        if (!isNight && ticketType.equals("nightOnly")) {
            throw new IllegalStateException("Night only ticket cannot be used now");
        }
        if (alreadyIn) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + displayPrice);
        } else {
            remainingEntranceCount--;
            if (remainingEntranceCount == 0) {
                alreadyIn = true;
            }
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                        ========
    /**
     * チケットの値段を返すメソッド。
     * @return displayPrice チケットの値段
     */
    public int getDisplayPrice() {
        return displayPrice;
    }

    /**
     * デフォルトの入場回数を返すメソッド。
     * @return originalEntranceCount デフォルトの入場回数
     */
    public int getOriginalEntranceCount() {
        return originalEntranceCount;
    };

    // TODO flute ticketTypeを返すようなメソッドを追加しました
    /**
     * チケットのタイプ（一日中使用可能か、夜のみか）を返すメソッド。
     * @return ticketType 一日中利用可能な場合はallDayが入り、夜のみの場合はnightOnlyが入る
     */
    public String getTicketType() {
        return ticketType;
    };

    // #1on1: いいね、alreadyInのニュアンスは元の通り (2026/10/23)
    // 入ったことがあるなのか？入り切ったなのか？
    // $alreadyInの名前を変えたくなった
    // いいね、TwoDayができてから、名前が曖昧になった。
    // そういう意味では、変えても構わない。
    // ただ、publicメソッドなので、呼び出し側が多い場合、メソッドまで変えるか？は要検討。
    // なので、内部の変数だけ変えて、publicメソッドはそのままという選択肢もあり。
    // もしくは、正しい名前のメソッドを作って、古い名前のメソッドは非推奨にする。
    // e.g.
    //  @deprecated 曖昧な名前なので、別の isXxxxxxxxUsed() を使ってください。
    // TODO takahara その観点で、JavaDocの表現がちょっと曖昧 by jflute (2026/10/23)
    // 1日分だけ使用したのか？使用し切ったのか？が判断できない文言になってる。
    /**
     * チケットをすでに使用したかどうかを確認するメソッド。
     * @return isAlreadyIn チケットをすでに使用したかどうか
     */
    public boolean isAlreadyIn() {
        return alreadyIn;
    }
}
