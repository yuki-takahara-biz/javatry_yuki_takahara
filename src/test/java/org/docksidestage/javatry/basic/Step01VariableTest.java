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

import java.math.BigDecimal;

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of variable. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step01VariableTest extends PlainTestCase {

    // ===================================================================================
    //                                                                      Local Variable
    //                                                                      ==============
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_variable_basic() { // example, so begin from the next method
        String sea = "mystic";
        log(sea); // your answer? => mystic
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_initial() {
        String sea = "mystic";
        Integer land = 8;
        String piari = null;
        String dstore = "mai";
        sea = sea + land + piari + ":" + dstore;
        log(sea); // your answer? => mystic8:mai(x) => mystic8null:mai(o)
        // 髙原：null文字が空文字になることを理解しました。
        // TODO done takahara 空文字ではなく"null"という文字列になりますね。 by noniwa
        // done takahara [ふぉろー] ここはプログラミング言語の決めの問題で... by jflute (2026/07/16)
        // Javaだと "null" で出ますが、C#だと空文字だったりします。
        // 本番環境で画面やメール文言にnullって表示されちゃう事故が起きやすい一方で、
        // 開発時はログや画面でnullって表示されてミスに気づきやすいって面も。
        // どっちもどっちという感じですね。ただ特徴は知っておきましょうと。

        // #1on1: nullのネタ話。メールって画面よりも油断しがち (2026/07/17)
        // #1on1: メリデメの話 (2026/07/17)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_basic() {
        String sea = "mystic"; // 1丁目1番地
        String land = "oneman"; // 2丁目2番地
        sea = land; // seaの紙を、1丁目1番地から2丁目2番地に書き換えた
        land = land + "'s dreams"; // 2丁目2番地 + 3丁目3番地 = 4丁目4番地
        log(sea); // your answer? => oneman

        // #1on1: インスタンスとは？ (2026/07/17)
        // 一軒家の例えをしてみた。
        // ローカル変数、インスタンス変数。
        // ここだと、
        // "mystic", "oneman", "'s dreams", "oneman's dreams" の4インスタンス。

        // #1on1: 変数のお話 (2026/07/17)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_int() {
        int sea = 94;
        int land = 415;
        sea = land;
        land++;
        log(sea); // your answer? => 415

        // #1on1: プリミティブ型(オブジェクトじゃないもの)、値そのものが変数に入ってる (2026/07/17)
        // Kotlinとかだと、intって書いてもオブジェクトで、Integerクラス扱い(だったような)
        // Javaが、C言語意識してか、インフラ面を意識してか、プリミティブ型を残した。
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_reassigned_BigDecimal() {
        BigDecimal sea = new BigDecimal(94);
        BigDecimal land = new BigDecimal(415);
        sea = land;
        sea = land.add(new BigDecimal(1));
        sea.add(new BigDecimal(1));
        log(sea); // your answer? => 416
        // 髙原：new が必要な時とそうでない時の違いについて知りたい。int は new が要らない。
        // TODO takahara [ふぉろー] intはプリミティブ型という特殊なデータ型で... by jflute (2026/07/16)
        // 値そのものが変数に入っているというイメージを持ってもらえればと。94そのものが変数に入っていると。
        // 一方で、BigDecimalやStringのようなオブジェクト型(こっちがほとんど)は、
        // 必ずnewしてインスタンスを生成します。構造の実体を作り上げるようなイメージです。
        // 変数には直接インスタンスは入らず、インスタンスの場所を指し示すアドレスが入ります。
        // なので、変数はただのリモコンで、実体は別の場所(メモリ上のどこか)に存在していると言う感じです。

        // 髙原：Java の型について知りたいと思いました。合わせて、Java の型は Kotlin でも同様に使用可能なのか知りたいです。
        // TODO takahara [ふぉろー] 基本的には Kotlin でも利用可能です by jflute (2026/07/16)
        // というのは、Kotlinの実行環境はJava(Runtime)だからです。
        //  Java   という言語 → コンパイルされてJavaバイトコードに変換されて → Java実行環境で動く
        //  Kotlin という言語 → コンパイルされてJavaバイトコードに変換されて → Java実行環境で動く
        // 言語だけ差し替えて、コンパイルされる同じ世界のコードになるんですね。
        // ただ、クラスというより文法で、Javaの機能がKotlinでは使えないように演出されたりとかあるので、
        // クラスも制限がもしかしたらあるかもですが、基本的には使えるはずです。

        // 髙原：BigDecimal が持っているメソッドについて知りたいと思いました。
        // done takahara [ふぉろー] add()については1on1でじっくり一緒に追求しましょう！ by jflute (2026/07/16)

        // done takahara Primitive型はnewが不要で、Object型はnewが必要ですね。 by noniwa
        //  型は大まかにPrimitiveとObject型があって、Object型変数は値そのものではなく値が格納されているメモリアドレスを保持している。
        //  具体的にどんな型があるのかはjavatry進めるうちに自然と覚えると思うので、まず以上を押さえておけば良いと思います。

        // done jflute 1on1にて、immutableについてのお話をする予定 (2026/07/16)
        // ↑このとぅどぅはくぼ用の備忘録なので、そのまま置いておいてください。
        // #1on1: immutableとは？(immutableが初耳だった) (2026/07/17)
        // BigDecimalはimmutable, JavaDocで宣言されてるし、インスタンス変数がfinal。
        // なので、インスタンスの状態が変わらない → immutable
        // 一方で、mutableだとインスタンス変数を書き換えて、状態を変えることができる。
        //
        // immutableのメリット: 絶対に変更したくない値を間違ってもいじれないので安全/安心、可読性
        // immutableのデメリット: sea.add()のケース、メモリ効率が悪い
        // mutableのメリット: メモリ効率が良い、mutableの方が書くのが簡単なケースある
        // mutableのデメリット: 間違って変えちゃう可能性、変わるかもしれないって疑いで読まないといけない(可読性)
        //
        // immutableの歴史のお話。
        // バランスの話。100%目指すのか？80%なのか？
        // 言語の文化、組織の文化、個人の文化。
        //
        // 変数のimmutableとインスタンスのimmutableの違い。

        // #1on1: BigDecimalインスタンスのお話 (2026/07/17)
        // add()の中でnewされている。
    }

    // TODO jflute 次回1on1ここから (2026/07/17)
    // 裏隔週で、Instance Variable と Method Argument のところを復習してもらう。
    // おそらく、いま見直せば色々理解できるようになっている。
    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private String instanceBroadway;
    private int instanceDockside;
    private Integer instanceHangar;
    private String instanceMagiclamp;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_String() {
        String sea = instanceBroadway;
        log(sea); // your answer? => 解りませんでした
        // 髙原：instance variable とは何かを理解したい。
        // 髙原：定義していないときは null という認識。
        // TODO takahara instance variableはその名の通りインスタンスが保持する変数で、class variableはクラスが保持する変数です。 by noniwa
        //  staticキーワードとともに宣言されていたら、それはclass variableです。
        //  > 定義していないときは null という認識。
        //  合っています！定義していないときは参照するオブジェクトがない（値が格納されているメモリアドレスを保持していない）のでnullになります。

        // done jflute 1on1にて、変数とインスタンス、そしてインスタンス変数の概念についてフォロー予定 (2026/07/16)
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_int() {
        int sea = instanceDockside;
        log(sea); // your answer? => null => 違いました、0でした。
        // 髙原：定義していない変数については、String の時 null になって、int の時は 0 になるという理解。
        // 髙原：他の型だとどうなるのか気になる。
        // TODO takahara Primitive型とObject型の変数がそれぞれ何を保持しているのかを考えると分かるかも？ by noniwa
        //  Hint: nullは参照先がない状態を示す
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_default_Integer() {
        Integer sea = instanceHangar;
        log(sea); // your answer? => 0 => 違いました、nullでした。
        // 髙原：int と Integer の違いは何か
        // TODO takahara 1つ前が理解できればこちらも分かるはず！ by noniwa
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_instance_variable_via_method() {
        instanceBroadway = "bbb";
        instanceMagiclamp = "magician";
        helpInstanceVariableViaMethod(instanceMagiclamp);
        String sea = instanceBroadway + "|" + instanceDockside + "|" + instanceHangar + "|" + instanceMagiclamp;
        log(sea); // your answer? => bigband|1||magician => bigband|1|null|magician でした。
        // 髙原：null は「null」として表示されることを理解しました。
        // TODO takahara ナイスです！ by noniwa
    }

    private void helpInstanceVariableViaMethod(String instanceMagiclamp) {
        instanceBroadway = "bigband";
        ++instanceDockside;
        instanceMagiclamp = "burn";
    }

    // ===================================================================================
    //                                                                     Method Argument
    //                                                                     ===============
    // -----------------------------------------------------
    //                                 Immutable Method-call
    //                                 ---------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_immutable_methodcall() {
        String sea = "harbor";
        int land = 415;
        helpMethodArgumentImmutableMethodcall(sea, land);
        log(sea); // your answer? => harbor
        // 髙原：sea はグローバル変数ではないので関数を通しても変化しない
        // TODO takahara ナイスです！ローカル変数を値渡ししていますね。（Javaは参照渡しはしないので全て値渡し） by noniwa
        //  sea.concat(landStr)の結果を使いたいならhelperメソッドの返り値で返してあげる必要がありますね。
    }

    private void helpMethodArgumentImmutableMethodcall(String sea, int land) {
        ++land;
        String landStr = String.valueOf(land); // is "416"
        sea.concat(landStr);
    }

    // -----------------------------------------------------
    //                                   Mutable Method-call
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_mutable_methodcall() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentMethodcall(sea, land);
        log(sea); // your answer? => harbor => harbor416 でした。
        // 髙原：なぜこうなるのか理解ができていないです
        // TODO takahara Stringはimmutable(不変)ですが、StringBuilderはmutable(可変)です。 by noniwa
        //  Stringのconcat()とStringBuilderのappend()は似てますが、前者は新しくインスタンスを生成するのに対して
        //  後者はメソッドを呼び出したインスタンスの状態を直接変えます。
        //  また、Object型の場合、値渡しでメモリアドレスのコピーを渡していることになるのも注意が必要です。
    }

    private void helpMethodArgumentMethodcall(StringBuilder sea, int land) {
        ++land;
        sea.append(land);
    }

    // -----------------------------------------------------
    //                                   Variable Assignment
    //                                   -------------------
    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_variable_method_argument_variable_assignment() {
        StringBuilder sea = new StringBuilder("harbor");
        int land = 415;
        helpMethodArgumentVariable(sea, land);
        log(sea); // your answer? => harbor416 => harbor でした。
        // 髙原：なぜこうなるのか理解できていないです。
        // TODO takahara 1つ前が理解できるとこちらも分かるかも？ by noniwa
    }

    private void helpMethodArgumentVariable(StringBuilder sea, int land) {
        ++land;
        String seaStr = sea.toString(); // is "harbor"
        sea = new StringBuilder(seaStr).append(land);
    }
    // TODO jflute noniwaさんのフォローも含めて1on1でもろもろ一緒に解釈していく (2026/07/16)

    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    /**
     * Define variables as followings:
     * <pre>
     * o local variable named sea typed String, initial value is "mystic"
     * o local variable named land typed Integer, initial value is null
     * o instance variable named piari typed int, without initial value
     * o show all variables by log() as comma-separated
     * </pre>
     * (変数を以下のように定義しましょう):
     * <pre>
     * o ローカル変数、名前はsea, 型はString, 初期値は "mystic"
     * o ローカル変数、名前はland, 型はInteger, 初期値は null
     * o インスタンス変数、名前はpiari, 型はint, 初期値なし
     * o すべての変数をlog()でカンマ区切りの文字列で表示
     * </pre>
     */
    private int piari;

    public void test_variable_writing() {
        // define variables here
        String sea = "mystic";
        Integer land = null;
        String answer = sea + "," + land + "," + piari;
        log(answer);
    }

    // ===================================================================================
    //                                                                           Good Luck
    //                                                                           =========
    /**
     * Make your original exercise as question style about variable. <br>
     * (変数についてあなたのオリジナルの質問形式のエクササイズを作ってみましょう)
     * <pre>
     * _/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
     * your question here (ここにあなたの質問を):
     * o ローカル変数、名前はa、初期値は"hoge"
     * o インスタンス変数、名前はa（同じ）、初期値は"huga"
     * o log()でで表示
     * _/_/_/_/_/_/_/_/_/_/
     * </pre>
     */
    private String a = "hoge";

    public void test_variable_yourExercise() {
        // write your code here
        String a = "huga";
        log(a); // 答え：huga
        //髙原：関数の中の変数の方が優先される。インスタンス変数の方にアクセスしたい場合はどうすればいい？
        // TODO takahara Javaにはthisという"現在のこのクラスのインスタンス"を示すキーワードがあるので、それを使えばアクセスできますね。 by noniwa
        log(this.a);
    }
}
