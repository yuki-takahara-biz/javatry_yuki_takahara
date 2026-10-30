package org.docksidestage.bizfw.basic.buyticket;

// done takahara JavaDocコメント、せめて1行タイトルとauthorだけでも by jflute (2026/10/09)
/**
 * @author takahara
 */
public class TicketBuyResult {


    private final Ticket ticket;
    private final int change;

    // TODO jflute TicketBuyResultは「Result」の側面を重視し、メソッドは入れずにデータの入れ物として定義するように修正しました！
    public TicketBuyResult(int change, Ticket ticket) {
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
        this.change = change;
        this.ticket = ticket;

        // done takahara チケットのnewがあっちらこっちらに散らばらないようにしたい by jflute (2026/10/09)
        // オブジェクト指向的な観点がわからないと難しいんですが、newは一箇所にしたい。拡張のために。
        // step6で深掘り。newが一箇所の方が良い理由が出てきます。

        // done takahara myTicketという変数名にした理由は？ by jflute (2026/10/09)
        // $自分のチケットというニュアンスを付けたかったからかな？
        // そういう別に悪くないけど、であれば getMyTicket() でも良いのかなと。
        // そういう概念を重視するという考え方なのであれば、逆にもっと前面に出していいかと。
    }

    // done takahara getterメソッドの場合、説明自体はほぼ書くことがないので... by jflute (2026/10/23)
    // 説明の方を省略しちゃってもOKです。
    // (@returnは戻り値の説明の枠として書いておきたい。設定によっては必須だし)
    // done takahara @returnは、変数名を入れる必要はないです by jflute (2026/10/23)
    // e.g. @return 買ったチケット
    /**
     * @return 買ったチケット
     */
    public Ticket getTicket() {
        return ticket;
    };

    // done takahara Constructorから抜けてきたとはいえ...Resultクラスなので... by jflute (2026/10/23)
    // Resultだと単なる入れ物イメージなので、そもそもgetに計算は置かない方が良いかなと。
    // もちろん、calculateChange(); という名前に変えるという選択肢もある。
    // ただ、やはりResultなので、計算するメソッドがあること自体が合わない。
    // (概念的な感覚で言うと、結果というオブジェクト自体は計算しない)
    /**
     * @return お釣り
     */
    public int getChange() { return change; };
}
