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
package org.docksidestage.javatry.basic;

import java.util.ArrayList;
import java.util.List;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of if-for. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step02IfForTest extends PlainTestCase {

    // ===================================================================================
    //                                                                        if Statement
    //                                                                        ============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_if_basic() { // example, so begin from the next method
        int sea = 904;
        if (sea >= 904) {
            sea = 2001;
        }
        log(sea); // your answer? => 2001
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_else_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else {
            sea = 7;
        }
        log(sea); // your answer? => 7
        // 髙原：if文とかは理解できていると思っています。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_basic() {
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
        } else if (sea >= 904) {
            sea = 7;
        } else if (sea >= 903) {
            sea = 8;
        } else {
            sea = 9;
        }
        log(sea); // your answer? => 7
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_if_elseif_nested() {
        boolean land = false;
        int sea = 904;
        if (sea > 904) {
            sea = 2001;
            sea = sea++ * 2;
        } else if (land && sea >= 904) {
            sea = 7;
            sea = ++sea * 2;
        } else if (sea >= 903 || land) {
            if (sea % 2 == 0) {
                sea = sea++ * 2;
                // 髙原：これが実行される。この時点でseaは904から1808になる
            }
            if (!land) {
                land = true;
                // 髙原：これが実行される
            } else if (sea <= 903) {
                sea++;
            }
            if (sea < 1810) {
                sea = 8;
            }
        } else if (sea == 8) {
            sea++;
            land = false;
            // 髙原：これが実行される。現時点でseaは8から9になる。landはtrueからfalseになる。
        } else {
            sea = 9;
        }
        if (sea >= 9 || (sea > 7 && sea < 9)) {
            sea--;
            // 髙原：seaは9から8になる
            if (sea % 2 == 1) {
                sea++;
                //髙原：seaは8から9になる
            }
        }
        if (land) {
            sea = 10;
        }
        log(sea); // your answer? => 8
        // done takahara 実際に実行して答えを確認してみてください〜 by jflute (2026/08/05)

        // #1on1: ちゃんと自分で間違ってたことは認識していた (2026/08/07)
        // なんで間違ったか？ else if の凡ミスじゃないかと。
        // なぜ凡ミスしたのか？
        // 括弧の対応、インデントなどのプログラム構造に対する意識を弱かったのかも？
        // 自分なんでミスったのか？ってのを振り返ることが大事。
        // でないとまた同じミスをするし、何かしらミスをするクセがあるかもしれません。
        //
        // jfluteのコツアドバイス:
        // まず構造だけ読む、独立ifが3つ、最初のifが巨大、else ifが3つ...みたいに構造把握して、
        // 全体のプログラムの地図を頭の中で作り上げてから、詳細の1行1行に突っ込んでいく。

        // #1on1: もういっこの読み方 (2026/08/07)
        // 漠然読みで構造だけ把握する、ここはさっきと同じ。当たりを付けてフォーカス読み。
        // (構造を把握すれば、今の目的を達成するための当たりが自然と見つかりやすい)
        // ここだと、landがtrueならseaは絶対に10というのが自然と目に入ってくる。
        // フォーカスをseaからlandに変えて、landのライフサイクルに注目して下から逆読みしていく。
        // 読まなくて良いものは極力読まない。
        // 
        // 全体の細かい把握はできなくても、お仕事は達成する。そういう読み方。
        // 目的に沿った読み方をする。
        //
        // BigDecimalのadd()がimmutableかmutableの例でもソースコードリーディングしてみた。
        // まず漠然読みで構造だけ把握する。構造で得られる情報で当たりを付ける。
        //
        // もちろん、ギャンブルに負けることはあるけど、
        // 一度landに注目してプログラムをある程度把握しているから、
        // 改めてベタに1行ずつ読むにしても、読むスピードが早くなってるので無駄にならない。
        // もしくは、仮説検証思考と同じで、別の仮説が生まれて、またフォーカス読みで当たるかも。
        //
        // TODO takahara [読み物課題] My Favorite Book: 仮説思考 by jflute (2026/08/07)
        // https://jflute.hatenadiary.jp/entry/20150111/kasetsu
        // ↑これ裏隔週の業務時間で読んでOKですので
    }

    // ===================================================================================
    //                                                                       for Statement
    //                                                                       =============
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_inti_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (int i = 0; i < stageList.size(); i++) {
            String stage = stageList.get(i);
            if (i == 1) {
                sea = stage;
            }
        }
        log(sea); // your answer? => dockside
        // 髙原：Javaでもリストや辞書などを扱えるようになりたいと思いました。
        // done takahara [いいね] コレクションは基礎の基礎ですからね。 by jflute (2026/08/05)
        // 他の言語とも比較しやすい素材ではありますのでぜひ(^^。

        // #1on1: Javaだと、辞書をMapと呼ぶ (2026/08/07)
        /*
         Map<String, Integer> map = new HashMap<>();
         map.put("sea", 94);
         map.put("land", 415);
        
         Integer seaInt = map.get("sea"); // 94
         Integer landInt = map.get("land"); // 415
        */
        // MapをHashで実現したのがHashMap。
        // getNode() のソースコードリーディングもしてみた。
        // TreeMapのputとgetのソースコードも読んでみた。DBのインデックスみたい。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_basic() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            sea = stage;
        }
        log(sea); // your answer? => magiclamp
        // 髙原：seaの値がbroadway ->  ... -> magiclamp に順に更新されていく

        // #1on1: Javaのfor文の種類 (現場感覚入り) (2026/08/07)
        // o (int iの)for文 // こっちは古いfor文って言われやすい
        // o 拡張for文      // 普通のfor文って言ったらこっちになりやすい
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_foreach_continueBreak() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // your answer? => hangar
        // 髙原：seaがhangarの時breakが発動する
        // 髙原：文字（列）の一致について学びたい。正規表現とかも使える方法があるのだろうか
        // #1on1: あります (2026/08/07)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_for_listforeach_basic() {
        List<String> stageList = prepareStageList();
        StringBuilder sb = new StringBuilder();
        stageList.forEach(stage -> {
            if (sb.length() > 0) {
                return;
            }
            if (stage.contains("i")) {
                sb.append(stage);
            }
        });
        String sea = sb.toString();
        log(sea); // your answer? => 先にreturnが発動するのでlogが実行されない。
        // 髙原：stageが"dockerside"の時にsbが"dockerside"になってreturnが発動するのでlogまでいかないと思っていたら、"dockerside"が出力された🤔
        // done takahara [ふぉろー] returnしてるのに不思議ですよね(^^ by jflute (2026/08/05)
        // forEach()メソッドのよるループは、ループ内の処理がコールバックになっています。
        // -> { } のカッコ内が、厳密には別クラスの別メソッドになっています。
        // なので、{ } の中で return しても、その別メソッドが終了しただけということになります。
        // 1on1でフォローしますが、良かったら forEach() のソースコード読んでみてください(^^。
        // #1on1: returnがcontinueみたいな役割になっている (2026/08/07)

        // done jflute 1on1にて、intあいのfor文、普通のfor文、forいーちメソッドのお話 (2026/08/05)
        // done jflute 1on1にて、コールバック先取り (2026/08/05)
        // (コールバックは、step8でじっくり)

        // #1on1: forいーちメソッド (文じゃない) (2026/08/07)
        // 実は単なるメソッドで、Listインターフェースにくっ付いている。(厳密にはIterable)
        // 単純に、内部では文法としてのfor文を使って、ループを代替わりしているだけ。

        // #1on1: 普通のfor文とforいーちメソッドの特徴の違いは？ (2026/08/07)
        // 後半エクササイズでいやというほど思い知るはず。

        // #1on1: Javaが3つもループの方法があるのはややこしいところ (2026/08/07)
        // 歴史的な経緯もあるし、適材適所なところもある。ここは、後半エクササイズの後でフォロー予定。
        // (厳密には、whileもあるので4つ!?)
    }

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Make list containing "a" from list of prepareStageList() and show it as log by loop. (without Stream API) <br>
     * (prepareStageList()のリストから "a" が含まれているものだけのリストを作成して、それをループで回してログに表示しましょう。(Stream APIなしで))
     */
    // done jflute noniwa ここまで完了しました🫡 レビューよろしくお願いします！🙇
    public void test_iffor_making() {
        // write if-for here
        List<String> stageList = prepareStageList();
        for (String stage : stageList) {
            if (stage.contains("a")) {
                log(stage);
            }
        }
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Change foreach statement to List's forEach() (keep result after fix) <br>
     * (foreach文をforEach()メソッドへの置き換えてみましょう (修正前と修正後で実行結果が同じになるように))
     */
    public void test_iffor_refactor_foreach_to_forEach() {
        List<String> stageList = prepareStageList();
        String sea = null;
        for (String stage : stageList) {
            if (stage.startsWith("br")) {
                continue;
            }
            sea = stage;
            if (stage.contains("ga")) {
                break;
            }
        }
        log(sea); // should be same as before-fix
    }

    /**
     * Make your original exercise as question style about if-for statement. <br>
     * (if文for文についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * 
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    public void test_iffor_yourExercise() {
        // write your code here
    }

    // ===================================================================================
    //                                                                        Small Helper
    //                                                                        ============
    private List<String> prepareStageList() {
        List<String> stageList = new ArrayList<>();
        stageList.add("broadway");
        stageList.add("dockside");
        stageList.add("hangar");
        stageList.add("magiclamp");
        return stageList;
    }
}
