package org.docksidestage.bizfw.basic.buyticket;

// TODO takahara JavaDocコメント、せめて1行タイトルとauthorだけでも by jflute (2026/10/09)
/**
 * @author takahara
 */
public class TicketBuyResult {

    // done takahara handedMoney が is not used の警告が出ている by jflute (2026/10/09)
    private final int price;
    private final int handedMoney;
    private final Ticket ticket;

    public TicketBuyResult(int price, int handedMoney, int originalEntranceCount, String ticketType) {
        // done takahara Constructorにロジックを入れるか？話 by jflute (2026/10/09)
        // $「お釣りを計算する」というロジックが入っている。
        // Constructorって、インスタンスを生成することが役割なので、あまりロジック入れない傾向にある。
        // そういう前提があるから、Constructor内にロジックがあると、見つけにくいという話になる。
        // 再利用という面もあるが、再利用は別の手段(privateメソッド)で大体できるので、ここでやらなくていい。
        //
        // あと、Resultというクラスなので、ってのもある。
        // TicketReturnCreator なのか、TicketBuyResult なのか、の違い。
        // Resultだと単なる入れ物イメージなので、あまりロジックは入れない印象。
        //
        // 小さくてもれっきとしてビジネスロジック。
        //
        this.price = price;
        this.handedMoney = handedMoney;
        this.ticket = new Ticket(price, originalEntranceCount, ticketType);

        // done takahara チケットのnewがあっちらこっちらに散らばらないようにしたい by jflute (2026/10/09)
        // オブジェクト指向的な観点がわからないと難しいんですが、newは一箇所にしたい。拡張のために。
        // step6で深掘り。newが一箇所の方が良い理由が出てきます。

        // done takahara myTicketという変数名にした理由は？ by jflute (2026/10/09)
        // $自分のチケットというニュアンスを付けたかったからかな？
        // そういう別に悪くないけど、であれば getMyTicket() でも良いのかなと。
        // そういう概念を重視するという考え方なのであれば、逆にもっと前面に出していいかと。
    }

    /**
     * 買ったチケットを返すメソッド
     * @return ticket 買ったチケット
     */
    public Ticket getTicket() {
        return ticket;
    };

    /**
     * お釣りを返すメソッド。
     * @return change お釣り
     */
    public int getChange() {
        return handedMoney - price;
    };
}
