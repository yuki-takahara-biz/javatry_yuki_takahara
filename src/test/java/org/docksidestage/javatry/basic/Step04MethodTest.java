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

import org.docksidestage.unit.PlainTestCase;

/**
 * The test of method. <br>
 * Operate exercise as javadoc. If it's question style, write your answer before test execution. <br>
 * (javadocの通りにエクササイズを実施。質問形式の場合はテストを実行する前に考えて答えを書いてみましょう)
 * @author jflute
 * @author your_name_here
 */
public class Step04MethodTest extends PlainTestCase {

    // ===================================================================================
    //                                                                         Method Call
    //                                                                         ===========
    /**
     * What string is sea variable at the method end? <br>
     * (メソッド終了時の変数 sea の中身は？)
     */
    public void test_method_call_basic() {
        String sea = supplySomething();
        log(sea); // your answer? => over
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_call_many() {
        String sea = functionSomething("mystic"); // mysmys
        consumeSomething(supplySomething()); // takahara: consumeSomething("over")が実行されるが、
        // consumeSomethingでは、immutableな変数をreplaceするだけなのでseaの値はそのまま変わらない
        runnableSomething(); // takahara: 中で同名の変数を定義しているがreturnされるわけではなく、こっちでそれを受け取っているわけでもないのでseaはそのまま
        log(sea); // your answer? => mysmys
        // TODO takahara コメントの理解も含めてgood by noniwa
    }

    // #1on1: Functionalインターフェースの名前と合わせている (2026/09/04)
    // Function, Supplier, Consumer: step8で登場します。
    // step2で Consumer は登場した。forEach()メソッドの引数の型。
    private String functionSomething(String name) {
        String replaced = name.replace("tic", "mys");
        log("in function: {}", replaced);
        return replaced;
    }

    private String supplySomething() {
        String sea = "over";
        log("in supply: {}", sea);
        return sea;
    }

    private void consumeSomething(String sea) {
        log("in consume: {}", sea.replace("over", "mystic"));
    }

    // #1on1; Runnableインターフェースは、引数/戻り値なしでちょうど良かったので... (2026/09/04)
    // Functionalインターフェースに当てはめられた。(元々は別の目的で作られたもの) 
    private void runnableSomething() {
        String sea = "outofshadow";
        log("in runnable: {}", sea);
    }

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    public void test_method_object() {
        St4MutableStage mutable = new St4MutableStage();
        int sea = 904;
        boolean land = false;
        helloMutable(sea - 4, land, mutable); // seaとlandの値は変化しない。mutableのstageNameが"mystic"になる
        if (!land) {
            sea = sea + mutable.getStageName().length(); // 910
        }
        log(sea); // your answer? => 910
        // TODO takahara Javaでは全て値渡し。引数がprimitiveの時は値がコピーされ、objectはアドレスがコピーされる。
        //  そのため、メソッド実行後もobjectへの変更が反映される(副作用を持つことができる) by noniwa

        // #1on1: 値渡しと参照渡し (2026/09/04)
        // イメージは？
        // $値渡し: 引数で渡しても、戻り値で受け取ったりしない限り呼び出し側に影響しない by たかはらさん
        // $参照渡し: ...で影響する
        // たかはらさんのイメージと実際に起きていることがちょっと違うかも？
        //
        // 本来の意味:
        // o 値渡し: 値を渡している
        //   (値とは？ → 変数の中身: int型とかなら7とかそのもの、オブジェクト型ならアドレス)
        //   (変数の中身(値)を渡してるから値渡し) // アドレスを渡してるからといって参照渡しというわけではない
        // o 参照渡し: (変数への)参照を渡している
        //   (参照とは？ → インスタンスのアドレスじゃなく変数のアドレスみたいなイメージ)
        //
        // なので高原さんのイメージの "呼び出し側に影響しない" は、
        // "呼び出し側の変数に影響しない" であれば、本来の意味合いの値渡し。
        // 
        // 参照の値渡しとは？
        // 「インスタンスへの参照(アドレス)を値として解釈して渡す値渡し」
        //
        // 参照と言う言葉をインスタンスへの参照と捉えて、
        // 参照渡しという言葉を使ってしまうケースもある。
        // (昨今のよく使われてる言語だと、本来の意味合いの参照渡しがあまりないので...
        // なので身近で直感的な解釈が広まって行きやすいのかなと)
        //
        // jfluteは、長らく怪しい言葉だなと思って近寄ってなかったけど...
        // 新卒のみなさんが色々バラバラに学んでるので、気になって調べてみた。
        // 今後も、あまり積極的には使わないかも。
        //
        // 言葉って気をつけないといけない。(時々、こういう言葉もある)
        //
        // 副作用と言った時も、インスタンスへの副作用なのか？変数への副作用なのか？
        // の違いもある。
    }

    private int helloMutable(int sea, Boolean land, St4MutableStage piari) {
        sea++;
        land = true;
        piari.setStageName("mystic");
        return sea;
    }

    private static class St4MutableStage {

        private String stageName;

        public String getStageName() {
            return stageName;
        }

        public void setStageName(String stageName) {
            this.stageName = stageName;
        }
    }

    // ===================================================================================
    //                                                                   Instance Variable
    //                                                                   =================
    private int inParkCount;
    private boolean hasAnnualPassport;

    /** Same as the previous method question. (前のメソッドの質問と同じ) */
    // done jflute noniwa ここまでやりました！🙇 by takahara
    public void test_method_instanceVariable() {
        hasAnnualPassport = true;
        int sea = inParkCount;
        offAnnualPassport(hasAnnualPassport); // hasAnnualPassportがfalseにはならない
        for (int i = 0; i < 100; i++) { // inParkCountが100までプラスされる
            goToPark();
        }
        ++sea;
        sea = inParkCount;
        log(sea); // your answer? => 100
        // TODO takahara Javaはprimitive変数の初期化をしていない場合、デフォルトの値を暗黙的に入れてくれるみたいですね。
        //  inParkCountは暗黙的に0で初期化されているので、常に100が出力される。 by noniwa
    }

    private void offAnnualPassport(boolean hasAnnualPassport) {
        hasAnnualPassport = false;
    }

    private void goToPark() {
        if (hasAnnualPassport) {
            ++inParkCount;
        }
    }

    // TODO jflute 次回1on1ここから (2026/09/04)
    // ===================================================================================
    //                                                                           Challenge
    //                                                                           =========
    // write instance variables here
    /**
     * Make private methods as followings, and comment out caller program in test method:
     * <pre>
     * o replaceAwithB(): has one argument as String, returns argument replaced "A" with "B" as String 
     * o replaceCwithB(): has one argument as String, returns argument replaced "C" with "B" as String 
     * o quote(): has two arguments as String, returns first argument quoted by second argument (quotation) 
     * o isAvailableLogging(): no argument, returns private instance variable "availableLogging" initialized as true (also make it separately)  
     * o showSea(): has one argument as String argument, no return, show argument by log()
     * </pre>
     * (privateメソッドを以下のように定義して、テストメソッド内の呼び出しプログラムをコメントアウトしましょう):
     * <pre>
     * o replaceAwithB(): 一つのString引数、引数の "A" を "B" に置き換えたStringを戻す 
     * o replaceCwithB(): 一つのString引数、引数の "C" を "B" に置き換えたStringを戻す 
     * o quote(): 二つのString引数、第一引数を第二引数(引用符)で囲ったものを戻す 
     * o isAvailableLogging(): 引数なし、privateのインスタンス変数 "availableLogging" (初期値:true) を戻す (それも別途作る)  
     * o showSea(): 一つのString引数、戻り値なし、引数をlog()で表示する
     * </pre>
     */
    public void test_method_making() {
        // use after making these methods
        //String replaced = replaceCwithB(replaceAwithB("ABC"));
        //String sea = quote(replaced, "'");
        //if (isAvailableLogging()) {
        //    showSea(sea);
        //}
    }

    // write methods here
}
