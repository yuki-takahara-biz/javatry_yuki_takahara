package org.docksidestage.bizfw.basic.buyticket;

// TODO takahara JavaDocコメント、せめて1行タイトルとauthorだけでも by jflute (2026/10/09)
public class TicketBuyResult {

    // TODO takahara handedMoney が is not used の警告が出ている by jflute (2026/10/09)
    private final int handedMoney;
    private final int change;
    private final Ticket myTicket;

    public TicketBuyResult(int price, int handedMoney) {
        // TODO takahara Constructorにロジックを入れるか？話 by jflute (2026/10/09)
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
        this.handedMoney = handedMoney;
        this.change = handedMoney - price;
        this.myTicket = new Ticket(price);

        // TODO takahara チケットのnewがあっちらこっちらに散らばらないようにしたい by jflute (2026/10/09)
        // オブジェクト指向的な観点がわからないと難しいんですが、newは一箇所にしたい。拡張のために。
        // step6で深掘り。newが一箇所の方が良い理由が出てきます。

        // TODO takahara myTicketという変数名にした理由は？ by jflute (2026/10/09)
        // $自分のチケットというニュアンスを付けたかったからかな？
        // そういう別に悪くないけど、であれば getMyTicket() でも良いのかなと。
        // そういう概念を重視するという考え方なのであれば、逆にもっと前面に出していいかと。
    }

    public Ticket getTicket() {
        return myTicket;
    };

    public int getChange() {
        return change;
    };
}
